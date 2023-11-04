/*
 * TuxedoConnector.java
 *
 * Copyright 2005 U.S. Dept Of Veterans Affais. All rights reserved.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.esb.connectors.client;

import gov.va.vba.framework.common.TuxedoErrorUtils;
import gov.va.vba.framework.domain.vo.ServiceVO;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.services.TuxedoException;
import gov.va.vba.framework.services.TuxedoServiceFoundButWrongAppException;
import gov.va.vba.framework.services.TuxedoAppNameNotFoundException;
import gov.va.vba.framework.services.TuxedoServiceNotFoundException;
import gov.va.vba.framework.services.TuxedoServiceNotFoundWTCException;

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

/**
 * Utility class that manages all Tuxedo connection and session handling. This class is
 * not intended to be used by application client code and is only intended to be invoked
 * by the framework since the implementation will change once the VBA platform
 * moves to a more SOA-based paradigm 
 * 
 * @since 	Oct 14, 2005
 * @version
 * @author 	Mario Rodrigues
 * <img src="doc-files/TuxedoConnector UML.gif">
 */
public class TuxedoConnector extends AbstractTuxedoConnector {

	private static Logger logger=Logger.getLogger(TuxedoConnector.class.getName());
	
	static {
		Context ctx = null;
    	try {
    		logger.debug("Initializing Tuxedo Connection Factory...");
    		ctx = new InitialContext();
	    	_tcf = (TuxedoConnectionFactory)ctx.lookup(TUXEDO_CONNECTION);
	    	
	    	initializeExternalFMLFldIdsMaxLengthHashMap();
		}
		catch (Exception e) {
			logger.error("",e);
			throw new RuntimeException(e);
		}	
		finally {
			if (ctx != null)
				try {ctx.close();}
				catch (NamingException e) {
					logger.error("",e);
					}
		}
	}
	
