package gov.va.vba.framework.services;

import gov.va.vba.framework.BaseTestCase;
import gov.va.vba.framework.EJBTestCase;
import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.esb.documentmanagement.PdfDocument;
import gov.va.vba.framework.esb.documentmanagement.PdfDocumentType;
import gov.va.vba.framework.logging.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.commons.lang.StringUtils;

/*to test locally -
 * 1) ensure the livecycle JNDI binder is set on start up
 * 2) comment out areas marked with LOCALTEST in CorrespondenceSessionEJBV2/CorrespondenceSessionEJBV2Local
 * 3) ensure path in outputDir exists 
 * 4) run junit configuration bep-testlocalejb-web/src/main/resources/eclipse-launchers/CorrespondenceServiceV2Test.launch
 */
public class CorrespondenceServiceV2Test extends EJBTestCase {

	public static Logger logger = Logger
			.getLogger(CorrespondenceServiceV2Test.class);

	private enum MapKeys {
		userId, stationId, clientIP, applicationName, bepApplicationName, pdfDocumentType, pdfOption, fileName
	};

	private CorrespondenceServiceRemoteV2 correspondenceServiceV2 = createCorrespondenceServiceRemoteV2(getJNDIContext());
	
	private final String outputDir = "c:\\temp\\";

	/**
	 * Constructor for CorrespondenceServiceV2Test.
	 * 
	 * @param arg0
	 */
	public CorrespondenceServiceV2Test(String arg0) {
		super(arg0);
	}

	public CorrespondenceServiceV2Test() {
		super();
	}


	public void testGetUsername() {
		assertEquals("administrator", correspondenceServiceV2.getAdobeLivecycleUserName(getAuditContext(),null));
	}
	public void testGetPassword() {
		assertEquals("password", correspondenceServiceV2.getAdobeLivecyclePassword(getAuditContext(),null));
	}
	public void testGetURL() {
		if (correspondenceServiceV2.isWebservice(getAuditContext(), null))
			assertEquals("http://vaausappwbt802.aac.va.gov:50015", correspondenceServiceV2.getAdobeLivecycleURL(getAuditContext(),null));
		if (correspondenceServiceV2.isEJB(getAuditContext(), null))
			assertEquals("t3://vaausappwbt802.aac.va.gov:50015", correspondenceServiceV2.getAdobeLivecycleURL(getAuditContext(),null));
		
	}
	public void testGetEJBURL() {
			assertEquals("t3://vaausappwbt802.aac.va.gov:50015", correspondenceServiceV2.getAdobeLivecycleEJBURL(getAuditContext(),null));
		
	}
	public void testGetWSURL() {
			assertEquals("http://vaausappwbt802.aac.va.gov:50015", correspondenceServiceV2.getAdobeLivecycleWSURL(getAuditContext(),null));
		
	}
	public void testGetFilesystem() {
		assertEquals("/AdobeDoc/"+env, correspondenceServiceV2.getAdobeLivecycleFileSystem(getAuditContext(),null));
	}
	
	
	public void testIsEJB()
	{
		logger.debug("isEJB()="+correspondenceServiceV2.isEJB(getAuditContext(), null));
	}

	public void testIsWebservice()
	{
		logger.debug("isWS()="+correspondenceServiceV2.isWebservice(getAuditContext(), null));
	}

