package gov.va.vba.framework.services;

import gov.va.vba.framework.exceptions.BaseException;

public class ROMappingException extends BaseException {
	
	private static final long serialVersionUID = 7238945739035893475L;
	private ROMappingFault roFault;
	
	public ROMappingException(String message, ROMappingFault roFault) {
		super(message);
		this.roFault = roFault;
	}

	public ROMappingException(String message, ROMappingFault roFault, Throwable cause) {
		super(message);
		this.roFault = roFault;
	}
	
	public ROMappingFault getFaultInfo() {
		return roFault;
	}
	
}