	/**
	 * Uses an interface specifically designed for servicing external user based requests.
	 * @throws TuxedoException 
	 * 
	 * @throws
	 * @return
	 * @since Oct 14, 2005
	 * {@link Todo} make this generic. Then depending on vo type, instantiate LGYFML or other FML
	 */
	public String invokeTUXservice(ServiceVO vo) throws TPException, TPReplyException, Ferror, TuxedoException {
		return invokeTUXservice(vo, false);
	}
	public String invokeTUXservice(ServiceVO vo, boolean transaction) throws TPException, TPReplyException, Ferror, TuxedoException 
		{
			return invokeTUXservice(vo,getTransactionMode(transaction));
		}
	public String invokeTUXservice(ServiceVO vo, int transactionType) throws TPException, TPReplyException, Ferror, TuxedoException {

	    TuxedoConnection con = null;  // For now we get it via NEW until the Factory works
	    TypedFML inpBuff, outBuff = null;
	    Reply reply = null;

	    validateExternalFML16FldsServiceVO(vo);
	    
	    try {
		    con = _tcf.getTuxedoConnection();
		    //System.out.println("TuxedoConnector: VO data passed in: "+vo.toString());
		    inpBuff = new TypedFML(new ExternalUserFMLFieldTable());
		    inpBuff.Fchg(ExternalUserFMLFieldTable.FML_STATION_TO_ROUTE_TO, 0, vo.getStationId().getBytes());
		    inpBuff.Fchg(ExternalUserFMLFieldTable.FML_USERID, 0, vo.getUserId().getBytes());
		    inpBuff.Fchg(ExternalUserFMLFieldTable.FML_REPLY_DEST_IND, 0, 'N');
		    inpBuff.Fchg(ExternalUserFMLFieldTable.FML_PRIOR_SERVICE_NAME, 0, "NA".getBytes());
		    inpBuff.Fchg(ExternalUserFMLFieldTable.FML_CLIENT_MODULE_NAME, 0, vo.getServiceName().getBytes());
		    inpBuff.Fchg(ExternalUserFMLFieldTable.FML_COMPUTER_NAME, 0, vo.getClientIPAddress().getBytes());
		    inpBuff.Fchg(ExternalUserFMLFieldTable.FML_APPL_DATA, 0, vo.getData().getBytes());
		    inpBuff.Fchg(ExternalUserFMLFieldTable.APPNAME, 0, vo.getApplicationName());//has CSS hooks
		    inpBuff.Fchg(ExternalUserFMLFieldTable.SVCNAME, 0, vo.getService().toString().toLowerCase());
		    inpBuff.Fchg(ExternalUserFMLFieldTable.EXTUID, 0, vo.getExternalId());
		    inpBuff.Fchg(ExternalUserFMLFieldTable.EXTKEY, 0, vo.getExternalKey());		    
		    logger.debug("Tux buffer before tpcall: \n"+toXML(inpBuff));
	    	reply = con.tpcall(PROXY_SERVICE, inpBuff, transactionType);
	    	logger.debug("Completed tpcall. TPUR Code: "+reply.gettpurcode());
	    	if (reply.gettpurcode()==901)
	    	{
	    		throw new TuxedoException(null, TuxedoServiceFoundButWrongAppException.TUXEDO_ERROR_MESAGE, TuxedoServiceFoundButWrongAppException.TUXEDO_ERROR_CODE);
	    	}
	    	if (reply.gettpurcode()==902)
	    	{
	    		throw new TuxedoException(null, TuxedoAppNameNotFoundException.TUXEDO_ERROR_MESAGE, TuxedoAppNameNotFoundException.TUXEDO_ERROR_CODE);
	    	}
	    	if (reply.gettpurcode()==903)
	    	{
	    		throw new TuxedoException(null, TuxedoServiceNotFoundException.TUXEDO_ERROR_MESAGE, TuxedoServiceNotFoundException.TUXEDO_ERROR_CODE);
	    	}
		    outBuff = (TypedFML)reply.getReplyBuffer();		    
		    //scanFMLBuffer(outBuff);
		    //toXML(outBuff);		    		    
	    } 
	    catch (TuxedoException te)
	    {
	    	logger.error(te);
	    	throw te;
	    }
	    catch (TPReplyException tre) {	    	
	    	TuxedoErrorUtils.handleTuxedoError(tre);
	    	throw tre;
	    } 
	    catch (TPException te) {

			//Exception catch before the reply
	    	logger.error("tpcall threw TPException: " , te);
			if (TuxedoServiceNotFoundWTCException.isMessageMatchingTuxedoServiceNotFoundWTCException(te)) {
				throw new TuxedoServiceNotFoundWTCException(te.getMessage(), te);
			} else {
				throw te;
			}
		}
	    catch (Ferror fe) {	    	
	    	logger.error("Error putting data into the TypedFML buffer: ", fe);
	    	//fe.printStackTrace();	    	
	    	throw fe;
	    }
	    catch (Exception ee) {	    	
	    	logger.error("tpcall threw Exception: " , ee);
	    	//ee.printStackTrace();
	    	throw new TPException(TPException.TPESYSTEM, "Exception: " + ee);
	    } 
	    finally {
	    	logger.debug("\nClosing TuxedoService Connection & InitialContext....");
	    	try {
	    		if (con != null)con.tpterm();	    		
	    	}
	    	catch (Exception e) {
	    		logger.error("",e);
	    		//e.printStackTrace();
			}
	    }
	    return new String((byte[])outBuff.Fget(ExternalUserFMLFieldTable.FML_APPL_DATA, 0));
	}
		
