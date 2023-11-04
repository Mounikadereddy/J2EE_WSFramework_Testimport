/*
 * PdfDocument.java
 *
 * Copyright 2009 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */

package gov.va.vba.framework.esb.documentmanagement;


/**
 * Document class that represents PDF Documents.
 *
 * @since	Apr 21, 2009
 * @version	
 * @author	Mario Rodrigues
 */
public class PdfDocument extends Document {

	private static final long serialVersionUID = 1450108124883072179L;
	private byte[] content;
	private String contentType;
	private long pageCount; 
	
	/**
	 * 
	 *
	 * @since	Apr 21, 2009
	 * @version	
	 */
	public PdfDocument() {
		super();
	}

	
	/**
	 * @return the pdfBytes
	 */
	public byte[] getContent() {
	
		return content;
	}

	
	/**
	 * @param pdfBytes the pdfBytes to set
	 */
	public void setContent(byte[] content) {
	
		this.content = content;
	}

	
	/**
	 * @return the pageCount
	 */
	public long getPageCount() {
	
		return pageCount;
	}

	
	/**
	 * @param pageCount the pageCount to set
	 */
	public void setPageCount(long pageCount) {
	
		this.pageCount = pageCount;
	}


	
	/**
	 * @return the contentType
	 */
	public String getContentType() {
	
		return contentType;
	}


	
	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
	
		this.contentType = contentType;
	}
	
	/**
	 * Implementation of java.lang.Object contract. 
	 * 
	 * @param
	 * @return
	 * @exception
	 * @since
	 * @see	gov.va.vba.framework.domain.vo#toString() 
	 */
	/* public String toString() {
		return super.toString();
	}*/
}
