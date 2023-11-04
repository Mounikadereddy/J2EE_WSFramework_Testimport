package gov.va.vba.framework.common;

import gov.va.vba.framework.esb.connectors.client.ExternalUserFMLFieldTable;
import gov.va.vba.framework.esb.connectors.client.InternalUserFMLFieldTable;
import gov.va.vba.framework.esb.connectors.client.TuxedoConnectorException;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.services.TuxedoAppNameNotFoundException;
import gov.va.vba.framework.services.TuxedoException;
import gov.va.vba.framework.services.TuxedoServiceFoundButWrongAppException;
import gov.va.vba.framework.services.TuxedoServiceNotFoundException;
import weblogic.wtc.jatmi.Ferror;
import weblogic.wtc.jatmi.Reply;
import weblogic.wtc.jatmi.TPException;
import weblogic.wtc.jatmi.TPReplyException;
import weblogic.wtc.jatmi.TypedFML;

public class TuxedoErrorUtils {

	private static Logger logger = Logger.getLogger(TuxedoErrorUtils.class);
	
	public static String getTuxedoErrorMessage(TPReplyException tre) {
		return getTuxedoErrorMessage(tre,null);
	}
	

	public static String getTuxedoErrorMessage(TPReplyException tre, AuditContext auditContext) {
		String error = "unable to get tuxedo error";
		if (tre != null) {
			Reply reply = tre.getReplyRtn();
			TypedFML outBuff = null;
			if (reply != null) {
				outBuff = (TypedFML) reply.getReplyBuffer();
				try {
					if (auditContext==null || auditContext.isInternal())
					{
						error = new String((byte[]) outBuff.Fget(
								InternalUserFMLFieldTable.FML_APPL_DATA, 0));
					}
					else //external
					{
						error = new String((byte[]) outBuff.Fget(
								ExternalUserFMLFieldTable.FML_APPL_DATA, 0));
					}
				} catch (Ferror e) {
					logger.error(e);
				} catch (Throwable e) {
					logger.error(e);
				}
			}
		}
		return error;
	}
	
	public static String getTuxedoErrorMessage(TPException tpex, AuditContext auditContext) {
		String error = "Unable to get tuxedo error from TPException";
		if (tpex != null) {
			Reply reply = tpex.getReplyRtn();
			TypedFML outBuff = null;
			if (reply != null) {
				outBuff = (TypedFML) reply.getReplyBuffer();
				try {
					if (auditContext==null || auditContext.isInternal())
					{
						error = new String((byte[]) outBuff.Fget(
								InternalUserFMLFieldTable.FML_APPL_DATA, 0));
					}
					else //external
					{
						error = new String((byte[]) outBuff.Fget(
								ExternalUserFMLFieldTable.FML_APPL_DATA, 0));
					}
				} catch (Ferror e) {
					logger.error(e);
				} catch (Throwable e) {
					logger.error(e);
				}
			}
			else if (tpex.getMessage() != null && !tpex.getMessage().isEmpty()) {
				error = tpex.getMessage();
			}
		}
		return error;
	}

	public static int getTuxedoErrorCode(TPReplyException tre) {

		int errorCode = -1;
		if (tre != null)
			errorCode = tre.gettperrno();
		return errorCode;
	}
	
	public static int getTuxedoErrorCode(TPException tpex) {

		int errorCode = -1;
		if (tpex != null)
			errorCode = tpex.gettperrno();
		return errorCode;
	}

	public static void handleTuxedoError(TPReplyException tre, AuditContext auditContext) throws TuxedoConnectorException {
		logger.error(combineStringAndCode(tre, auditContext));
		throw new TuxedoConnectorException(tre,
				getTuxedoErrorMessage(tre, auditContext),
				getTuxedoErrorCode(tre));
	}
	
	public static String handleTuxedoError(TPReplyException tre) {
		//todo remove duplicate code
		String error = "unable to get tuxedo error";
		Reply reply = null;
		if (tre != null) {
			reply = tre.getReplyRtn();
			if (reply != null) {
				TypedFML outBuff = (TypedFML) reply.getReplyBuffer();
				try {
					error = new String((byte[]) outBuff.Fget(
							InternalUserFMLFieldTable.FML_APPL_DATA, 0));
				} catch (Ferror e) {
					logger.error(e);
				} catch (Throwable e) {
					logger.error(e);
				}
			}
		}
		logger.error(combineStringAndCode(tre, null));
		return error;
	}
	

	
	public static void handle90x(Reply reply) throws TuxedoException {
		if (reply.gettpurcode() == 901) {
			throw new TuxedoServiceFoundButWrongAppException();
		}
		if (reply.gettpurcode() == 902) {
			throw new TuxedoAppNameNotFoundException();
		}
		if (reply.gettpurcode() == 903) {
			throw new TuxedoServiceNotFoundException();
		}
	}

	public static String combineStringAndCode(TPReplyException tre, AuditContext auditContext)
	{
		return combineStringAndCode(getTuxedoErrorMessage(tre, auditContext), getTuxedoErrorCode(tre));
	}
	
	public static String combineStringAndCode(TPException tpex, AuditContext auditContext)
	{
		return combineStringAndCode(getTuxedoErrorMessage(tpex, auditContext), getTuxedoErrorCode(tpex));
	}

	public static String combineStringAndCode(String errorMessage, int code)
	{
		StringBuilder message = new StringBuilder(errorMessage);
		message.append(" (");
		message.append(code);
		message.append(")");
		return message.toString();
	}
	
}