	/**
	 * Method to test the generation of a PDF files.
	 * 
	 * @return
	 */
	public void testGeneratePDF() {

		PdfDocument pdfDoc = null;
		PdfDocumentType document = null;
		boolean print = false;
		String fileName = null;
		AuditContext context = new AuditContext();

		try {
			logger.info("**********************************************");
			logger.info("Inside testGeneratePDF-V2");

			// Get the input parameters as one string
			String inputParam = readFileAsString("CS-SinglePDF-inputParam-RBPS.txt");
			logger.info(inputParam);

			if (inputParam != null) {

				// Get the individual parameters from the delimited string
				HashMap<MapKeys, String> inputMap = parseInput(inputParam);

				// Set the context
				context.setUserId(inputMap.get(MapKeys.userId));
				context.setStationID(inputMap.get(MapKeys.stationId));
				context.setClientIPAddress(inputMap.get(MapKeys.clientIP));
				context.setApplicationName(inputMap
						.get(MapKeys.applicationName));

				// Set the documentType
				if ("VetsnetDocument".equalsIgnoreCase(inputMap.get(
						MapKeys.bepApplicationName).trim())) {
					document = PdfDocumentType.VetsnetDocument.valueOf(inputMap
							.get(MapKeys.pdfDocumentType).trim());
				} else if ("FocasDocument".equalsIgnoreCase(inputMap.get(
						MapKeys.bepApplicationName).trim())) {
					document = PdfDocumentType.FocasDocument.valueOf(inputMap
							.get(MapKeys.pdfDocumentType).trim());
				} else if ("RBPSDocument".equalsIgnoreCase(inputMap.get(
						MapKeys.bepApplicationName).trim())) {
					document = PdfDocumentType.RBPSDocument.valueOf(inputMap
							.get(MapKeys.pdfDocumentType).trim());
				}

				// Set the printOption
				print = "true".equalsIgnoreCase(inputMap.get(MapKeys.pdfOption)
						.trim()) ? true : false;
				print=true;
				// Set the fileName
				fileName = inputMap.get(MapKeys.fileName).trim();// +"-"+getDateTime();
			}

			// Get the inputData
			// String data = readFileAsString("CS-SinglePDF-inputXMLData.xml");
			String data = readFileAsString("RBPS.xml");

			logger.info("Context: " + context.getUserId() + ":"
					+ context.getStationID() + ":"
					+ context.getClientIPAddress() + ":"
					+ context.getApplicationName());
			logger.info("document: " + document);
			logger.info("printOption: " + print);
			logger.info("fileName: " + fileName);
			// logger.info("inputData: "+data);

			// Call the service
			pdfDoc = correspondenceServiceV2.generatePDF(document, data, print,
					fileName, context, null);

			assertTrue(pdfDoc != null);
			assertTrue(pdfDoc.getPageCount() > 0);
			logger.info("PDF document page count = " + pdfDoc.getPageCount());
			try {
				//ensure the path exists already
				FileOutputStream fos = new FileOutputStream(outputDir+"output.pdf");
				fos.write(pdfDoc.getContent());
				fos.close();
			} catch (IOException x) {
				System.err.format("IOException: %s%n", x);
			}
		} catch (AssertionFailedError afe) {
			logger.error("Reason for failure : " + afe.getMessage());
			logger.error("**********************************************");
			Assert.fail("AssertionFailedError for assertTrue: testGeneratePDF-V2 Failed!!!!");
		} catch (Exception ex) {
			ex.printStackTrace();
			// logger.error("Reason for exception : "+ex.getMessage());
			logger.error("**********************************************");
			Assert.fail("Exception: testGeneratePDF-V2 Failed!!!!");
		}

		logger.info("testGeneratePDF-V2 SUCCESS!");
		logger.info("**********************************************");
	}

