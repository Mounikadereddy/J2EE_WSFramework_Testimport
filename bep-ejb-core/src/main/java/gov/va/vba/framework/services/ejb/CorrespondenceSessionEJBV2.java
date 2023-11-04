/*
 * BaseSessionBean.java
 *
 * Copyright 2006 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.services.ejb;

import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.common.SystemUtils;
import gov.va.vba.framework.esb.documentmanagement.PdfDocument;
import gov.va.vba.framework.esb.documentmanagement.PdfDocumentType;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.serverconfig.LiveCycleContexts;
import gov.va.vba.framework.services.CorrespondenceServiceException;
import gov.va.vba.framework.services.CorrespondenceServiceRemoteV2;
import gov.va.vba.framework.services.CorrespondenceServiceV2;
import gov.va.vba.framework.services.GenericService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
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
import com.adobe.idp.DocumentError;
import com.adobe.idp.DocumentStringType;
import com.adobe.idp.dsc.clientsdk.ServiceClientFactory;
import com.adobe.idp.dsc.clientsdk.ServiceClientFactoryProperties;
import com.adobe.livecycle.formsservice.client.FormsResult;
import com.adobe.livecycle.formsservice.client.FormsServiceClient;
import com.adobe.livecycle.formsservice.client.PDFFormRenderSpec;
import com.adobe.livecycle.formsservice.client.RenderAtClient;
import com.adobe.livecycle.formsservice.client.URLSpec;
import com.adobe.livecycle.output.client.AcrobatVersion;
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
 * @since	Dec 9, 2010
 * @version	2
 * @author	Joshua Glickman
 */
@TransactionTimeoutSeconds(36000)
@Stateless(name="VbaCorrespondenceServiceV2", mappedName="vba/CorrespondenceServiceV2")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(CorrespondenceServiceV2.class)
@Remote(CorrespondenceServiceRemoteV2.class)
@CallByReference
public class CorrespondenceSessionEJBV2 extends GenericService implements CorrespondenceServiceV2 {
	
	@javax.annotation.Resource 
	private SessionContext ctx;
	private static ServiceClientFactory _svcFactory; 
	private static final String REPOSITORY = "repository://";
	private static String BASE_FILE_SYSTEM;
	private static String BATCH_FILE_PATH;
	private static javax.naming.Context _ctx;
	private static Logger logger=Logger.getLogger(CorrespondenceSessionEJBV2.class);

	private final static String EJB = "EJB";
	private final static String WS = "WS";

	public CorrespondenceSessionEJBV2() {

	}

	@PostConstruct
	public void init() {

		logger.debug("CorrespondenceSessionEJB::@PostConstruct(init)");
		setupFactory();
		
	}
	
	private boolean isEJB()
	{
		boolean result=false;
		result=EJB.equals(getAdobeLiveCycleEndpoint());
		return result;
	}
	
	private boolean isWebservice()
	{
		boolean result=false;
		result=WS.equals(getAdobeLiveCycleEndpoint());
		return result;
	}
	
	
	private void setupFactory() throws IllegalStateException
	{
		try {
			_ctx = new InitialContext();
			
			BASE_FILE_SYSTEM=getAdobeLivecycleFileSystem();
			BATCH_FILE_PATH = BASE_FILE_SYSTEM + "/batch";
			
			Properties connectionProps = getConnectionProperties();
			
			ServiceClientFactory.installThrowHandler(
					new SimpleTimeoutThrowHandler(
							(String)_ctx.lookup(LiveCycleContexts.SERVER_USER_ID.getName()), 
							(String)_ctx.lookup(LiveCycleContexts.SERVER_PASSWORD.getName()), 
							connectionProps));
			_svcFactory = ServiceClientFactory.createInstance(connectionProps);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			try {
				_ctx.close();
			} catch (NamingException e) {
				logger.error("Error closing context");
			}
		}
	}
	
	private Properties getConnectionProperties() throws Exception {
		Properties connectionProps = new Properties();
		
		logger.debug("adobe URL=" + getAdobeLivecycleURL());
		logger.debug("adobe API=" + getAdobeLiveCycleEndpoint());
		
		if (isEJB()) {
			connectionProps.setProperty(
					ServiceClientFactoryProperties.DSC_DEFAULT_EJB_ENDPOINT,
					getAdobeLivecycleURL());
			connectionProps.setProperty(
					ServiceClientFactoryProperties.DSC_TRANSPORT_PROTOCOL,
					ServiceClientFactoryProperties.DSC_EJB_PROTOCOL);
		} else if (isWebservice()) {
			connectionProps.setProperty(
					ServiceClientFactoryProperties.DSC_DEFAULT_SOAP_ENDPOINT,
					getAdobeLivecycleURL());
			connectionProps.setProperty(
					ServiceClientFactoryProperties.DSC_TRANSPORT_PROTOCOL,
					ServiceClientFactoryProperties.DSC_SOAP_PROTOCOL);
		} else {
			logger.error("invalid correspondence API");
			throw new Exception("invalid correspondence API");
		}
		
		connectionProps.setProperty(
				ServiceClientFactoryProperties.DSC_SERVER_TYPE,
				ServiceClientFactoryProperties.DSC_WEBLOGIC_SERVER_TYPE);
		connectionProps.setProperty(
				ServiceClientFactoryProperties.DSC_CREDENTIAL_USERNAME, 
				(String)_ctx.lookup(LiveCycleContexts.SERVER_USER_ID.getName()));
		connectionProps.setProperty(
				ServiceClientFactoryProperties.DSC_CREDENTIAL_PASSWORD, 
				(String)_ctx.lookup(LiveCycleContexts.SERVER_PASSWORD.getName()));
		
		return connectionProps;
	}
	

