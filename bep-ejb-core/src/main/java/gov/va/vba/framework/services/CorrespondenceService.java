/*
 * TuxedoService.java
 *
 * Copyright 2008 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.services;

import gov.va.vba.framework.common.*;
import gov.va.vba.framework.esb.documentmanagement.*;


/**
 * Business interface for Correspondence service API
 *
 *
 * @since	Aug 21, 2008
 * @version	
 * @author	Mario Rodrigues
* <img src="doc-files/VETSNET_Correspondence.gif"> 
 */
public interface CorrespondenceService {

	/**
	 * Generates a PDF document from ASCII data passed in. 
	 * 
	 * @param document
	 * @param data
	 * @param print
	 * @param fileName
	 * @return
	 * @throws CorrespondenceServiceException	
	 * @throws	
	 * @return	
	 * @since	May 17, 2010
	 */
	 PdfDocument generatePDF(PdfDocumentType document, String data, boolean print, String fileName) 
		throws CorrespondenceServiceException;

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
	 * @throws 	Exception
	 */	
	String processBatch(PdfDocumentType document, String data, String fileName, boolean generateManyFiles) 
		throws CorrespondenceServiceException;

}
