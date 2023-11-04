/*
 * TuxedoService.java
 *
 * Copyright 2008 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.services;

import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.esb.documentmanagement.PdfDocument;
import gov.va.vba.framework.esb.documentmanagement.PdfDocumentType;

import java.util.Map;


/**
 * Business interface for Correspondence service API
 * A Service that handles all communication with AdobeLiveCycle for PDF generation and batch processing of PDF files.
 *
 * @since	Dec 9, 2010
 * @version	2
 * @author	Joshua Glickman
 * <img src="doc-files/VETSNET_Correspondence.gif"> 
 */

@SuppressWarnings("rawtypes") 
public interface CorrespondenceServiceV2 {
	/**
	 * generates a PDF file with rendering set to server side 
	 * 
	 * @param document
	 * @param data
	 * @param print
	 * @param fileName
	 * @param auditContext
	 * @param extensions
	 * @return	PDFDocument
	 * @throws 	CorrespondenceServiceException
	 * @since	Dec 9, 2010
	 */
	PdfDocument generatePDF(PdfDocumentType document, String data, boolean print, String fileName, AuditContext auditContext, Map extensions) 
	throws CorrespondenceServiceException;

	/**
	 * generates a PDF file 
	 * 
	 * @param document
	 * @param data
	 * @param print
	 * @param fileName
	 * @param renderOnClient
	 * @param auditContext
	 * @param extensions
	 * @return	PDFDocument
	 * @throws 	CorrespondenceServiceException
	 * @since	Dec 9, 2010
	 */
	PdfDocument generatePDF(PdfDocumentType document, String data, boolean print, String fileName, boolean renderOnClient, AuditContext auditContext, Map extensions) 
	throws CorrespondenceServiceException;
	/**
	 * processes a batch 
	 * 
	 * @param document
	 * @param data
	 * @param fileName
	 * @param auditContext
	 * @param extensions
	 * @return	String
	 * @throws 	CorrespondenceServiceException
	 * @since	Dec 9, 2010
	 */
	String processBatch(PdfDocumentType document, String data, String fileName, boolean generateManyFiles, AuditContext auditContext, Map extensions) 
		throws CorrespondenceServiceException;
	
	/**
	 * processes a batch using the new generatePDFOutput2 method
	 * 
	 * @param document
	 * @param data
	 * @param fileName
	 * @param generateManyFiles
	 * @param auditContext
	 * @param extensions
	 * @return
	 * @throws CorrespondenceServiceException
	 */
	String processBatch2(PdfDocumentType document, String data, String fileName, boolean generateManyFiles, AuditContext auditContext, Map extensions) 
			throws CorrespondenceServiceException;
	
	/*
	 * gets the url for accessing the adobe live cycle server
	 * @return String
	 * since May 16, 2013
	 */
	String getAdobeLivecycleURL(AuditContext auditContext, Map extensions);
	/*
	 * gets the EJB url for accessing the adobe live cycle server
	 * @return String
	 * since May 16, 2013
	 */
	String getAdobeLivecycleEJBURL(AuditContext auditContext, Map extensions);
	/*
	 * gets the WS url for accessing the adobe live cycle server
	 * @return String
	 * since May 16, 2013
	 */
	String getAdobeLivecycleWSURL(AuditContext auditContext, Map extensions);
	/*
	 * gets the username for accessing the adobe live cycle server
	 * @return String
	 * since May 16, 2013
	 */
	String getAdobeLivecycleUserName(AuditContext auditContext, Map extensions);
	/*
	 * gets the password for accessing the adobe live cycle server
	 * @return String
	 * since May 16, 2013
	 */
	String getAdobeLivecyclePassword(AuditContext auditContext, Map extensions);
	/*
	 * gets the filesystem to determine where to put report files
	 * @return String
	 * since May 16, 2013
	 */
	String getAdobeLivecycleFileSystem(AuditContext auditContext, Map extensions);
	
	/*
	 * returns true if the endpoint is EJB
	 * @return boolean
	 * since May 21, 2013
	 */

	boolean isEJB(AuditContext auditContext, Map extensions);
	
	/*
	 * returns true if the endpoint is WS
	 * @return boolean
	 * since May 21, 2013
	 */
	boolean isWebservice(AuditContext auditContext, Map extensions);

}