	/**
	 * generates a PDF file
	 * 
	 * @param document
	 * @param data
	 * @param print
	 * @param fileName
	 * @param auditContext
	 * @param extensions
	 * @return PDFDocument
	 * @throws CorrespondenceServiceException
	 * @since Dec 9, 2010
	 */
	public PdfDocument generatePDF(PdfDocumentType document, String data, boolean print, String fileName, AuditContext auditContext, Map extensions) 
	throws CorrespondenceServiceException {
		return generatePDF(document, data, print, fileName, false, auditContext, extensions);//LOCALTEST - change renderOnClient false to true for local test
	}

	public PdfDocument generatePDF(PdfDocumentType document, String data, boolean print, String fileName, boolean renderOnClient, AuditContext auditContext, Map extensions) 
	throws CorrespondenceServiceException {

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
		if (renderOnClient)
			pdfRenderSpec.setRenderAtClient(RenderAtClient.Yes);
		else
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
					generatePDFOutput(pdfBytes, document, fileName);						
				}
			}
			else 
				throw new CorrespondenceServiceException("PDF document could not be generated. Byte size is 0. ");			
		}
		catch (Exception e) {
			logger.error("auditContext="+auditContext,e);
			throw new CorrespondenceServiceException(e);
		}
		finally {
			//no need to dispose() if doc.setTransactionBound=true
			//doc.dispose();
			//transformedDocument.dispose();
			try {
				if(resultStream!=null) 
					resultStream.close();
				}
			catch (IOException ioe){
				throw new CorrespondenceServiceException(ioe);
				}
		}
		return pdfDocument;
	}
	
	private void generatePDFOutput(byte[] pdfBytes, PdfDocumentType document, String fileName) throws Exception {
		OutputClient outputClient = new OutputClient(_svcFactory);
		outputClient.setSynchronous(true);
		
		Document transformedDocument = outputClient.transformPDF(
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
		else {//LOCALTEST - comment out this else for local test
			logger.debug("Writing transformed PDF/A doc \""+fileName+"\" to filesystem....");
			transformedDocument.copyToFile(
					new File(new StringBuilder(BASE_FILE_SYSTEM+document.getFilePath()).append(fileName).append(".pdf").toString()));
		}	
	}


	/**
	 * processes a batch 
	 * 
	 * @param document
	 * @param data
	 * @param fileName
	 * @param auditContext
	 * @param extensions
	 * @return	String
	 * @since	Dec 9, 2010	 * 
	 * @throws 	CorrespondenceServiceException
	 */
	public String processBatch(PdfDocumentType document, String data, String fileName, boolean generateManyFiles, AuditContext auditContext, Map extensions) 
	  throws CorrespondenceServiceException {

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
			logger.error("auditContext="+auditContext,e);
			throw new CorrespondenceServiceException(e);
		}
		finally {
			logger.debug("Closing LiveCycle resources...");
		}
		return BATCH_FILE_PATH+document.getFilePath();//outputDocument.getStatusDoc().toString();//TODO: return string status doc or obj
	}
	
	/* (non-Javadoc)
	 * @see gov.va.vba.framework.services.CorrespondenceServiceV2#processBatch2(gov.va.vba.framework.esb.documentmanagement.PdfDocumentType, java.lang.String, java.lang.String, boolean, gov.va.vba.framework.common.AuditContext, java.util.Map)
	 */
	public String processBatch2(PdfDocumentType document, String data, String fileName, boolean generateManyFiles,
			AuditContext auditContext, Map extensions) throws CorrespondenceServiceException {

		OutputClient outputClient = null;
		OutputResult outputDocument = null;
		Document inputXDpDocument = null;

		try {
			inputXDpDocument = new Document(DocumentStringType.URI_STRING, REPOSITORY + document.getTemplate(), _svcFactory);
			inputXDpDocument.passivate();

			outputClient = new OutputClient(_svcFactory);
			PDFOutputOptionsSpec outputOptions = new PDFOutputOptionsSpec();
			String file = new StringBuilder(BATCH_FILE_PATH + document.getFilePath() + fileName + ".pdf").toString();
			logger.debug("Writing batch file: " + file.toString());
			outputOptions.setFileURI(file);
			outputOptions.setGenerateManyFiles(generateManyFiles);
			outputOptions.setRecordName(document.getBatchRecordId());
			outputOptions.setRecordLevel(1);
			outputOptions.setLazyLoading(true);
			RenderOptionsSpec pdfOptions = new RenderOptionsSpec();
			pdfOptions.setCacheEnabled(false);
			pdfOptions.setRenderAtClient(RenderAtClient.No.getValue());
			pdfOptions.setAcrobatVersion(AcrobatVersion.Acrobat_9);
			outputDocument = outputClient.generatePDFOutput2(
					TransformationFormat.PDF, 
					"repository://",
					inputXDpDocument, 
					outputOptions, 
					pdfOptions, 
					new Document(data.getBytes()));
			try {
				outputDocument.getStatusDoc().copyToFile(new File(BATCH_FILE_PATH + document.getFilePath() + "BatchStatus_" + System.currentTimeMillis() + ".xml"));
			} catch (NullPointerException e) {
				logger.info("Null pointer when trying to print out status document for batch, no error occurred");
			}
		} catch (DocumentError de) {
			logger.error("DocumentError auditContext=" + auditContext, de);
			throw new CorrespondenceServiceException(de);
		} catch (Exception e) {
			logger.error("auditContext=" + auditContext, e);
			throw new CorrespondenceServiceException(e);
		} finally {
			logger.debug("Closing LiveCycle resources...");
		}
		return BATCH_FILE_PATH + document.getFilePath();// outputDocument.getStatusDoc().toString();//TODO: return
														// string status doc or obj
	}
	
	/* private void writeFile(String fileName, byte[] data, AuditContext auditContext) throws CorrespondenceServiceException {
		
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
			logger.error("auditContext="+auditContext, e);
			throw new CorrespondenceServiceException("Error writing to file: ", e);
		}
		
	}*/

	@Override
	public String getAdobeLivecycleURL(AuditContext auditContext, Map extensions) {
		return getAdobeLivecycleURL();
	}
	private String getAdobeLivecycleURL() {
		String result="";
		if (isWebservice())
			result= getAdobeLiveCycleWSURL();
		if (isEJB())
			result= getAdobeLiveCycleEJBURL();
		return result;
	}
	
	private String getAdobeLiveCycleEJBURL()
	{
		String result="t3://"+SystemUtils.getProperty(SystemUtils.Key.CORRESPONDENCE_LIVECYCLE_URL);
		return result;
	}

	private String getAdobeLiveCycleWSURL()
	{
		String result="http://"+SystemUtils.getProperty(SystemUtils.Key.CORRESPONDENCE_LIVECYCLE_URL);
		return result;
	}

	@Override
	public String getAdobeLivecycleUserName(AuditContext auditContext, Map extensions) {
		return getAdobeLivecycleUserName();
	}

	private String getAdobeLivecycleUserName() {
		return getJNDIString(LiveCycleContexts.SERVER_USER_ID.getName());
	}

	private String getJNDIString(String key) {
		String result = "";
		javax.naming.Context ctx = null;
		try {
			ctx = new InitialContext();
			result = (String) ctx.lookup(key);
		} catch (NamingException e) {
			logger.error("error attempting to get the jndi string",e);
		} finally {
			try {
				ctx.close();
			} catch (NamingException e) {
				logger.error("error attempting to get the jndi string",e);
			}
		}
		logger.debug("key="+key+". value="+result);
		return result;
	}
	
	@Override
	public String getAdobeLivecyclePassword(AuditContext auditContext, Map extensions) {
		return getAdobeLivecyclePassword();
	}

	private String getAdobeLivecyclePassword() {
		return getJNDIString(LiveCycleContexts.SERVER_PASSWORD.getName());
	}

	@Override
	public String getAdobeLivecycleFileSystem(AuditContext auditContext, Map extensions) {
		return getAdobeLivecycleFileSystem();
	}
	
	private String  getAdobeLivecycleFileSystem() {
		return SystemUtils.getProperty(SystemUtils.Key.CORRESPONDENCE_LIVECYCLE_FILESYSTEM);
	}

	@Override
	public boolean isEJB(AuditContext auditContext, Map extensions) {
		return isEJB();
	}
	
	@Override
	public boolean isWebservice(AuditContext auditContext, Map extensions) {
		return isWebservice();
	}
	
	private String getAdobeLiveCycleEndpoint()
	{
		return SystemUtils.getProperty(SystemUtils.Key.CORRESPONDENCE_LIVECYCLE_ENDPOINT);
	}

	@Override
	public String getAdobeLivecycleEJBURL(AuditContext auditContext,
			Map extensions) {
		return getAdobeLiveCycleEJBURL();
	}

	@Override
	public String getAdobeLivecycleWSURL(AuditContext auditContext,
			Map extensions) {
		return getAdobeLiveCycleWSURL();
	}
}
