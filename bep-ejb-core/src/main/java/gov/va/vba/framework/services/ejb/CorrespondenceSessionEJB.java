/*
 * BaseSessionBean.java
 *
 * Copyright 2006 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.services.ejb;

import gov.va.vba.framework.auditing.LegacyEJBAuditer;
import gov.va.vba.framework.common.SystemUtils;
import gov.va.vba.framework.esb.documentmanagement.PdfDocument;
import gov.va.vba.framework.esb.documentmanagement.PdfDocumentType;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.serverconfig.LiveCycleContexts;
import gov.va.vba.framework.services.CorrespondenceService;
import gov.va.vba.framework.services.CorrespondenceServiceException;
import gov.va.vba.framework.services.CorrespondenceServiceRemote;
import gov.va.vba.framework.services.GenericService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import weblogic.javaee.CallByReference;
import weblogic.javaee.TransactionTimeoutSeconds;

import com.adobe.idp.Document;
import com.adobe.idp.dsc.clientsdk.ServiceClientFactory;
import com.adobe.idp.dsc.clientsdk.ServiceClientFactoryProperties;
import com.adobe.livecycle.formsservice.client.FormsResult;
import com.adobe.livecycle.formsservice.client.FormsServiceClient;
import com.adobe.livecycle.formsservice.client.PDFFormRenderSpec;
import com.adobe.livecycle.formsservice.client.RenderAtClient;
import com.adobe.livecycle.formsservice.client.URLSpec;
import com.adobe.livecycle.output.client.OutputClient;
import com.adobe.livecycle.output.client.OutputResult;
import com.adobe.livecycle.output.client.PDFAConformance;
import com.adobe.livecycle.output.client.PDFARevisionNumber;
import com.adobe.livecycle.output.client.PDFOutputOptionsSpec;
import com.adobe.livecycle.output.client.RenderOptionsSpec;
import com.adobe.livecycle.output.client.TransformationFormat;


/**
 * A Service that handles all communication with AdobeLiveCycle for PDF generation and batch processing of PDF files.
 *  
 * @since	Mar 31, 2006
 * @version	
 * @author	Mario Rodrigues
 */