	/**
	 * Method to test the generation of multiple PDF files.
	 * 
	 * @return
	 */
	public void testProcessBatch() {

		PdfDocumentType document = null;
		boolean generateManyFiles = false;
		String fileName = null;

		AuditContext context = new AuditContext();

		try {
			logger.info("**********************************************");
			logger.info("Inside testProcessBatch-V2");

			// Get the input parameters as one string
			String inputParam = readFileAsString("CS-BatchPDF-inputParam-Focas.txt");
			logger.info(inputParam);

			if (inputParam != null) {

				// Get the individual parameters from the delimited string
				HashMap<MapKeys, String> inputMap = parseInput(inputParam);

				// Set the context
				context.setUserId(inputMap.get(MapKeys.userId));
				context.setStationID(inputMap.get(MapKeys.stationId));
				context.setClientIPAddress(inputMap.get(MapKeys.clientIP));
				context.setApplicationName(inputMap
						.get(MapKeys.applicationName));

				// Set the documentType
				if ("VetsnetDocument".equalsIgnoreCase(inputMap.get(
						MapKeys.bepApplicationName).trim())) {
					document = PdfDocumentType.VetsnetDocument.valueOf(inputMap
							.get(MapKeys.pdfDocumentType).trim());
				} else if ("FocasDocument".equalsIgnoreCase(inputMap.get(
						MapKeys.bepApplicationName).trim())) {
					document = PdfDocumentType.FocasDocument.valueOf(inputMap
							.get(MapKeys.pdfDocumentType).trim());
				}

				// Set the generateFilesOption
				generateManyFiles = "true".equalsIgnoreCase(inputMap.get(
						MapKeys.pdfOption).trim()) ? true : false;

				// Set the fileName
				fileName = (inputMap.get(MapKeys.fileName)).trim();// +"-"+getDateTime();
			}

			// Get the inputData
			String data = readFileAsString("CS-BatchPDF-inputXMLData-Focas.txt");

			logger.info("Context: " + context.getUserId() + ":"
					+ context.getStationID() + ":"
					+ context.getClientIPAddress() + ":"
					+ context.getApplicationName());
			logger.info("document: " + document);
			logger.info("generateManyFilesOption: " + generateManyFiles);
			logger.info("fileName: " + fileName);
			// logger.info("inputData: "+data);

			// String status =
			// getCorrespondenceServiceV2().processBatch(document, data,
			// fileName, generateManyFiles);
			// assertTrue(status != null);

			// Call the service and assert the output
			String status = BATCH_FILE_PATH + document.getFilePath(); // the service returns the filepath on successful creation of pdf
			String actualResults=correspondenceServiceV2.processBatch(document, data,
					fileName, generateManyFiles, context, null);										// files
			assertEquals(actualResults, status);

		} catch (IOException e) {
			logger.error("" + e.getMessage());
			logger.error("**********************************************");
			Assert.fail("IOException while reading the XMLFile: testProcessBatch-V2 Failed!!!!");
		} catch (AssertionFailedError afe) {
			logger.error("Reason for failure : " + afe.getMessage());
			logger.error("**********************************************");
			Assert.fail("AssertionFailedError: testProcessBatch-V2 Failed!!!!");
		} catch (Exception ex) {
			ex.printStackTrace();
			// logger.error(""+ex.getMessage());
			logger.error("**********************************************");
			Assert.fail("Exception: testProcessBatch-V2 Failed!!!!");
		}

		logger.info("testProcessBatch-V2 SUCCESS!");
		logger.info("**********************************************");
	}

	/**
	 * Method to get the timeStamp
	 * 
	 * @return timestamp
	 */
	private final static String getDateTime() {
		DateFormat df = new SimpleDateFormat("MM-dd_hh:mm");
		df.setTimeZone(TimeZone.getTimeZone("EST"));
		return df.format(new Date());
	}

	/**
	 * Method to parse the test input
	 * 
	 * @param String
	 *            input
	 * @return Map
	 */
	public HashMap<MapKeys, String> parseInput(String input) {
		String[] fields = StringUtils.split(input, "~");
		HashMap<MapKeys, String> map = new HashMap<MapKeys, String>();
		map.put(MapKeys.userId, fields[0]);
		map.put(MapKeys.stationId, fields[1]);
		map.put(MapKeys.clientIP, fields[2]);
		map.put(MapKeys.applicationName, fields[3]);
		map.put(MapKeys.bepApplicationName, fields[4]);
		map.put(MapKeys.pdfDocumentType, fields[5]);
		map.put(MapKeys.pdfOption, fields[6]);
		map.put(MapKeys.fileName, fields[7]);
		return map;
	}


	public CorrespondenceServiceRemoteV2 createCorrespondenceServiceRemoteV2(
			Context jndiContext) {
		Object ref = null;
		try {
			ref = jndiContext.lookup("vba/CorrespondenceServiceV2#"
					+ CorrespondenceServiceRemoteV2.class.getName());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return (CorrespondenceServiceRemoteV2) PortableRemoteObject
				.narrow(ref, CorrespondenceServiceRemoteV2.class);

	}
	
	private AuditContext getAuditContext()
	{
		AuditContext context = new AuditContext();

		context.setUserId("doesn't mastter");
		context.setStationID("doesn't matter");
		context.setClientIPAddress("doesn't matter");
		context.setApplicationName("doesn't matter");
		return context;
	}

}
