/*
 * the appname was not found
 */
package gov.va.vba.framework.services;

public class TuxedoAppNameNotFoundException extends TuxedoException {

	private static final long serialVersionUID = -1;
	public static final String TUXEDO_ERROR_MESAGE="TuxedoAppNameNotFound";
	public static final int TUXEDO_ERROR_CODE=902;

	public TuxedoAppNameNotFoundException() {
		super(null, TUXEDO_ERROR_MESAGE, TUXEDO_ERROR_CODE);
	}
}
