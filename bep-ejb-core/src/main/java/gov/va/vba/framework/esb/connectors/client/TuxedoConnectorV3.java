/*
 * TuxedoConnector.java
 *
 * Copyright 2005 U.S. Dept Of Veterans Affais. All rights reserved.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.esb.connectors.client;

import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.common.TuxedoErrorUtils;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.services.TuxedoException;
import gov.va.vba.framework.services.TuxedoServiceNotFoundWTCException;
import gov.va.vba.framework.services.ejb.TuxParams;

import java.nio.charset.StandardCharsets;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import weblogic.wtc.gwt.TuxedoConnection;
import weblogic.wtc.gwt.TuxedoConnectionFactory;
import weblogic.wtc.gwt.XmlFmlCnv;
import weblogic.wtc.jatmi.ApplicationToMonitorInterface;
import weblogic.wtc.jatmi.Ferror;
import weblogic.wtc.jatmi.Reply;
import weblogic.wtc.jatmi.TPException;
import weblogic.wtc.jatmi.TPReplyException;
import weblogic.wtc.jatmi.TypedBuffer;
import weblogic.wtc.jatmi.TypedFML;
import weblogic.wtc.jatmi.TypedFML32;

/**
 * Utility class that manages all Tuxedo connection and session handling. This
 * class is not intended to be used by application client code and is only
 * intended to be invoked by the framework since the implementation will change
 * once the VBA platform moves to a more SOA-based paradigm
 * 
 * @since Oct 14, 2005
 * @version
 * @author Mario Rodrigues <img src="doc-files/TuxedoConnector UML.gif">
 */
public class TuxedoConnectorV3 extends AbstractTuxedoConnector {

	private static Logger logger = Logger.getLogger(TuxedoConnectorV3.class
			.getName());
	
	static {
		Context ctx = null;
		try {
			logger.debug("Initializing Tuxedo Connection Factory...");
			ctx = new InitialContext();
			_tcf = (TuxedoConnectionFactory) ctx.lookup(TUXEDO_CONNECTION);
			
			initializeExternalFMLFldIdsMaxLengthHashMap();			
		} catch (Exception e) {
			logger.error("", e);
			throw new RuntimeException(e);
		} finally {
			if (ctx != null)
				try {
					ctx.close();
				} catch (NamingException e) {
					logger.error("", e);
				}
		}
	}
	
