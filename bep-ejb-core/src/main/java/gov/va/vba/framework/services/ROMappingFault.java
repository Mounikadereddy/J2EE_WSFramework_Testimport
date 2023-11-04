package gov.va.vba.framework.services;

import java.io.Serializable;

public class ROMappingFault implements Serializable {
	
	private static final long serialVersionUID = 6591234734690568974L;
	private String message;
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String value) {
		this.message = value;
	}
	
}
