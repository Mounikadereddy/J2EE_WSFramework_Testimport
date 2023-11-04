/*
 * the service was not found
 */
package gov.va.vba.framework.services;

public class TuxedoServiceNotFoundException extends TuxedoException {

	private static final long serialVersionUID = -1;
	public static final String TUXEDO_ERROR_MESAGE="TuxedoServiceNameNotFound";
	public static final int TUXEDO_ERROR_CODE=903;

	public TuxedoServiceNotFoundException() {
		super(null, TUXEDO_ERROR_MESAGE, TUXEDO_ERROR_CODE);
	}
}
