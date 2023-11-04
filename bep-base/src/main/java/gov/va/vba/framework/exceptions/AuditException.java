package gov.va.vba.framework.exceptions;

public class AuditException extends BaseRuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1960655759248711005L;
	public AuditException()
	{
		super();
	}
	public AuditException(String description)
	{
		super(description);
	}

}
