package gov.va.vba.framework.esb.connectors.client;

import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.domain.vo.ServiceVO;
import gov.va.vba.framework.domain.vo.ServiceVO.TuxedoService;
import gov.va.vba.framework.services.TuxedoException;
import gov.va.vba.framework.services.ejb.TuxParams;

import java.util.HashMap;

import weblogic.wtc.gwt.TuxedoConnectionFactory;
import weblogic.wtc.jatmi.TypedFML;

public class AbstractTuxedoConnector {

	protected static final String TUXEDO_CONNECTION = "tuxedo.services.TuxedoConnection";
	protected static final String PROXY_SERVICE = "wtcsvccall";
	protected static TuxedoConnectionFactory _tcf;

	private static HashMap<Integer, Integer> externalFldIdsMaxLength = null;
	
	private static final String SERVICE_PREFIX = "WebLogic-";
	
	//These field sizes are only for external requests
	private static final int FML_APPNAME_MAX_LENGTH  = 30;
	private static final int FML_SVCNAME_MAX_LENGTH  = 15;
	private static final int FML_EXTUID_MAX_LENGTH  = 40;
	private static final int FML_EXTKEY_MAX_LENGTH  = 40;
	private static final int FML_COMPUTER_NAME_MAX_LENGTH  = 15;
	private static final int FML_CLIENT_MODULE_NAME_MAX_LENGTH  = 15;
	private static final int FML_APPL_DATA_MAX_LENGTH  = 32000;

	protected static void initializeExternalFMLFldIdsMaxLengthHashMap(){
		externalFldIdsMaxLength = new HashMap<Integer, Integer>();
		externalFldIdsMaxLength.put(ExternalUserFMLFieldTable.APPNAME, FML_APPNAME_MAX_LENGTH);
		externalFldIdsMaxLength.put(ExternalUserFMLFieldTable.SVCNAME, FML_SVCNAME_MAX_LENGTH);
		externalFldIdsMaxLength.put(ExternalUserFMLFieldTable.EXTUID, FML_EXTUID_MAX_LENGTH);
		externalFldIdsMaxLength.put(ExternalUserFMLFieldTable.EXTKEY, FML_EXTKEY_MAX_LENGTH);
		externalFldIdsMaxLength.put(ExternalUserFMLFieldTable.FML_COMPUTER_NAME, FML_COMPUTER_NAME_MAX_LENGTH);
		externalFldIdsMaxLength.put(ExternalUserFMLFieldTable.FML_CLIENT_MODULE_NAME, FML_CLIENT_MODULE_NAME_MAX_LENGTH);
		externalFldIdsMaxLength.put(ExternalUserFMLFieldTable.FML_APPL_DATA, FML_APPL_DATA_MAX_LENGTH);
	}
	

	/**
	 * Validate external field. TEXT VALUE. MAX SIZE
	 * @param fldId
	 * @param fldValue
	 * @throws TuxedoException
	 */
	private void validateExternalFMLFld(int fldId, String fldValue)throws TuxedoException{
		int maxLength = externalFldIdsMaxLength.get(fldId);
		ExternalUserFMLFieldTable externalUserFMLFieldTable = new ExternalUserFMLFieldTable();
		if ( fldValue == null || fldValue.isEmpty() || fldValue.length() > maxLength ){
			throw new TuxedoException( externalUserFMLFieldTable.Fldid_to_name(fldId) + "  cannot be null or empty, and length cannot be greater than  " + maxLength);
		}
	}
	
	/**
	 * Validate external field. BYTE VALUE. MAX SIZE
	 * @param fldId
	 * @param fldValue
	 * @throws TuxedoException
	 */
	private void validateExternalFMLFld(int fldId, byte[] fldValue)throws TuxedoException{
		int maxLength = externalFldIdsMaxLength.get(fldId);
		ExternalUserFMLFieldTable externalUserFMLFieldTable = new ExternalUserFMLFieldTable();
		if ( fldValue == null || fldValue.length == 0 || fldValue.length > maxLength ){
			throw new TuxedoException( externalUserFMLFieldTable.Fldid_to_name(fldId) + " cannot be null or empty, and length cannot be greater than  " + maxLength);
		}
	}
	
	
	/**
	 *  This method is validates the fields passed to WTCSVCCALL tuxedo service for external requests.
	 * This method is called from method processExternalRequest (when NOT needing to pass an FMLBuffer)
	 */
	protected void validateExternalFML16FldsTuxParams(TuxParams tuxParams, AuditContext auditContext) throws TuxedoException{		
		validateExternalFMLFld(ExternalUserFMLFieldTable.APPNAME, auditContext.getApplicationName());
		validateExternalFMLFld(ExternalUserFMLFieldTable.SVCNAME, tuxParams.getActualServiceName());
		validateExternalFMLFld(ExternalUserFMLFieldTable.EXTUID, auditContext.getClientUniqueID());
		validateExternalFMLFld(ExternalUserFMLFieldTable.EXTKEY, auditContext.getClientUniqueKey());
		validateExternalFMLFld(ExternalUserFMLFieldTable.FML_COMPUTER_NAME, auditContext.getClientIPAddress());
		validateExternalFMLFld(ExternalUserFMLFieldTable.FML_CLIENT_MODULE_NAME, constructTuxedoApplicationName(
				tuxParams.getTuxedoService(),
				tuxParams.getActualServiceName()));
		validateExternalFMLFld(ExternalUserFMLFieldTable.FML_APPL_DATA, tuxParams
				.getData());
	}
	
