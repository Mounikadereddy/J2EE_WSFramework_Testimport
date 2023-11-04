package gov.va.vba.framework.services;


import gov.va.vba.framework.BaseTestCase;
import gov.va.vba.framework.EJBTestCase;
import gov.va.vba.framework.esb.documentmanagement.PdfDocument;
import gov.va.vba.framework.esb.documentmanagement.PdfDocumentType;

import java.util.HashMap;

import javax.naming.NamingException;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.commons.lang.StringUtils;

import weblogic.rmi.extensions.PortableRemoteObject;

public class CorrespondenceServiceTest extends EJBTestCase {
	
	private enum MapKeys {userId, stationId, clientIP, applicationName, bepApplicationName, pdfDocumentType, pdfOption, fileName};

	private CorrespondenceServiceRemote correspondenceServiceRemote = createCorrespondenceServiceRemote();

	public CorrespondenceServiceTest(String arg0){
		super(arg0);
	}

	public CorrespondenceServiceTest(){
		super();
	}

	/**
	 * Method to test the generation of a PDF files.
	 * @return
	 */	
	public  void testGeneratePDF(){
		
		PdfDocument pdfDoc = null;
		PdfDocumentType document = null;		
		boolean print = false;
		String fileName = "test";
		
		try{		
			System.out.println("**********************************************");		
			System.out.println("Inside testGeneratePDF");	
			//Get the input parameters as one string
			String inputParam = readFileAsString("CS-SinglePDF-inputParam.txt"); 
			//System.out.println(inputParam);
			
			if (inputParam != null) {
				
				//Get the individual parameters from the delimited string
				HashMap<MapKeys, String> inputMap = parseInput(inputParam);

				//Set the documentType
				if("VetsnetDocument".equalsIgnoreCase(inputMap.get(MapKeys.bepApplicationName).trim())){
					document = PdfDocumentType.VetsnetDocument.valueOf(inputMap.get(MapKeys.pdfDocumentType).trim());					
				}
				else if("FocasDocument".equalsIgnoreCase(inputMap.get(MapKeys.bepApplicationName).trim())){
					document = PdfDocumentType.FocasDocument.valueOf(inputMap.get(MapKeys.pdfDocumentType).trim());	
				}

				
				//Set the printOption
				print = "true".equalsIgnoreCase(inputMap.get(MapKeys.pdfOption))?true:false;
				
				//Set the fileName
				fileName = inputMap.get(MapKeys.fileName).trim();//+"-"+getDateTime();
			}			
			
			//Get the inputData
			String data = readFileAsString("CS-SinglePDF-inputXMLData.xml");
			
			System.out.println("document: "+document);			
			System.out.println("printOption: "+print);
			System.out.println("fileName: "+fileName);	
			//System.out.println("inputData: "+data);			
			
			//Call the service
			pdfDoc = correspondenceServiceRemote.generatePDF(document, data, print, fileName);
		
			//assert the output
			assertTrue(pdfDoc != null);
			assertTrue(pdfDoc.getPageCount() > 0);
			System.out.println("PDF document page count = " + pdfDoc.getPageCount());						
			
			//Test Code to generate PDF file on locally
			/*FileOutputStream fileoutputstream = new FileOutputStream("C:\\temp\\Sample_Vets_DIC.pdf");
			if(pdfDoc != null){
				
				byte[] contents = pdfDoc.getContent();

				for (int i = 0; i < contents.length; i++) {
					fileoutputstream.write(contents[i]);
				}
	        	fileoutputstream.flush();
		        fileoutputstream.close();
			}*/
		}
		catch(Exception ex){
			ex.printStackTrace();
			Assert.fail("Exception: testProcessBatch Failed!!!!");							
		}

		System.out.println("testGeneratePDF SUCCESS!");
		System.out.println("**********************************************");	
	}
	
	
	/**
	 * Method to test the generation of multiple PDF files.
	 * @return
	 */	
	public void testProcessBatch(){		
		
		PdfDocumentType document = null;		
		boolean generateManyFiles = false;
		String fileName = null;
	
		try{
			System.out.println("**********************************************");		
			System.out.println("Inside testProcessBatch");	

			//Get the input parameters as one string
			String inputParam = readFileAsString("CS-BatchPDF-inputParam.txt"); 
			//System.out.println(inputParam);

			if (inputParam != null) {
				
				//Get the individual parameters from the delimited string
				HashMap<MapKeys, String> inputMap = parseInput(inputParam);

				//Set the documentType
				if("VetsnetDocument".equalsIgnoreCase(inputMap.get(MapKeys.bepApplicationName).trim())){
					document = PdfDocumentType.VetsnetDocument.valueOf(inputMap.get(MapKeys.pdfDocumentType).trim());					
				}
				else if("FocasDocument".equalsIgnoreCase(inputMap.get(MapKeys.bepApplicationName).trim())){
					document = PdfDocumentType.FocasDocument.valueOf(inputMap.get(MapKeys.pdfDocumentType).trim());	
				}
				
				//Set the generateFilesOption
				generateManyFiles = "true".equalsIgnoreCase(inputMap.get(MapKeys.pdfOption).trim())?true:false;
				
				//Set the fileName
				fileName = (inputMap.get(MapKeys.fileName)).trim();//+"-"+getDateTime();
			}	
			
			//Get the inputData
			String data = readFileAsString("CS-BatchPDF-inputXMLData.txt");
			
			System.out.println("document: "+document);			
			System.out.println("generateManyFilesOption: "+generateManyFiles);
			System.out.println("fileName: "+fileName);	
			//System.out.println("inputData: "+data);	
				
			//Call the service and assert the output	
			String status = BATCH_FILE_PATH+document.getFilePath(); //the service returns the filepath on successful creation of pdf files			
			assertEquals(correspondenceServiceRemote.processBatch(document, data, fileName, generateManyFiles), status );
			System.out.println("Status: "+status);
		}
		catch(Exception ex){
			ex.printStackTrace();
			Assert.fail("Exception: testProcessBatch Failed!!!!");							
		}
		System.out.println("testProcessBatch SUCCESS!");
		System.out.println("**********************************************");	
	}
	
	
	/**
	 * Method to parse the test input
	 * @param String input
	 * @return Map
	 */
	private HashMap<MapKeys, String> parseInput(String input) {
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
	
	/**
	 * JUnit Test Suite
	 * @return
	 */
	public static Test suite() {
		TestSuite ts = new TestSuite();
		ts.addTest(new CorrespondenceServiceTest("testGeneratePDF"));
		ts.addTest(new CorrespondenceServiceTest("testProcessBatch"));
		return ts;
	}

	public CorrespondenceServiceRemote createCorrespondenceServiceRemote(){
		setupEJBClient();
		Object ref = null;
		try {
			ref = getJNDIContext().lookup("vba/CorrespondenceService#"
					+ CorrespondenceServiceRemote.class.getName());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return (CorrespondenceServiceRemote) PortableRemoteObject
				.narrow(ref, CorrespondenceServiceRemote.class);

	}



}
