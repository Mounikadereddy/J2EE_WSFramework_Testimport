package gov.va.vba.framework.services;

/*
 * svc found but not for requested app
 */
public class TuxedoServiceFoundButWrongAppException extends TuxedoException {

	private static final long serialVersionUID = -1;
	public static final String TUXEDO_ERROR_MESAGE="TuxedoServiceFoundButWrongApp";
	public static final int TUXEDO_ERROR_CODE=901;

	public TuxedoServiceFoundButWrongAppException() {
		super(null, TUXEDO_ERROR_MESAGE, TUXEDO_ERROR_CODE);
	}
}