	/**
	 * Generic method that executes Tuxedo services based on attributes of the Service VO. Used to execute services based on 
	 * internal/intranet user requests
	 * 
	 * @param	serviceVo
	 * @throws TuxedoException 
	 * @throws
	 * @return
	 * @since Oct 14, 2005
	 */
	public String execute(ServiceVO serviceVo) throws TuxedoConnectorException, TuxedoException {
		return execute(serviceVo, false);
	}
	public String execute(ServiceVO serviceVo, boolean transaction) throws TuxedoConnectorException, TuxedoException {
		return execute(serviceVo,getTransactionMode(transaction));
	}
	public String execute(ServiceVO serviceVo, int transactionType) throws TuxedoConnectorException, TuxedoException {

	    TuxedoConnection con = null; 
	    TypedFML inpBuff, outBuff = null;	    
	    Reply reply = null; 

	    try {
		    inpBuff = new TypedFML(new InternalUserFMLFieldTable());	
		    //System.out.println("Data Passed in to TuxedoConnector: "+serviceVo.toString());
		    inpBuff.Fchg(InternalUserFMLFieldTable.FML_CLIENT_MODULE_NAME, 0, serviceVo.getApplicationName().getBytes());		    
		    inpBuff.Fchg(InternalUserFMLFieldTable.FML_USERID, 0, serviceVo.getUserId().getBytes());		    
		    inpBuff.Fchg(InternalUserFMLFieldTable.FML_REPLY_DEST_IND, 0, 'N');		    
		    inpBuff.Fchg(InternalUserFMLFieldTable.FML_COMPUTER_NAME, 0, serviceVo.getClientIPAddress().getBytes());
		    inpBuff.Fchg(InternalUserFMLFieldTable.FML_PRIOR_SERVICE_NAME, 0, "NA".getBytes());	
		    inpBuff.Fchg(InternalUserFMLFieldTable.FML_STATION_TO_ROUTE_TO, 0, serviceVo.getStationId().getBytes());
		    inpBuff.Fchg(InternalUserFMLFieldTable.FML_APPL_DATA, 0, serviceVo.getData().getBytes());
		    con = _tcf.getTuxedoConnection();
			
	    	reply = con.tpcall(serviceVo.getService().toString().toLowerCase(), inpBuff, transactionType);
	    	if (reply.gettpurcode()==901)
	    	{
	    		throw new TuxedoException(null, TuxedoServiceFoundButWrongAppException.TUXEDO_ERROR_MESAGE, TuxedoServiceFoundButWrongAppException.TUXEDO_ERROR_CODE);
	    	}
	    	if (reply.gettpurcode()==902)
	    	{
	    		//throw new TuxedoAppNameNotFoundException();
	    		throw new TuxedoException(null, TuxedoAppNameNotFoundException.TUXEDO_ERROR_MESAGE, TuxedoAppNameNotFoundException.TUXEDO_ERROR_CODE);
	    	}
	    	if (reply.gettpurcode()==903)
	    	{
	    		//throw new TuxedoServiceNotFoundException();
	    		throw new TuxedoException(null, TuxedoServiceNotFoundException.TUXEDO_ERROR_MESAGE, TuxedoServiceNotFoundException.TUXEDO_ERROR_CODE);
	    	}


		    outBuff = (TypedFML)reply.getReplyBuffer();   		    
//		    Iterator iter = outBuff.Fiterator(); 
//		    while(iter.hasNext()) { 
//		    	Map.Entry entry = (Map.Entry)iter.next(); 
//		    	FmlKey fmlkey = (FmlKey) entry.getKey(); 
//		    	switch(outBuff.Fldtype(fmlkey.get_fldid())) { 
//		    		case TypedFML.FLD_SHORT: 
//		    			Short value_short = (Short) entry.getValue();
//		    			System.out.println("short val is: "+value_short);
//		    			break;
//		    		default:
//		    			System.out.println(fmlkey);
//		    		
//		    	} 
//		    } 
//		    System.out.println((byte[])outBuff.Fget(InternalUserFMLFieldTable.FML_STATION_TO_ROUTE_TO, 0));
//		    System.out.println((byte[])outBuff.Fget(InternalUserFMLFieldTable.FML_APPL_DATA, 0));
//		    System.out.println((byte[])outBuff.Fget(InternalUserFMLFieldTable.FML_APPL_DATA, 1));
//		    System.out.println((byte[])outBuff.Fget(InternalUserFMLFieldTable.FML_APPL_DATA, 2));
		    return new String((byte[])outBuff.Fget(InternalUserFMLFieldTable.FML_APPL_DATA, 0));
	    } 

	    catch (TuxedoException te)
	    {
	    	logger.error(te);
	    	throw te;
	    }
	    catch (TPReplyException tre) {	    	
	    	TuxedoErrorUtils.handleTuxedoError(tre);
	    	throw new TuxedoConnectorException(tre, TuxedoErrorUtils.getTuxedoErrorMessage(tre),TuxedoErrorUtils.getTuxedoErrorCode(tre));
	    } 
	    catch (TPException tpe) {

			//Exception catch before the reply
	    	logger.error("tpcall threw TPException: " , tpe);
			if (TuxedoServiceNotFoundWTCException.isMessageMatchingTuxedoServiceNotFoundWTCException(tpe)) {
				throw new TuxedoServiceNotFoundWTCException(tpe.getMessage(), tpe);
			} else {
				throw new TuxedoException(tpe);
			}
		}
	    catch (Exception ee) {
	    	logger.error("",ee);
	    	throw new TuxedoConnectorException(ee);
	    } 
	    finally {
	    	logger.debug("\nClosing TuxedoService Connection & InitialContext....");
	    	try {
	    		if (con != null)con.tpterm();    
	    	}
	    	catch (Exception e) {
	    		throw new TuxedoConnectorException(e);
			}
	    }	    
	}
	
	
	public TypedBuffer execute(String serviceName, TypedBuffer buffer) throws TuxedoConnectorException, TuxedoException {
		TypedBuffer typedBuffer=execute(serviceName, buffer, false);
		return typedBuffer;
	}