	public String processRequest(TuxParams tuxParams, AuditContext auditContext)
			throws TuxedoException {
		if (tuxParams != null)
			logger.debug("Executing TuxedoService: '"
					+ tuxParams.getTuxedoServiceName() + "'....");

		String response = "";
		try {
			if (auditContext.isRequestExternal())
				response = processExternalRequest(tuxParams, auditContext);
			else
				response = processInternalRequest(tuxParams, auditContext);
		} catch (TPReplyException tpr) {
			logger.error(TuxedoErrorUtils.combineStringAndCode(tpr, auditContext));
			throw new TuxedoException(tpr,
					TuxedoErrorUtils.getTuxedoErrorMessage(tpr, auditContext),
					TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		} catch (TPException tpex) {
			//Exception catch before the reply
			logger.error(TuxedoErrorUtils.combineStringAndCode(tpex, auditContext));
			if (TuxedoServiceNotFoundWTCException.isMessageMatchingTuxedoServiceNotFoundWTCException(tpex)) {
				throw new TuxedoServiceNotFoundWTCException(tpex.getMessage(), tpex);
			} else {
				throw new TuxedoException(tpex,
					TuxedoErrorUtils.getTuxedoErrorMessage(tpex, auditContext),
					TuxedoErrorUtils.getTuxedoErrorCode(tpex));
			}
		}
		catch (TuxedoException te) {
			logger.error("auditContext=" + auditContext, te);
			throw te;
		} catch (Exception e) {
			logger.error("auditContext=" + auditContext, e);
			throw new TuxedoException(e);
		}
		return response;
	}
	
	private String processExternalRequest(TuxParams tuxParams,
			AuditContext auditContext) throws TPException, TPReplyException,
			Ferror, TuxedoException {
		validateExternalFML16FldsTuxParams(tuxParams, auditContext);
		String response = "";
		TuxedoConnection con = null; // For now we get it via NEW until the
										// Factory works
		TypedFML inpBuff, outBuff = null;
		Reply reply = null;

		try {
			
			con = _tcf.getTuxedoConnection(); 
			
			// System.out.println("TuxedoConnector: VO data passed in:
			// "+vo.toString());
			inpBuff = new TypedFML(new ExternalUserFMLFieldTable());
			inpBuff.Fchg(ExternalUserFMLFieldTable.FML_STATION_TO_ROUTE_TO, 0,
					auditContext.getStationID().getBytes());
			inpBuff.Fchg(ExternalUserFMLFieldTable.FML_USERID, 0, auditContext
					.getUserId().getBytes());
			inpBuff.Fchg(ExternalUserFMLFieldTable.FML_REPLY_DEST_IND, 0, 'N');
			inpBuff.Fchg(ExternalUserFMLFieldTable.FML_PRIOR_SERVICE_NAME, 0,
					"NA".getBytes());
			inpBuff.Fchg(
					ExternalUserFMLFieldTable.FML_CLIENT_MODULE_NAME,
					0,
					constructTuxedoApplicationName(
							tuxParams.getTuxedoService(),
							tuxParams.getActualServiceName()).getBytes());
			inpBuff.Fchg(ExternalUserFMLFieldTable.FML_COMPUTER_NAME, 0,
					auditContext.getClientIPAddress().getBytes());
			inpBuff.Fchg(ExternalUserFMLFieldTable.FML_APPL_DATA, 0, tuxParams
					.getData().getBytes());
			inpBuff.Fchg(ExternalUserFMLFieldTable.APPNAME, 0,
					auditContext.getApplicationName());// has CSS hooks
			inpBuff.Fchg(ExternalUserFMLFieldTable.SVCNAME, 0, tuxParams
					.getActualServiceName().toLowerCase());
			inpBuff.Fchg(ExternalUserFMLFieldTable.EXTUID, 0,
					auditContext.getClientUniqueID());
			inpBuff.Fchg(ExternalUserFMLFieldTable.EXTKEY, 0,
					auditContext.getClientUniqueKey());
			logger.debug("Tux buffer before tpcall: " + toXML(inpBuff));
			reply = con.tpcall(PROXY_SERVICE, inpBuff,
					tuxParams.getTransactionType());
			logger.debug("Completed tpcall. TPUR Code: " + reply.gettpurcode());
			TuxedoErrorUtils.handle90x(reply);
			outBuff = (TypedFML) reply.getReplyBuffer();
			// scanFMLBuffer(outBuff);
			// toXML(outBuff);
			response = new String((byte[]) outBuff.Fget(
			InternalUserFMLFieldTable.FML_APPL_DATA, 0));
			
			/*response = new String((byte[]) outBuff.Fget(InternalUserFMLFieldTable.FML_APPL_DATA, 0));
			logger.debug("External  Original Response (Lenght= "+response.length()+"):<START>"+response+"<END>");
			
			byte arr[] = (byte[]) outBuff.Fget(InternalUserFMLFieldTable.FML_APPL_DATA, 0);
			int i;
			for (i = 0; i < arr.length && arr[i] != 0; i++)
				;
			response = new String(arr, 0, i, StandardCharsets.UTF_8);

			logger.debug("External Tuxedo Response (Lenght= <START>" + response.length() + "): " + response + "<END>");*/

		} finally {
			
			logger.debug("Closing TuxedoService Connection & InitialContext....");
			
			try {
                if (con != null)
                      con.tpterm();
          	} catch (Exception e) {
          		logger.error("Error closing tuxedo connection", e);
          	}

		}
		return response;
	}

	private String processInternalRequest(TuxParams tuxParams,
			AuditContext auditContext) throws TuxedoConnectorException,
			TuxedoException, Ferror, TPException {

		String response = "";

		TuxedoConnection con = null;
		try {
			TypedFML inpBuff, outBuff = null;
			Reply reply = null;

			inpBuff = new TypedFML(new InternalUserFMLFieldTable());
			inpBuff.Fchg(
					InternalUserFMLFieldTable.FML_CLIENT_MODULE_NAME,
					0,
					constructTuxedoApplicationName(
							tuxParams.getTuxedoService(),
							tuxParams.getActualServiceName()).getBytes());
			inpBuff.Fchg(InternalUserFMLFieldTable.FML_USERID, 0, auditContext
					.getUserId().getBytes());
			inpBuff.Fchg(InternalUserFMLFieldTable.FML_REPLY_DEST_IND, 0, 'N');
			inpBuff.Fchg(InternalUserFMLFieldTable.FML_COMPUTER_NAME, 0,
					auditContext.getClientIPAddress().getBytes());
			inpBuff.Fchg(InternalUserFMLFieldTable.FML_PRIOR_SERVICE_NAME, 0,
					"NA".getBytes());
			inpBuff.Fchg(InternalUserFMLFieldTable.FML_STATION_TO_ROUTE_TO, 0,
					auditContext.getStationID().getBytes());
			inpBuff.Fchg(InternalUserFMLFieldTable.FML_APPL_DATA, 0, tuxParams
					.getData().getBytes());
			con = _tcf.getTuxedoConnection();

			reply = con.tpcall(tuxParams.getActualServiceName().toLowerCase(),
					inpBuff, tuxParams.getTransactionType());
			TuxedoErrorUtils.handle90x(reply);

			outBuff = (TypedFML) reply.getReplyBuffer();
			response = new String((byte[]) outBuff.Fget(
			InternalUserFMLFieldTable.FML_APPL_DATA, 0));
			
			/*byte arr[] = (byte[]) outBuff.Fget(InternalUserFMLFieldTable.FML_APPL_DATA, 0);

			response = new String((byte[]) outBuff.Fget(InternalUserFMLFieldTable.FML_APPL_DATA, 0));
			logger.debug("Internal Original Response (Lenght= " + response.length() + "):<START>" + response + "<END>");

			int i;
			for (i = 0; i < arr.length && arr[i] != 0; i++)
				;
			response = new String(arr, 0, i, StandardCharsets.UTF_8);

			logger.debug("Internal Tuxedo Response (Lenght= " + response.length() + "):<START>" + response + "<END>");*/
	
		} finally {
			logger.debug("Closing TuxedoService Connection & InitialContext....");
            try {
                  if (con != null)
                        con.tpterm();
            } catch (Exception e) {
            	logger.error("Error closing tuxedo connection", e);
            }
		}
		return response;
	}

	/**
	 * Invoked by VETSNET `bit FML buffer services
	 * 
	 * @param serviceName
	 * @param buffer
	 * @param transaction
	 * @param auditContext
	 * @return
	 * @throws TuxedoException
	 * @throws
	 * @return
	 * @since Jun 22, 2012
	 * @author Mario Rodrigues
	 */

	public TypedBuffer execute(String serviceName, TypedBuffer buffer,
			AuditContext auditContext) throws TuxedoException {
		return execute(serviceName, buffer,
				ApplicationToMonitorInterface.TPNOTRAN, auditContext);
	}

	public TypedBuffer execute(String serviceName, TypedBuffer buffer,
			int transactionMode, AuditContext auditContext)
			throws TuxedoException {
		logger.debug("TuxedoConnectorV3: Executing TuxedoService: '" + serviceName + "'....");

		TypedBuffer reply = null;
		TypedFML buffer16 = null;
		TypedFML32 buffer32 = null;
		if (buffer instanceof TypedFML32)
			buffer32 = (TypedFML32) buffer;
		else if (buffer instanceof TypedFML)
			buffer16 = (TypedFML) buffer;
		try {
			if (auditContext.isRequestExternal() && buffer instanceof TypedFML32)
				reply = execExternalRequest32(serviceName, buffer32,
						transactionMode, auditContext);
			if (auditContext.isRequestExternal() && buffer instanceof TypedFML)
				reply = execExternalRequest16(serviceName, buffer16,
						transactionMode, auditContext);
			if (!auditContext.isRequestExternal() && buffer instanceof TypedFML32)
				reply = execInternalRequest32(serviceName, buffer32,
						transactionMode, auditContext);
			if (!auditContext.isRequestExternal() && buffer instanceof TypedFML)
				reply = execInternalRequest16(serviceName, buffer16,
						transactionMode, auditContext);
		} catch (TPReplyException tpr) {
			logger.error(TuxedoErrorUtils.combineStringAndCode(tpr, auditContext));
			throw new TuxedoException(tpr,
					TuxedoErrorUtils.getTuxedoErrorMessage(tpr,auditContext),
					TuxedoErrorUtils.getTuxedoErrorCode(tpr));
		} catch (TPException tpex) {
			//Exception catch before the reply
			logger.error(TuxedoErrorUtils.combineStringAndCode(tpex, auditContext));
			if (TuxedoServiceNotFoundWTCException.isMessageMatchingTuxedoServiceNotFoundWTCException(tpex)) {
				throw new TuxedoServiceNotFoundWTCException(tpex.getMessage(), tpex);
			} else {
				throw new TuxedoException(tpex,
					TuxedoErrorUtils.getTuxedoErrorMessage(tpex, auditContext),
					TuxedoErrorUtils.getTuxedoErrorCode(tpex));
			}
		} catch (TuxedoException te) {
			logger.error("auditContext=" + auditContext, te);
			throw te;
		} catch (Exception e) {
			if (auditContext.isExternal() && buffer instanceof TypedFML32) // external 32
			{
				logger.error("tpcall threw Exception: ", e);
				throw new TuxedoException(e, "Exception: " + e,
						TPException.TPESYSTEM);
			} else {
				logger.error("auditContext=" + auditContext, e);
				throw new TuxedoException(e);
			}
		}
		return reply;
	}

	/**
	 * Routes requests to a proxy service
	 * 
	 * @param serviceName
	 * @param applicationBuffer
	 * @param transaction
	 * @param auditContext
	 * @return
	 * @throws TPException
	 * @throws TPReplyException
	 * @throws Ferror
	 * @throws TuxedoException
	 * @throws
	 * @return
	 * @since Jun 22, 2012
	 * @author 281mrodr
	 */
	private TypedBuffer execExternalRequest32(String serviceName,
			TypedFML32 applicationBuffer, int transactionMode,
			AuditContext auditContext) throws TPException, TPReplyException,
			Ferror, TuxedoException {		
		validateExternalFML32Flds(serviceName, auditContext);
		TuxedoConnection con = null;
		TypedFML32 proxyBuff = null;
		Reply reply = null;
		TypedBuffer result = null;

		con = _tcf.getTuxedoConnection();
		try {
			if (applicationBuffer == null) {
				logger.debug("VETSNET applicaion32 buffer is null!");
			} else
				logger.debug("VN app buffer: " + applicationBuffer.toString()
						+ ". " + applicationBuffer.getFieldTables());
			logger.debug("AuditCtx values: " + auditContext);
			proxyBuff = new TypedFML32(new WTCfmlvba32());
			proxyBuff.Fchg(WTCfmlvba32.APPNAME32, 0,
					auditContext.getApplicationName());// "EBENEFITS"
			proxyBuff.Fchg(WTCfmlvba32.EXTKEY32, 0,
					auditContext.getClientUniqueKey());// "99999"
			proxyBuff.Fchg(WTCfmlvba32.EXTUID32, 0,
					auditContext.getClientUniqueID());// "10101"
			proxyBuff.Fchg(WTCfmlvba32.SVCNAME32, 0, serviceName);// "SVNTYPE"
			logger.debug("Inserting (into VN buff) following values from proxyBuff... ");
			logger.debug("Appname " + proxyBuff.Fget(WTCfmlvba32.APPNAME32, 0));
			logger.debug("extkey: " + proxyBuff.Fget(WTCfmlvba32.EXTKEY32, 0));
			logger.debug("extUid: " + proxyBuff.Fget(WTCfmlvba32.EXTUID32, 0));
			logger.debug("svcname: " + proxyBuff.Fget(WTCfmlvba32.SVCNAME32, 0));
			applicationBuffer.Fadd(WTCfmlvba32.APPNAME32,
					proxyBuff.Fget(WTCfmlvba32.APPNAME32, 0));
			applicationBuffer.Fadd(WTCfmlvba32.EXTKEY32,
					proxyBuff.Fget(WTCfmlvba32.EXTKEY32, 0));
			applicationBuffer.Fadd(WTCfmlvba32.EXTUID32,
					proxyBuff.Fget(WTCfmlvba32.EXTUID32, 0));
			applicationBuffer.Fadd(WTCfmlvba32.SVCNAME32,
					proxyBuff.Fget(WTCfmlvba32.SVCNAME32, 0));

			reply = con.tpcall(PROXY_SERVICE, applicationBuffer,
					transactionMode);
			logger.debug("Completed tpcall. TPUR Code: " + reply.gettpurcode());
			TuxedoErrorUtils.handle90x(reply);
			result = reply.getReplyBuffer();
		} finally {
			logger.debug("Closing TuxedoService Connection & InitialContext....");
			try {
                if (con != null)
                      con.tpterm();
          } catch (Exception e) {
        	logger.error("Error closing tuxedo connection", e);
          }
		}
		return result;
	}

	private TypedBuffer execInternalRequest16(String serviceName,
			TypedFML applicationBuffer, int transactionMode,
			AuditContext auditContext) throws TuxedoConnectorException,
			TuxedoException, TPException {
		return execRequest16(serviceName, applicationBuffer, transactionMode,
				auditContext);
	}

	private TypedBuffer execExternalRequest16(String serviceName,
			TypedFML applicationBuffer, int transactionMode,
			AuditContext auditContext) throws TuxedoConnectorException,
			TuxedoException, Ferror, TPException {
		validateExternalFML16Flds(serviceName, applicationBuffer, auditContext);
		TypedFML proxyBuff = null;
		if (applicationBuffer == null) {
			logger.debug("VETSNET applicaion16 buffer is null!");
		} else
			logger.debug("VN app buffer: " + applicationBuffer.toString()
					+ ". " + applicationBuffer.getFieldTables());
		logger.debug("AuditCtx values: " + auditContext);
		
		proxyBuff = new TypedFML(new ExternalUserFMLFieldTable());
		proxyBuff.Fchg(ExternalUserFMLFieldTable.APPNAME, 0,
				auditContext.getApplicationName());// "EBENEFITS"
		proxyBuff.Fchg(ExternalUserFMLFieldTable.FML_STATION_TO_ROUTE_TO, 0,
				auditContext.getStationID().getBytes());// "317"
		proxyBuff.Fchg(ExternalUserFMLFieldTable.FML_USERID, 0,
				auditContext.getUserId().getBytes());// "VBAJOHNDOE"
		proxyBuff.Fchg(ExternalUserFMLFieldTable.EXTKEY, 0,
				auditContext.getClientUniqueKey());// "99999"
		proxyBuff.Fchg(ExternalUserFMLFieldTable.EXTUID, 0,
				auditContext.getClientUniqueID());// "10101"
		proxyBuff.Fchg(ExternalUserFMLFieldTable.SVCNAME, 0, 
				serviceName.toLowerCase());// "SVNTYPE"
		proxyBuff.Fchg(ExternalUserFMLFieldTable.FML_COMPUTER_NAME, 0, 
				auditContext.getClientIPAddress().getBytes()); //192.168.1.11
		proxyBuff.Fchg(ExternalUserFMLFieldTable.FML_CLIENT_MODULE_NAME, 0, 
				constructTuxedoApplicationName(serviceName).getBytes()); //Weblogic-SVNTYPE
		// applicationBuffer.Fadd(0, proxyBuff);
		logger.debug("Inserting (into VN buff) following values from proxyBuff... ");
		logger.debug("Appname "
				+ proxyBuff.Fget(ExternalUserFMLFieldTable.APPNAME, 0));
		logger.debug("Station "
				+ proxyBuff.Fget(ExternalUserFMLFieldTable.FML_STATION_TO_ROUTE_TO, 0));
		logger.debug("UserId "
				+ proxyBuff.Fget(ExternalUserFMLFieldTable.FML_USERID, 0));
		logger.debug("extkey: "
				+ proxyBuff.Fget(ExternalUserFMLFieldTable.EXTKEY, 0));
		logger.debug("extUid: "
				+ proxyBuff.Fget(ExternalUserFMLFieldTable.EXTUID, 0));
		logger.debug("svcname: "
				+ proxyBuff.Fget(ExternalUserFMLFieldTable.SVCNAME, 0));
		logger.debug("Computer Name: "
				+ proxyBuff.Fget(ExternalUserFMLFieldTable.FML_COMPUTER_NAME, 0));
		logger.debug("Client Module Name: "
				+ proxyBuff.Fget(ExternalUserFMLFieldTable.FML_CLIENT_MODULE_NAME, 0));
		
		applicationBuffer.Fadd(ExternalUserFMLFieldTable.APPNAME,
				proxyBuff.Fget(ExternalUserFMLFieldTable.APPNAME, 0));
		applicationBuffer.Fadd(ExternalUserFMLFieldTable.FML_STATION_TO_ROUTE_TO,
				proxyBuff.Fget(ExternalUserFMLFieldTable.FML_STATION_TO_ROUTE_TO, 0));
		applicationBuffer.Fadd(ExternalUserFMLFieldTable.FML_USERID,
				proxyBuff.Fget(ExternalUserFMLFieldTable.FML_USERID, 0));
		applicationBuffer.Fadd(ExternalUserFMLFieldTable.EXTKEY,
				proxyBuff.Fget(ExternalUserFMLFieldTable.EXTKEY, 0));
		applicationBuffer.Fadd(ExternalUserFMLFieldTable.EXTUID,
				proxyBuff.Fget(ExternalUserFMLFieldTable.EXTUID, 0));
		applicationBuffer.Fadd(ExternalUserFMLFieldTable.SVCNAME,
				proxyBuff.Fget(ExternalUserFMLFieldTable.SVCNAME, 0));
		applicationBuffer.Fadd(ExternalUserFMLFieldTable.FML_COMPUTER_NAME,
				proxyBuff.Fget(ExternalUserFMLFieldTable.FML_COMPUTER_NAME, 0));
		applicationBuffer.Fadd(ExternalUserFMLFieldTable.FML_CLIENT_MODULE_NAME,
				proxyBuff.Fget(ExternalUserFMLFieldTable.FML_CLIENT_MODULE_NAME, 0));
		return execRequest16(PROXY_SERVICE, applicationBuffer, transactionMode,
				auditContext);
	}

	private TypedBuffer execRequest16(String serviceName,
			TypedFML applicationBuffer, int transactionMode,
			AuditContext auditContext) throws TuxedoConnectorException,
			TuxedoException, TPException {
		TuxedoConnection con = null;
		Reply reply = null;
		TypedBuffer result = null;
		try {
			logger.debug("Executing TuxedoService: '" + serviceName + "'....");
			con = _tcf.getTuxedoConnection();
			// TODO this should be enhanced to handle any
			// ApplicationToMonitorInterface constant passed in
			reply = con.tpcall(serviceName, applicationBuffer, transactionMode);
			TuxedoErrorUtils.handle90x(reply);
			result = reply.getReplyBuffer();
		} finally {
			logger.debug("Closing TuxedoService Connection & InitialContext....");
			try {
                if (con != null)
                      con.tpterm();
          } catch (Exception e) {
        	  logger.error("Error closing tuxedo connection", e);
          }
		}
		return result;
	}

	/**
	 * 
	 * @param tuxParams
	 * @param auditContext
	 * @return
	 * @throws TuxedoConnectorException
	 * @throws TuxedoException
	 * @throws
	 * @return
	 * @since Jun 22, 2012
	 * @author 281mrodr
	 * @throws TPException
	 */
	private TypedBuffer execInternalRequest32(String serviceName,
			TypedFML32 applicationBuffer, int transactionMode,
			AuditContext auditContext) throws TuxedoConnectorException,
			TuxedoException, TPException {

		TuxedoConnection con = null;
		Reply reply = null;
		TypedBuffer result = null;
		try {

			con = _tcf.getTuxedoConnection();
			logger.debug("Tux buffer32 before internal tpcall: "
					+ toXML32(applicationBuffer));
			logger.debug("Internal call AuditCtx values: " + auditContext);
			reply = con.tpcall(serviceName, applicationBuffer, transactionMode);
			TuxedoErrorUtils.handle90x(reply);
			result = reply.getReplyBuffer();
		} finally {
			try {
                if (con != null)
                      con.tpterm();
          } catch (Exception e) {
        	  logger.error("Error closing tuxedo connection", e);
          }
		}
		return result;
	}

	/**
	 * 
	 * 
	 * @see java.lang.Object#finalize()
	 */
	/* @Override
	protected void finalize() throws Throwable {

		super.finalize();
		// _tcf = null; causes NPE when garbage collected due to this object
		// being gc'd intermittently (from EJB refsbeing passivated??)
	}*/


	/**
	 * 
	 * @return
	 * @throws
	 * @return
	 * @since Oct 20, 2005
	 */
	private String toXML(TypedFML buff) {

		return new XmlFmlCnv().FMLtoXML(buff, true, true, true, true);
	}

	private String toXML32(TypedFML32 buff) {

		return new XmlFmlCnv().FML32toXML(buff, true, true, true, true);
	}

	/**
	 * 
	 * @throws
	 * @return
	 * @since Oct 14, 2005
	 */
	public static class ServiceReply {

		public byte[] FML_STATION_TO_ROUTE_TO;

		public char FML_REPLY_DEST_IND;

		public byte[] FML_CLIENT_MODULE_NAME;

		public byte[] FML_PRIOR_SERVICE_NAME;

		public byte[] FML_USERID;

		public byte[] FML_COMPUTER_NAME;

		public byte[] FML_APPL_DATA;
	}

}
