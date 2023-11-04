/*
 * PdfDocumentType.java
 *
 * Copyright 2009 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */

package gov.va.vba.framework.esb.documentmanagement;



/**
 * A generic PDF document interface that all VBA PDF documents must conform to.  Includes a set a published document types (enums) for 
 * easy access by applications.
 *
 * @since	Aug 6, 2009
 * @version	
 * @author	Mario Rodrigues
 */
public interface PdfDocumentType {
	
	DocumentData getData();
	String getTemplate();
	String getBatchRecordId();
	String getFilePath();
	
	/**w
	 * VETSNET Pension Award Letter document types
	 *
	 * @since	Aug 2, 2009
	 * @version	
	 * @author	Mario Rodrigues
	 */
	public enum VetsnetDocument implements PdfDocumentType {
	
		PENSION_AWARD_LETTER(DocumentData.class, "/Applications/VETSNET/1.0/Pension Letters/Forms/PensionLetterTemplate.xdp", "letter"),
		PENSION_ADJUSTMENT_LETTER(DocumentData.class, "/Applications/VETSNET/1.0/Pension Letters/Forms/PensionAdjLetterTemplate.xdp", "letter"),
		DIC_ADJUSTMENT_LETTER(DocumentData.class, "/Applications/VETSNET/1.0/Pension Letters/Forms/DICAdjustmentLetter.xdp", "letter");
		
		private final String FILE_GENERATION_PATH = "/VetsNet/PensionAwardLetter/";
		private DocumentData data;
		private final String template;
		private final String batchRecordId;
		
		private VetsnetDocument(Class<? extends DocumentData> data, String template, String batchRecordId) {			
			//this.data = (DocumentData)data.getClass().getConstructor(new Class[]{}).newInstance(new Object[]{});
			this.template = template;
			this.batchRecordId = batchRecordId;			
		}
				
		public DocumentData getData() {
			return data;
		}
		
		public String getTemplate() {
			return template;
		}

		public String getBatchRecordId() {
			return batchRecordId;
		}
		
		public String getFilePath() {
			return FILE_GENERATION_PATH;
		}
	}
	
	/**
	 * FOCAS document types
	 *
	 * @since	Aug 2, 2009
	 * @version	
	 * @author	Mario Rodrigues
	 */
	public enum FocasDocument implements PdfDocumentType {
		
		APPRENTICE_OJT_CERTIFICATION(DocumentData.class, "/Applications/FOCAS/1.0/Forms/Certification/appojt_certification.xdp", "formInputList"),
		FLIGT_CERTIFICATION(DocumentData.class, "/Applications/FOCAS/1.0/Forms/Certification/flight_certification.xdp", "formInputList"),
		CORRESPONDENCE_CERTIFICATION(DocumentData.class, "/Applications/FOCAS/1.0/Forms/Certification/correspondence_certification.xdp", "formInputList"),
		
		DELINQUENT_ON_THE_JOB(DocumentData.class, "/Applications/FOCAS/1.0/Forms/Delinquent/ojt_delinquent.xdp", "formInputList"),
		DELINQUENT_FLIGHT(DocumentData.class, "/Applications/FOCAS/1.0/Forms/Delinquent/flight_delinquent.xdp", "formInputList"),
		DELINQUENT_CORRESPONDENCE(DocumentData.class, "/Applications/FOCAS/1.0/Forms/Delinquent/correspondence_delinquent.xdp", "formInputList"),
		DELINQUENT_APPRENTICE(DocumentData.class, "/Applications/FOCAS/1.0/Forms/Delinquent/apprentice_delinquent.xdp", "formInputList"),

		B_LETTER(DocumentData.class, "/Applications/FOCAS/1.0/Forms/Collection/B_Letter.xdp", "formInputList"),
		C_LETTER(DocumentData.class, "/Applications/FOCAS/1.0/Forms/Collection/C_Letter.xdp", "formInputList"),
		D_LETTER(DocumentData.class, "/Applications/FOCAS/1.0/Forms/Collection/D_Letter.xdp", "formInputList");

		private final String FILE_GENERATION_PATH = "/FOCAS/";
		private DocumentData data;
		private final String template;
		private final String batchRecordId;

		private FocasDocument(Class<? extends DocumentData> data, String template, String batchRecordId) {
			//this.data = (DocumentData)data.getClass().getConstructor(new Class[]{}).newInstance(new Object[]{});
			this.template = template;
			this.batchRecordId = batchRecordId;
		}
		
		public DocumentData getData() {
			return data;
		}
		
		public String getTemplate() {
			return template;
		}

		public String getBatchRecordId() {
			return batchRecordId;
		}

		public String getFilePath() {
			return FILE_GENERATION_PATH;
		}

	}
	/**w
	 * RBPS document types
	 *
	 * @since	Nov 7, 2012
	 * @version	
	 * @author	Mario Rodrigues
	 */
	public enum RBPSDocument implements PdfDocumentType {
	
		DEPENDENCY_CLAIM_AWARD_APPROVAL_DENIAL(DocumentData.class, "/Applications/RBPS/1.0/Forms/RbpsLetter.xdp", "letter"),
		DEPENDENCY_CLAIM_AWARD_APPROVAL(DocumentData.class, "/Applications/RBPS/1.0/Forms/RbpsApprovalLetter.xdp", "letter"),
		DEPENDENCY_CLAIM_AWARD_DENIAL(DocumentData.class, "/Applications/RBPS/1.0/Forms/RbpsDenialLetter.xdp", "letter"),
		DEPENDENCY_CLAIM_MILITARY_AWARD_APPROVAL_DENIAL(DocumentData.class, "/Applications/RBPS/1.0/Forms/RbpsApprovalDenialMilitaryLetter.xdp","letter"),                
		DEPENDENCY_CLAIM_MILITARY_AWARD_APPROVAL(DocumentData.class, "/Applications/RBPS/1.0/Forms/RbpsApprovalMilitaryLetter.xdp","letter");             


		private final String FILE_GENERATION_PATH = "/RBPS/";
		private DocumentData data;
		private final String template;
		private final String batchRecordId;
		
		private RBPSDocument(Class<? extends DocumentData> data, String template, String batchRecordId) {			
			this.template = template;
			this.batchRecordId = batchRecordId;			
		}
				
		public DocumentData getData() {
			return data;
		}
		
		public String getTemplate() {
			return template;
		}

		public String getBatchRecordId() {
			return batchRecordId;
		}
		
		public String getFilePath() {
			return FILE_GENERATION_PATH;
		}
	}
		
}