	/**
	 * Generic method that executes Tuxedo services based on attributes of the Service VO. Used to execute services based on 
	 * internal/intranet user requests
	 * 
	 * @param	String serviceName
	 * @throws TuxedoException 
	 * @throws
	 * @return
	 * @since  
	 */
	public TypedBuffer execute(String serviceName, TypedBuffer buffer, boolean transaction) throws TuxedoConnectorException, TuxedoException {
		return execute(serviceName, buffer, getTransactionMode(transaction));
	}
	public TypedBuffer execute(String serviceName, TypedBuffer buffer, int transactionType) throws TuxedoConnectorException, TuxedoException {

	    TuxedoConnection con = null; 	    
	    Reply reply = null; 

	    try {
	    	logger.debug("\nTuxedoConnector: Executing TuxedoService: '"+serviceName+"'....");
		    con = _tcf.getTuxedoConnection();
		    // TODO this should be enhanced to handle any ApplicationToMonitorInterface constant passed in
		    reply = con.tpcall(serviceName, buffer, transactionType);
	    	if (reply.gettpurcode()==901)
	    	{
	    		throw new TuxedoException(null, TuxedoServiceFoundButWrongAppException.TUXEDO_ERROR_MESAGE, TuxedoServiceFoundButWrongAppException.TUXEDO_ERROR_CODE);
	    	}
	    	if (reply.gettpurcode()==902)
	    	{
	    		throw new TuxedoException(null, TuxedoAppNameNotFoundException.TUXEDO_ERROR_MESAGE, TuxedoAppNameNotFoundException.TUXEDO_ERROR_CODE);
	    	}
	    	if (reply.gettpurcode()==903)
	    	{
	    		throw new TuxedoException(null, TuxedoServiceNotFoundException.TUXEDO_ERROR_MESAGE, TuxedoServiceNotFoundException.TUXEDO_ERROR_CODE);
	    	}

		    return reply.getReplyBuffer();
	    } 
	    

	    catch (TuxedoException te)
	    {
	    	logger.error(te);
	    	throw te;
	    }
	    catch (TPReplyException tre) {	    	
	    	TuxedoErrorUtils.handleTuxedoError(tre);
	    	throw new TuxedoConnectorException(tre, TuxedoErrorUtils.getTuxedoErrorMessage(tre),TuxedoErrorUtils.getTuxedoErrorCode(tre));
	    } 
	    catch (TPException tpe) {

			//Exception catch before the reply
	    	logger.error("tpcall threw TPException: " , tpe);
			if (TuxedoServiceNotFoundWTCException.isMessageMatchingTuxedoServiceNotFoundWTCException(tpe)) {
				throw new TuxedoServiceNotFoundWTCException(tpe.getMessage(), tpe);
			} else {
				throw new TuxedoException(tpe);
			}
		}
	    catch (Exception ee) {
	    	//ee.printStackTrace();
	    	logger.error("",ee);
	    	throw new TuxedoConnectorException(ee);
	    } 
	    finally {
	    	logger.debug("Closing TuxedoService Connection & InitialContext....");
	    	try {
	    		if (con != null)con.tpterm();    
	    	}
	    	catch (Exception e) {
	    		throw new TuxedoConnectorException(e);
			}
	    }	    
	}
	
	/**
	 * 
	 * 
	 * @see java.lang.Object#finalize()
	 */
	/* @Override
	protected void finalize() throws Throwable {
		super.finalize();
		//_tcf = null; causes NPE when garbage collected due to this object being gc'd intermittently (from EJB refsbeing passivated??) 
	}*/

	/**
	 * 
	 * @return	
	 * @throws	
	 * @return	
	 * @since	Oct 20, 2005
	 */
	private String toXML(TypedFML buff) {
		
		return new XmlFmlCnv().FMLtoXML(buff, true, true, true, true);
	}
	
	/**
	 * 	
	 * @throws	
	 * @return	
	 * @since	Oct 14, 2005
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
   
    
    private int getTransactionMode(boolean transaction)
   {
	   int transactionMode=0;
	   if (transaction)
		   transactionMode=ApplicationToMonitorInterface.TPNOAUTH;
	   else
		   transactionMode=ApplicationToMonitorInterface.TPNOTRAN;
	   return transactionMode;
   }
   
}