@TransactionTimeoutSeconds(36000)
@Stateless(name="VbaCorrespondenceService", mappedName="vba/CorrespondenceService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(CorrespondenceService.class)
@Remote(CorrespondenceServiceRemote.class)
@CallByReference
public class CorrespondenceSessionEJB extends GenericService implements CorrespondenceService {
	
	@javax.annotation.Resource 
	private SessionContext ctx;
	private static ServiceClientFactory _svcFactory; 
	private static final String REPOSITORY = "repository://";
	private static String BASE_FILE_SYSTEM;
	private static String BATCH_FILE_PATH;
	private static javax.naming.Context _ctx;
	private static Logger logger=Logger.getLogger(CorrespondenceSessionEJB.class);

	/**
	 *
	 * @since	Oct 9, 2008
	 * @version
	 */
	public CorrespondenceSessionEJB() {

	}

	/**
	 * 	
	 * @throws	
	 * @return	
	 * @since	Oct 14, 2008
	 */
	@PostConstruct
	public void init() {
		
		logger.debug("CorrespondenceSessionEJB::@PostConstruct(init)");
		try {
			_ctx = new InitialContext();
			BASE_FILE_SYSTEM=SystemUtils.getProperty(SystemUtils.Key.CORRESPONDENCE_LIVECYCLE_FILESYSTEM);
			BATCH_FILE_PATH = BASE_FILE_SYSTEM+"/batch";
			Properties connectionProps = new Properties();
			connectionProps.setProperty(ServiceClientFactoryProperties.DSC_DEFAULT_EJB_ENDPOINT, 
					SystemUtils.getProperty(SystemUtils.Key.CORRESPONDENCE_LIVECYCLE_URL));
			connectionProps.setProperty(ServiceClientFactoryProperties.DSC_TRANSPORT_PROTOCOL, "EJB"); 
			connectionProps.setProperty(ServiceClientFactoryProperties.DSC_SERVER_TYPE, 
					ServiceClientFactoryProperties.DSC_WEBLOGIC_SERVER_TYPE);
			connectionProps.setProperty(ServiceClientFactoryProperties.DSC_CREDENTIAL_USERNAME, 
					(String)_ctx.lookup(LiveCycleContexts.SERVER_USER_ID.getName()));
			connectionProps.setProperty(ServiceClientFactoryProperties.DSC_CREDENTIAL_PASSWORD,
					(String)_ctx.lookup(LiveCycleContexts.SERVER_PASSWORD.getName()));		
			_svcFactory = ServiceClientFactory.createInstance(connectionProps);			
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			try {
				_ctx.close();
			} catch (NamingException e) {
				logger.error("Error closing context", e);
			}
		}		
	}
	
	
	/**
	 * Generates a PDF document from ASCII data passed in. 
	 * 
	 * @param document
	 * @param data
	 * @param print
	 * @param fileName
	 * @return
	 * @throws CorrespondenceServiceException	
	 * @return	
	 */
	public PdfDocument generatePDF(PdfDocumentType document, String data, boolean print, String fileName) 
		throws CorrespondenceServiceException {

		new LegacyEJBAuditer().audit(CorrespondenceSessionEJB.class.getName(), "generatePDF");
		FormsServiceClient formsClient = null;
		OutputClient outputClient = null;
		byte[] pdfBytes = null;
		PDFFormRenderSpec pdfRenderSpec = null;
		URLSpec uriValues = null;
		Document doc = null;
		Document transformedDocument = null;
		FormsResult result = null;
		InputStream resultStream = null;
		PdfDocument pdfDocument = null;
		
		pdfRenderSpec = new PDFFormRenderSpec();
		pdfRenderSpec.setRenderAtClient(RenderAtClient.No);
		// pdfRenderSpec.setPDFVersion(PDFVersion.PDFVersion_1_6);
		// pdfRenderSpec.setAcrobatVersion(AcrobatVersion.Acrobat_8);
		pdfRenderSpec.setCacheEnabled(true);
		pdfRenderSpec.setTaggedPDF(true);
		// pdfRenderSpec.setLinearizedPDF(true);
		uriValues = new URLSpec();
		uriValues.setContentRootURI(REPOSITORY);
		uriValues.setApplicationWebRoot("http://localhost:7002");
		uriValues.setTargetURL("/framework/test");
		logger.debug("Getting Adobe template: " + document.getTemplate());
		try {			
			doc = new Document(data.getBytes());
			doc.setTransactionBound(true);
			//writeFile("XMLData", data);
			formsClient = new FormsServiceClient(_svcFactory);
			result = formsClient.renderPDFForm(document.getTemplate(), doc, pdfRenderSpec, uriValues, null);
			resultStream = result.getOutputContent().getInputStream();
			pdfBytes = new byte[resultStream.available()];
			logger.debug("Result stream size: "+ pdfBytes.length);
			if (pdfBytes.length != 0) {					
				resultStream.read(pdfBytes);
				pdfDocument = new PdfDocument();
				pdfDocument.setPageCount(result.getPageCount());
				pdfDocument.setContent(pdfBytes);
				pdfDocument.setContentType(result.getContentType());
				if (print) {
					outputClient = new OutputClient(_svcFactory);
					outputClient.setSynchronous(true);
					transformedDocument = outputClient.transformPDF(
						new Document(pdfBytes),
						TransformationFormat.PDFA,
						PDFARevisionNumber.Revision_1,
						"",
						PDFAConformance.A);  
					transformedDocument.setTransactionBound(true);					
					logger.debug("Transformed PDF/A doc stream size: "+transformedDocument.getInputStream().available());					
					logger.debug("Transformed PDF/A doc length: "+transformedDocument.length());					
					if (transformedDocument.getInputStream().available()==0 && transformedDocument.length()==0)
						throw new CorrespondenceServiceException("PDF/A version of document could not be generated! ");
					else {
						logger.debug("Writing transformed PDF/A doc \""+fileName+"\" to filesystem....");
						transformedDocument.copyToFile(
								new File(new StringBuilder(BASE_FILE_SYSTEM+document.getFilePath()).append(fileName).append(".pdf").toString()));
					}						
				}
			}
			else 
				throw new CorrespondenceServiceException("PDF document could not be generated. Byte size is 0. ");			
		}
		catch (Exception e) {
			logger.error("",e);
			throw new CorrespondenceServiceException(e);
		}
		finally {
			//no need to dispose() if doc.setTransactionBound=true
			//doc.dispose();
			//transformedDocument.dispose();
			try {resultStream.close();}
			catch (IOException ioe){throw new CorrespondenceServiceException(ioe);}
		}
		return pdfDocument;
	}

	/**
	 * VETSNET batch processing method. Generates files on a file system that runs on the same 
	 * server as the Adobe LiveCycle instance.
	 * 
	 * @param 	PdfDocumentType. 
	 * @param 	String 
	 * @param 	baseFileName. The filename <b>without an extension</b> (do not include the ".pdf" extension). "0001" thru "9999" 
	 * 			will be appended to this name based on the number of records in the batch.
	 * @param	boolean
	 * @return	String containing the batch processing status. 
	 * @throws 	CorrespondenceServiceException
	 */	
	public String processBatch(PdfDocumentType document, String data, String fileName, boolean generateManyFiles) 
	  throws CorrespondenceServiceException {

		new LegacyEJBAuditer().audit(CorrespondenceSessionEJB.class.getName(), "processBatch");
		OutputClient outputClient = null;
		OutputResult outputDocument = null;
				
		try {			
			outputClient = new OutputClient(_svcFactory); 					
			PDFOutputOptionsSpec outputOptions = new PDFOutputOptionsSpec();
			String file = new StringBuilder(BATCH_FILE_PATH+document.getFilePath()+fileName+".pdf").toString();
			logger.debug("Writing batch file: "+file.toString());
			outputOptions.setFileURI(file);	
			outputOptions.setGenerateManyFiles(generateManyFiles);
			outputOptions.setRecordName(document.getBatchRecordId());			
			RenderOptionsSpec pdfOptions = new RenderOptionsSpec(); 
			pdfOptions.setCacheEnabled(false);
			outputDocument = outputClient.generatePDFOutput(
					TransformationFormat.PDF,
					document.getTemplate(),
					"repository://",
					outputOptions,
					pdfOptions,
					new Document(data.getBytes()));
			outputDocument.getStatusDoc().copyToFile(new File(BATCH_FILE_PATH+document.getFilePath()+"BatchStatus_"+System.currentTimeMillis()+".xml"));
		}
		catch (Exception e) {
			logger.error("",e);
			throw new CorrespondenceServiceException(e);
		}
		finally {
			logger.debug("Closing LiveCycle resources...");
		}
		return BATCH_FILE_PATH+document.getFilePath();//outputDocument.getStatusDoc().toString();//TODO: return string status doc or obj
	}
	
	/**
	 * 
	 * @param 	fileName
	 * @param 	data	
	 * @throws	
	 * @return	
	 * @since	Feb 24, 2009
	 */
	/* private void writeFile(String fileName, byte[] data) throws CorrespondenceServiceException {
		
		BufferedOutputStream outputBuffer = null;
		
		try {
			logger.debug("Writing \""+fileName+"\" to filesystem....");
			outputBuffer = new BufferedOutputStream(
					new FileOutputStream(
						new StringBuilder("FIXME!!").append(fileName).append(".pdf").toString()));
			outputBuffer.write(data);
			outputBuffer.close();
		}
		catch (IOException e) {
			logger.error(e);
			throw new CorrespondenceServiceException("Error writing to file: ", e);
		}
		
	}*/
}