	/**
	 *  This method is validates the fields passed to WTCSVCCALL tuxedo service for external requests.
	 * This method is called from method processExternalRequest (when NOT needing to pass an FMLBuffer)
	 */
	protected void validateExternalFML16FldsServiceVO(ServiceVO service) throws TuxedoException{		
		validateExternalFMLFld(ExternalUserFMLFieldTable.APPNAME, service.getApplicationName());
		validateExternalFMLFld(ExternalUserFMLFieldTable.SVCNAME, service.getService().toString().toLowerCase());
		validateExternalFMLFld(ExternalUserFMLFieldTable.EXTUID, service.getExternalId());
		validateExternalFMLFld(ExternalUserFMLFieldTable.EXTKEY, service.getExternalKey());
		validateExternalFMLFld(ExternalUserFMLFieldTable.FML_COMPUTER_NAME, service.getClientIPAddress().getBytes());
		validateExternalFMLFld(ExternalUserFMLFieldTable.FML_CLIENT_MODULE_NAME, service.getServiceName().getBytes());
		validateExternalFMLFld(ExternalUserFMLFieldTable.FML_APPL_DATA, service.getData().getBytes());
	}
	

	/* This method is validates the fields passed to WTCSVCCALL tuxedo service for external requests for FML 32 bit buffer.
	 * This method is called from method execExternalRequest32 (when 32 bit FML buffer is passed)
	 */
	protected void validateExternalFML32Flds(String serviceName, AuditContext auditContext) throws TuxedoException{		
		validateExternalFMLFld(ExternalUserFMLFieldTable.APPNAME, auditContext.getApplicationName());
		validateExternalFMLFld(ExternalUserFMLFieldTable.SVCNAME, serviceName);
		validateExternalFMLFld(ExternalUserFMLFieldTable.EXTUID, auditContext.getClientUniqueID());
		validateExternalFMLFld(ExternalUserFMLFieldTable.EXTKEY, auditContext.getClientUniqueKey());
	}
	

	/**
	 *  This method is validates the fields passed to WTCSVCCALL tuxedo service for external requests for FML 16 bit buffer.
	 * This method is called from method execExternalRequest16 (when 16 bit FML buffer is passed)
	 */
	protected void validateExternalFML16Flds(String serviceName, TypedFML applicationBuffer, AuditContext auditContext) throws TuxedoException{		
		validateExternalFMLFld(ExternalUserFMLFieldTable.APPNAME, auditContext.getApplicationName());
		validateExternalFMLFld(ExternalUserFMLFieldTable.EXTUID, auditContext.getClientUniqueID());
		validateExternalFMLFld(ExternalUserFMLFieldTable.EXTKEY, auditContext.getClientUniqueKey());
		validateExternalFMLFld(ExternalUserFMLFieldTable.SVCNAME, serviceName);
		validateExternalFMLFld(ExternalUserFMLFieldTable.FML_COMPUTER_NAME, auditContext.getClientIPAddress());
		validateExternalFMLFld(ExternalUserFMLFieldTable.FML_CLIENT_MODULE_NAME, constructTuxedoApplicationName(serviceName));
		Object fmlApplData;
		
		try {
			fmlApplData = applicationBuffer.Fget(ExternalUserFMLFieldTable.FML_APPL_DATA, 0);
		} catch (Exception e) {
			throw new TuxedoException("Error while retrieving FML_APPL_DATA from applicationBuffer to validate");
		}
		//This seems wrong. Audit Context may not contain service name and be null
		validateExternalFMLFld(ExternalUserFMLFieldTable.FML_APPL_DATA, (byte[])fmlApplData);
	}
	
	protected String constructTuxedoApplicationName(TuxedoService tuxedoService,
			String tuxedoServiceName) {
		String result = "";
		if (tuxedoService != null)
			result = constructTuxedoApplicationName(tuxedoService);
		else
			result = constructTuxedoApplicationName("Unknown");
		return result;
	}

	protected String constructTuxedoApplicationName(TuxedoService tuxedoService) {
		return constructTuxedoApplicationName(tuxedoService.getClass()
				.getSimpleName());
	}

	protected String constructTuxedoApplicationName(String tuxedoServiceName) {
		StringBuilder strBldr = new StringBuilder(SERVICE_PREFIX);
		int len = 0;
		String svcStr = tuxedoServiceName;

		strBldr.append(svcStr.substring(
				0,
				(len = svcStr.toLowerCase().indexOf("service")) == -1 ? svcStr
						.length() : len));
		if (strBldr.length() > 15)
			strBldr.setLength(15);

		return strBldr.toString();
	}

}