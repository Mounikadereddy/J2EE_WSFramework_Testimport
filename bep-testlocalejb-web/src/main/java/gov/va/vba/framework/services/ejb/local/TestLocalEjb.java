package gov.va.vba.framework.services.ejb.local;


import java.util.HashMap;
import java.util.Properties;

import gov.va.vba.benefits.arch.fml.fldtbl.ArchTbl;
import gov.va.vba.benefits.arch.fml.fldtbl.VNTbl;
import gov.va.vba.framework.services.ejb.TuxParams;

import javax.annotation.Resource;
import javax.jws.*;
import javax.jws.soap.SOAPBinding;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.soap.SOAPFaultException;
import javax.xml.ws.BindingType;
import javax.xml.ws.WebServiceContext;

import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.services.TuxedoServiceV3;
import gov.va.vba.framework.services.VeteranSessionRemoteV2;
import gov.va.vba.framework.services.VeteranSessionV2;
import gov.va.vba.framework.services.CommonSecurityServiceLocalV2;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.benefits.arch.fml.fldtbl.ArchTbl;
import gov.va.vba.benefits.arch.fml.fldtbl.VNTbl;
import weblogic.rmi.extensions.PortableRemoteObject;
import weblogic.wtc.jatmi.FldTbl;
import weblogic.wtc.jatmi.TypedFML32;
import weblogic.wtc.jatmi.TypedFML;
import weblogic.wtc.jatmi.TypedBuffer;
import gov.va.vba.framework.domain.entities.Person;
import gov.va.vba.framework.esb.transformers.TuxedoSecurityProfile;
import gov.va.vba.framework.esb.transformers.TuxedoSecurityProfile.Function;
import gov.va.vba.framework.domain.vo.ServiceVO;
import gov.va.vba.framework.esb.connectors.client.ExternalUserFMLFieldTable;
import gov.va.vba.framework.esb.connectors.client.InternalUserFMLFieldTable;
import gov.va.vba.framework.esb.connectors.client.WTCfmlvba32;


@WebService

@SOAPBinding(style=SOAPBinding.Style.DOCUMENT,
			 use=SOAPBinding.Use.LITERAL,
			 parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)
@BindingType(value = javax.xml.ws.soap.SOAPBinding.SOAP11HTTP_BINDING)
public class TestLocalEjb { 
	
	@WebMethod(action="Hello")		
	public String hello() {
		return "Hello";
	}	
	

	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceExecExternal32Success() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();
    	    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	auditCtx.setClientUniqueID("x281MARIO");
	    	auditCtx.setClientUniqueKey("777777");
	    	auditCtx.setTuxedoServiceName("SVNTYPE");
	    	auditCtx.setForceExternal(true);
	    	
	    	
	    	TypedFML32 fml32 = new TypedFML32(new ExternalUserFMLFieldTable());
            System.out.println("Executing service....");
            TypedFML32 outBuff = (TypedFML32)tuxedoService.execute("SVNTYPE", fml32, false, auditCtx, null);
            if (outBuff == null) {
            	message = "Returned FML32 buff is null. Exiting....";
            }
	    	System.out.println("Response: "+outBuff);
	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceExecExternal32AppNameException() {		
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();
    	     	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITSEBENEFITSEBENEFITSEBENEFITSEBENEFITSEBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	auditCtx.setClientUniqueID("x281MARIO");
	    	auditCtx.setClientUniqueKey("777777");
	    	auditCtx.setTuxedoServiceName("SVNTYPE");
	    	auditCtx.setForceExternal(true);
	    		    	    	            
            TypedFML32 fml32 = new TypedFML32(new ExternalUserFMLFieldTable());
            System.out.println("Executing service....");
            TypedFML32 outBuff = (TypedFML32)tuxedoService.execute("SVNTYPE", fml32, false, auditCtx, null);
            if (outBuff == null) {
            	message = "Returned FML32 buff is null. Exiting....";
            }
	    	System.out.println("Response: "+outBuff);	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceExecExternal32ExtUidException() {		
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();
    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	auditCtx.setClientUniqueID("x281MARIOx281MARIOx281MARIOx281MARIOx2812");
	    	auditCtx.setClientUniqueKey("777777");
	    	auditCtx.setTuxedoServiceName("SVNTYPE");
	    	auditCtx.setForceExternal(true);
	    	
	    	
	    	TypedFML32 fml32 = new TypedFML32(new ExternalUserFMLFieldTable());
            System.out.println("Executing service....");
            TypedFML32 outBuff = (TypedFML32)tuxedoService.execute("SVNTYPE", fml32, false, auditCtx, null);
            if (outBuff == null) {
            	message = "Returned FML32 buff is null. Exiting....";
            }
	    	System.out.println("Response: "+outBuff);
	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceExecExternal32ExtKeyException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	auditCtx.setClientUniqueID("x281MARIO");
	    	auditCtx.setClientUniqueKey("777777777777777777777777777777777777777777");
	    	auditCtx.setTuxedoServiceName("SVNTYPE");
	    	auditCtx.setForceExternal(true);	    	
	    	
	    	TypedFML32 fml32 = new TypedFML32(new ExternalUserFMLFieldTable());
            System.out.println("Executing service....");
            TypedFML32 outBuff = (TypedFML32)tuxedoService.execute("SVNTYPE", fml32, false, auditCtx, null);
            if (outBuff == null) {
            	message = "Returned FML32 buff is null. Exiting....";
            }
	    	System.out.println("Response: "+outBuff);	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceExecExternal32SvcException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();    	 
    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	auditCtx.setClientUniqueID("x281MARIO");
	    	auditCtx.setClientUniqueKey("777777");
	    	auditCtx.setTuxedoServiceName("SVNTYPESVNTYPESV");
	    	auditCtx.setForceExternal(true);
	    		    	
	    	TypedFML32 fml32 = new TypedFML32(new ExternalUserFMLFieldTable());
            System.out.println("Executing service....");
            TypedFML32 outBuff = (TypedFML32)tuxedoService.execute("SVNTYPESVNTYPESV", fml32, false, auditCtx, null);
            if (outBuff == null) {
            	message = "Returned FML32 buff is null. Exiting....";
            }
	    	System.out.println("Response: "+outBuff);	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceExecInternal32ComputerNameException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	//auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	//auditCtx.setClientUniqueID("x281MARIO");
	    	//auditCtx.setClientUniqueKey("777777");
	    	auditCtx.setTuxedoServiceName("SVNTYPE");
	    		    	
	    	TypedFML32 fml32 = new TypedFML32(new ExternalUserFMLFieldTable());
            System.out.println("Executing service....");
            TypedFML32 outBuff = (TypedFML32)tuxedoService.execute("SVNTYPE", fml32, false, auditCtx, null);
            if (outBuff == null) {
            	message = "Returned FML32 buff is null. Exiting....";
            }
	    	System.out.println("Response: "+outBuff);
	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceExecInternal32ClientModuleException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	//auditCtx.setClientUniqueID("x281MARIO");
	    	//auditCtx.setClientUniqueKey("777777");
	    	auditCtx.setTuxedoServiceName("");
	    	           
	    	TypedFML32 fml32 = new TypedFML32(new InternalUserFMLFieldTable());
            System.out.println("Executing service....");
            TypedFML32 outBuff = (TypedFML32)tuxedoService.execute("SVNTYPE", fml32, false, auditCtx, null);
            if (outBuff == null) {
            	message = "Returned FML32 buff is null. Exiting....";
            }
	    	System.out.println("Response: "+outBuff);
	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	
	// Failing test case
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceExecInternal32AppDataException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	//auditCtx.setClientUniqueID("x281MARIO");
	    	//auditCtx.setClientUniqueKey("777777");
	    	auditCtx.setTuxedoServiceName("SVNTYPE");
	    	
	    	TypedFML32 fml32 = new TypedFML32(new InternalUserFMLFieldTable());
	    	fml32.Fadd(InternalUserFMLFieldTable.FML_APPL_DATA, "vbms435354534345345354354534534534344vbms435354534345345354354534534534344".getBytes());	    	
            System.out.println("Executing service....");
            TypedFML32 outBuff = (TypedFML32)tuxedoService.execute("SVNTYPE", fml32, false, auditCtx, null);
	    	System.out.println("Response: "+outBuff);
	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceExecExternal16AppNameException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITSEBENEFITSEBENEFITSEBENEFITSEBENEFITSEBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	auditCtx.setClientUniqueID("x281MARIO");
	    	auditCtx.setClientUniqueKey("777777");
	    	auditCtx.setTuxedoServiceName("SVNTYPE");
	    	auditCtx.setForceExternal(true);	    	

	    	TypedFML proxyBuff = new TypedFML(new ExternalUserFMLFieldTable());
	    	proxyBuff.Fchg(ExternalUserFMLFieldTable.FML_APPL_DATA, 0, "vbms435354534345345354354534534534344vbms435354534345345354354534534534344".getBytes());	    	
	    	TypedBuffer outBuff = (TypedBuffer)tuxedoService.execute("SVNTYPE", proxyBuff, 0, auditCtx, null);
            if (outBuff == null) {
            	message = "Returned buff is null. Exiting....";
            }
	    	System.out.println("Response: "+outBuff);	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceExecExternal16ExtUidException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	auditCtx.setClientUniqueID("x281MARIOx281MARIOx281MARIOx281MARIOx2812");
	    	auditCtx.setClientUniqueKey("777777");
	    	auditCtx.setTuxedoServiceName("SVNTYPE");
	    	auditCtx.setForceExternal(true);
	    	    	
	    	TypedFML proxyBuff = new TypedFML(new ExternalUserFMLFieldTable());	    //	proxyBuff.Fchg(ExternalUserFMLFieldTable.APPNAME, 0, "vbms435354534345345354354534534534344vbms435354534345345354354534534534344");
	    	proxyBuff.Fchg(ExternalUserFMLFieldTable.FML_APPL_DATA, 0, "vbms435354534345345354354534534534344vbms435354534345345354354534534534344".getBytes());	    	
	    	TypedBuffer outBuff = (TypedBuffer)tuxedoService.execute("SVNTYPE", proxyBuff, 0, auditCtx, null);
            if (outBuff == null) {
            	message = "Returned FML32 buff is null. Exiting....";
            }
	    	System.out.println("Response: "+outBuff);
	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceExecExternal16ExtKeyException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	auditCtx.setClientUniqueID("x281MARIO");
	    	auditCtx.setClientUniqueKey("777777777777777777777777777777777777777777");
	    	auditCtx.setTuxedoServiceName("SVNTYPE");
	    	auditCtx.setForceExternal(true);	    	

	    	TypedFML proxyBuff = new TypedFML(new ExternalUserFMLFieldTable());
	    	proxyBuff.Fchg(ExternalUserFMLFieldTable.FML_APPL_DATA, 0, "vbms435354534345345354354534534534344vbms435354534345345354354534534534344".getBytes());	    	
	    	TypedBuffer outBuff = (TypedBuffer)tuxedoService.execute("SVNTYPE", proxyBuff, 0, auditCtx, null);
            if (outBuff == null) {
            	message = "Returned FML32 buff is null. Exiting....";
            }
	    	System.out.println("Response: "+outBuff);
	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceExecExternal16SvcException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	auditCtx.setClientUniqueID("x281MARIO");
	    	auditCtx.setClientUniqueKey("777777");
	    	auditCtx.setTuxedoServiceName("SVNTYPE");
	    	auditCtx.setForceExternal(true);
	    		    
	    	TypedFML proxyBuff = new TypedFML(new ExternalUserFMLFieldTable());
	    	proxyBuff.Fchg(ExternalUserFMLFieldTable.FML_APPL_DATA, 0, "vbms435354534345345354354534534534344vbms435354534345345354354534534534344".getBytes());	    	
	    	TypedBuffer outBuff = (TypedBuffer)tuxedoService.execute("", proxyBuff, 0, auditCtx, null);
            if (outBuff == null) {
            	message = "Returned FML32 buff is null. Exiting....";
            }
	    	System.out.println("Response: "+outBuff);
	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	

	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceExecExternal16ComputerNameException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.111.111.1001");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	auditCtx.setClientUniqueID("x281MARIO");
	    	auditCtx.setClientUniqueKey("777777");
	    	auditCtx.setTuxedoServiceName("SVNTYPESVNTYPESVNTYPESVNTYPESVNTYPE");
	    	auditCtx.setForceExternal(true);
	    		    	
	    	TypedFML proxyBuff = new TypedFML(new ExternalUserFMLFieldTable());
	    	proxyBuff.Fchg(ExternalUserFMLFieldTable.FML_APPL_DATA, 0, "vbms435354534345345354354534534534344vbms435354534345345354354534534534344".getBytes());	    	
	    	TypedBuffer outBuff = (TypedBuffer)tuxedoService.execute("SVNTYPE", proxyBuff, 0, auditCtx, null);
            if (outBuff == null) {
            	message = "Returned FML32 buff is null. Exiting....";
            }
	    	System.out.println("Response: "+outBuff);	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceExecExternal16ClientModuleException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	auditCtx.setClientUniqueID("x281MARIO");
	    	auditCtx.setClientUniqueKey("777777");
	    	auditCtx.setTuxedoServiceName("");
	    	auditCtx.setForceExternal(true);
	    		    	
	    	TypedFML proxyBuff = new TypedFML(new ExternalUserFMLFieldTable());
	    	proxyBuff.Fchg(ExternalUserFMLFieldTable.FML_APPL_DATA, 0, "vbms435354534345345354354534534534344vbms435354534345345354354534534534344".getBytes());	    	
	    	TypedBuffer outBuff = (TypedBuffer)tuxedoService.execute("SVNTYPE", proxyBuff, 0, auditCtx, null);
            if (outBuff == null) {
            	message = "Returned FML32 buff is null. Exiting....";
            }
	    	System.out.println("Response: "+outBuff);	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceExecExternal16Success() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	auditCtx.setClientUniqueID("x281MARIO");
	    	auditCtx.setClientUniqueKey("777777");
	    	auditCtx.setTuxedoServiceName("SVNTYPE");
	    	auditCtx.setForceExternal(true);
	    		    
	    	TypedFML proxyBuff = new TypedFML(new ExternalUserFMLFieldTable());
	    	proxyBuff.Fchg(ExternalUserFMLFieldTable.FML_APPL_DATA, 0, "vbms".getBytes());	    	
	    	TypedBuffer outBuff = (TypedBuffer)tuxedoService.execute("SVNTYPE", proxyBuff, 0, auditCtx, null);
            if (outBuff == null) {
            	message = "Returned FML32 buff is null. Exiting....";
            }
	    	System.out.println("Response: "+outBuff);	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceExecExternal16ApplDataException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	auditCtx.setClientUniqueID("x281MARIO");
	    	auditCtx.setClientUniqueKey("777777");
	    	auditCtx.setTuxedoServiceName("");
	    	auditCtx.setForceExternal(true);
	    		    	
	    	TypedFML proxyBuff = new TypedFML(new ExternalUserFMLFieldTable());
	    	proxyBuff.Fchg(ExternalUserFMLFieldTable.FML_APPL_DATA, 0, getTestApplData().getBytes());	    	
	    	TypedBuffer outBuff = (TypedBuffer)tuxedoService.execute("SVNTYPE", proxyBuff, 0, auditCtx, null);
            if (outBuff == null) {
            	message = "Returned FML32 buff is null. Exiting....";
            }
	    	System.out.println("Response: "+outBuff);	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceExecInternal16ComputerNameException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	//auditCtx.setClientUniqueID("x281MARIO");
	    	//auditCtx.setClientUniqueKey("777777");
	    	auditCtx.setTuxedoServiceName("SVNTYP");
	    	//auditCtx.setForceExternal(true);
	    		    	
	    	TypedFML proxyBuff = new TypedFML(new InternalUserFMLFieldTable());
	    	proxyBuff.Fchg(InternalUserFMLFieldTable.FML_APPL_DATA, 0, "vbms435354534345345354354534534534344vbms435354534345345354354534534534344".getBytes());	    	
	    	TypedBuffer outBuff = (TypedBuffer)tuxedoService.execute("SVNTYPE", proxyBuff, 0, auditCtx, null);
            if (outBuff == null) {
            	message = "Returned FML32 buff is null. Exiting....";
            }
	    	System.out.println("Response: "+outBuff);	    	
		}catch (Exception se){
			message = se.getMessage();
		}	
		return message;
	}
	
	
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceExecInternal16ClientModuleException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	//auditCtx.setClientUniqueID("x281MARIO");
	    	//auditCtx.setClientUniqueKey("777777");
	    	auditCtx.setTuxedoServiceName("");
	    	//auditCtx.setForceExternal(true);
	    		    	
	    	TypedFML proxyBuff = new TypedFML(new InternalUserFMLFieldTable());
	    	proxyBuff.Fchg(InternalUserFMLFieldTable.FML_APPL_DATA, 0, "vbms435354534345345354354534534534344vbms435354534345345354354534534534344".getBytes());	    	
	    	TypedBuffer outBuff = (TypedBuffer)tuxedoService.execute("SVNTYPE", proxyBuff, 0, auditCtx, null);
            if (outBuff == null) {
            	message = "Returned FML32 buff is null. Exiting....";
            }
	    	System.out.println("Response: "+outBuff);	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceExecInternal16ApplDataException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	//auditCtx.setClientUniqueID("x281MARIO");
	    	//auditCtx.setClientUniqueKey("777777");
	    	auditCtx.setTuxedoServiceName("SVNTYPE");
	    	//auditCtx.setForceExternal(true);
	    		    	
	    	TypedFML proxyBuff = new TypedFML(new InternalUserFMLFieldTable());
	    	proxyBuff.Fchg(InternalUserFMLFieldTable.FML_APPL_DATA, 0, "".getBytes());	    	
	    	TypedBuffer outBuff = (TypedBuffer)tuxedoService.execute("SVNTYPE", proxyBuff, 0, auditCtx, null);
            if (outBuff == null) {
            	message = "Returned FML32 buff is null. Exiting....";
            }
	    	System.out.println("Response: "+outBuff);
	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceExecInternal16Success() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	//auditCtx.setClientUniqueID("x281MARIO");
	    	//auditCtx.setClientUniqueKey("777777");
	    	auditCtx.setTuxedoServiceName("SVNTYPE");
	    	//auditCtx.setForceExternal(true);
	    	
	    	TypedFML proxyBuff = new TypedFML(new InternalUserFMLFieldTable());
	    	proxyBuff.Fchg(InternalUserFMLFieldTable.FML_APPL_DATA, 0, "vbms435354534345345354354534534534344vbms435354534345345354354534534534344".getBytes());	    	
	    	TypedBuffer outBuff = (TypedBuffer)tuxedoService.execute("SVNTYPE", proxyBuff, 0, auditCtx, null);
            if (outBuff == null) {
            	message = "Returned FML32 buff is null. Exiting....";
            }
	    	System.out.println("Response: "+outBuff);	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	/*
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceExecExternal16AppNameException() {
		TuxedoServiceV3 tuxedoService = null;
		
		TypedFML outBuff = null;
	    VNTbl vnTbl = null;
	    ArchTbl archTbl = null;
	    String message = "Success";
		try{
			Context initialContext = new InitialContext();
			tuxedoService = (TuxedoServiceV3)initialContext.lookup("java:comp/env/ejb/TuxedoServiceV3Local");
    	 
    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITSEBENEFITSEBENEFITSEBENEFITSEBENEFITSEBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	auditCtx.setClientUniqueID("x281MARIO");
	    	auditCtx.setClientUniqueKey("777777");
	    	auditCtx.setTuxedoServiceName("SVNTYPE");
	    	auditCtx.setForceExternal(true);
	    	
	    	
	    	archTbl = new ArchTbl();
	    	vnTbl =  new VNTbl();
	        FldTbl[] tblArray = {archTbl, vnTbl};
	        TypedFML fml = new TypedFML(tblArray);
	        fml.Fchg(archTbl.name_to_Fldid("CALL_ID"), 0, new Integer(17));
	        fml.Fchg(archTbl.name_to_Fldid("DBDEBUG"), 0, 0);
	        fml.Fchg(archTbl.name_to_Fldid("CLIENT_NAME"), 0, "MarioTestCL");
	        fml.Fchg(archTbl.name_to_Fldid("CLIENT_MACH"), 0, "MarioPC");
	        fml.Fchg(archTbl.name_to_Fldid("JRN_LCTN_ID"), 0, "100");
	        fml.Fchg(archTbl.name_to_Fldid("LCTN_ID"), 0, "800001");
	        fml.Fchg(archTbl.name_to_Fldid("RTE_STN"), 0, "281");
	        fml.Fchg(archTbl.name_to_Fldid("PTCPNT_ID"), 0, "91320");
	        fml.Fchg(archTbl.name_to_Fldid("CLNT_TIME"), 0, "");
	        fml.Fchg(archTbl.name_to_Fldid("APPLICATION_ID"), 0, "121");
            System.out.println("Executing service....");
            outBuff = (TypedFML)tuxedoService.execute("SVNTYPE", fml, false, auditCtx, null);
            if (outBuff == null) {
            	message = "Returned FML32 buff is null. Exiting....";
            }
	    	System.out.println("Response: "+outBuff);
	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	*/
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceProcessRequestExternalAppNameException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();    		    	
	    	TuxParams tuxParams = new TuxParams();
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITSEBENEFITSEBENEFITSEBENEFITSEBENEFITSEBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	auditCtx.setClientUniqueID("x281MARIO");
	    	auditCtx.setClientUniqueKey("777777");
	    	//auditCtx.setTuxedoServiceName("SVNTYPESVNTYPESV");
	    	tuxParams.setTuxedoServiceName("SVNTYPE");
	    	auditCtx.setForceExternal(true);
	    		    	
            String response = tuxedoService.processRequest(tuxParams, auditCtx, null);
	    	System.out.println("Response: "+ response);
	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceProcessRequestExternalExtUidException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();
			TuxParams tuxParams = new TuxParams();
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	auditCtx.setClientUniqueID("x281MARIOx281MARIOx281MARIOx281MARIOx2812");
	    	auditCtx.setClientUniqueKey("777777");
	    	//auditCtx.setTuxedoServiceName("SVNTYPESVNTYPESV");
	    	tuxParams.setTuxedoServiceName("SVNTYPE");
	    	auditCtx.setForceExternal(true);
	    		    	
            String response = tuxedoService.processRequest(tuxParams, auditCtx, null);
	    	System.out.println("Response: "+ response);
	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceProcessRequestExternalExtKeyException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();
			TuxParams tuxParams = new TuxParams();
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	auditCtx.setClientUniqueID("x281MARIO");
	    	auditCtx.setClientUniqueKey("777777777777777777777777777777777777777777");
	    	//auditCtx.setTuxedoServiceName("SVNTYPESVNTYPESV");
	    	tuxParams.setTuxedoServiceName("SVNTYPE");
	    	auditCtx.setForceExternal(true);
	    		    	
            String response = tuxedoService.processRequest(tuxParams, auditCtx, null);
	    	System.out.println("Response: "+ response);
	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceProcessRequestExternalSvcException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();
			TuxParams tuxParams = new TuxParams();
    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	auditCtx.setClientUniqueID("x281MARIO");
	    	auditCtx.setClientUniqueKey("777777");
	    	//auditCtx.setTuxedoServiceName("SVNTYPESVNTYPESV");
	    	tuxParams.setTuxedoServiceName("SVNTYPESVNTYPESV");
	    	auditCtx.setForceExternal(true);
	    	
            String response = tuxedoService.processRequest(tuxParams, auditCtx, null);
	    	System.out.println("Response: "+ response);	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceProcessRequestExternalComputerNameException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();
			TuxParams tuxParams = new TuxParams();
    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.112.121.1001");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	auditCtx.setClientUniqueID("x281MARIO");
	    	auditCtx.setClientUniqueKey("7777");
	    	//auditCtx.setTuxedoServiceName("SVNTYPE");
	    	tuxParams.setTuxedoServiceName("SVNTYPE");
	    	auditCtx.setForceExternal(true);
	    	
            String response = tuxedoService.processRequest(tuxParams, auditCtx, null);
	    	System.out.println("Response: "+ response);
	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	// Same as Service test case
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceProcessRequestExternalClientModuleException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();
			TuxParams tuxParams = new TuxParams();
    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	auditCtx.setClientUniqueID("x281MARIO");
	    	auditCtx.setClientUniqueKey("777777");
	    	//auditCtx.setTuxedoServiceName("SVNTYPESVNTYPESV");
	    	tuxParams.setTuxedoServiceName("");
	    	auditCtx.setForceExternal(true);
	    	
            String response = tuxedoService.processRequest(tuxParams, auditCtx, null);
	    	System.out.println("Response: "+ response);	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	
	// Same as Service test case
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceProcessRequestExternalApplDataException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();
			TuxParams tuxParams = new TuxParams();
    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	auditCtx.setClientUniqueID("x281MARIO");
	    	auditCtx.setClientUniqueKey("777777");
	    	//auditCtx.setTuxedoServiceName("SVNTYPESVNTYPESV");
	    	tuxParams.setTuxedoServiceName("SVNTYPE");
	    	tuxParams.setData(getTestApplData());
	    	auditCtx.setForceExternal(true);
	    	
            String response = tuxedoService.processRequest(tuxParams, auditCtx, null);
	    	System.out.println("Response: "+ response);
	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	private String getTestApplData(){
		StringBuilder str = new StringBuilder();
		for(int num=0; num<3200; num++){
			str.append("datadatadata");
		}
		return str.toString();
	}
	
	
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceProcessRequestExternalSuccess() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();
			TuxParams tuxParams = new TuxParams();
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITSE");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	auditCtx.setClientUniqueID("x281MARIO");
	    	auditCtx.setClientUniqueKey("777777");
	    	tuxParams.setData("data");
	    	tuxParams.setTuxedoServiceName("SVNTYPE");
	    	auditCtx.setForceExternal(true);
	    		    	
            String response = tuxedoService.processRequest(tuxParams, auditCtx, null);
	    	System.out.println("Response: "+ response);	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceProcessRequestInternalComputerNameException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();
			TuxParams tuxParams = new TuxParams();
    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	//auditCtx.setTuxedoServiceName("SVNTYPE");
	    	tuxParams.setTuxedoServiceName("SVNTYPE");
	    	
            String response = tuxedoService.processRequest(tuxParams, auditCtx, null);
	    	System.out.println("Response: "+ response);	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	// Same as Service test case
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceProcessRequestInternalClientModuleException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();
			TuxParams tuxParams = new TuxParams();
    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	//auditCtx.setTuxedoServiceName("SVNTYPESVNTYPESV");
	    	tuxParams.setTuxedoServiceName("");
	    	tuxParams.setData("TestData");
            String response = tuxedoService.processRequest(tuxParams, auditCtx, null);
	    	System.out.println("Response: "+ response);	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	
	// Same as Service test case
	@WebMethod(action="TuxedoService")		
	public String testTuxedoServiceProcessRequestInternalApplDataException() {
	    String message = "Success";
		try{
			TuxedoServiceV3 tuxedoService = getTuxedoServiceV3Local();
			TuxParams tuxParams = new TuxParams();
    	 
			AuditContext auditCtx = new AuditContext();	    	
	    	auditCtx.setApplicationName("EBENEFITS");
	    	auditCtx.setClientIPAddress("192.1.1.100");
	    	auditCtx.setStationID("281");
	    	auditCtx.setUserId("281CEASL");
	    	//auditCtx.setTuxedoServiceName("SVNTYPESVNTYPESV");
	    	tuxParams.setTuxedoServiceName("SVNTYPE");
	    	//tuxParams.setData(getTestApplData(tuxParams));
	    	
            String response = tuxedoService.processRequest(tuxParams, auditCtx, null);
	    	System.out.println("Response: "+ response);	    	
		}catch (Exception se){
			message = se.getMessage();
		}
		return message;
	}
	
	
	
	
	/*
	@WebMethod(action="VeteranSession")		
	public String testVeteranSessionService() {
		VeteranSessionV2 veteranService = null;
		 String message = "Success";
		try{
			Context initialContext = new InitialContext();
			veteranService = (VeteranSessionV2)initialContext.lookup("java:comp/env/ejb/VeteranSessionEJBV2Local");
			//Person person = findPerson(veteranService, 1141L);
			//Person person = findPerson(veteranService, "123456789");
			Person person = findPerson(veteranService, "422345678");
			//Person person = findPerson(veteranService, 4L);
			if ( person == null ){
				message = "Person is null";
			}
			
		}catch (Exception se){
 			message = se.getMessage();
		}
		return message;		
	}
	
	
	
	@WebMethod(action="SecuritySession")		
	public String testSecuritySessionService() {
		CommonSecurityServiceLocalV2 commonSecurityService = null;
		String message = "Failure";
		TuxedoSecurityProfile securityProfile = null;
		try{
			Context initialContext = new InitialContext();
			commonSecurityService = (CommonSecurityServiceLocalV2)initialContext.lookup("java:comp/env/ejb/SecurityServiceV2Local");
			
			ServiceVO serviceVO = new ServiceVO("281CEASL", "281", 
					"10.224.104.174",
					"VBMS",
					ServiceVO.SecurityService.WLSAUTHEN);
			
			AuditContext auditContext=new AuditContext();
			auditContext.setUserId("281CEASL");
			auditContext.setStationID("281");
			auditContext.setClientIPAddress("1.1.1.1");
			auditContext.setApplicationName("VBMS");
						
			securityProfile = commonSecurityService.getSecurityProfile(serviceVO, auditContext, null);
			char retCode = securityProfile.getRetCode();
			switch (retCode) {
				case '0': case'1': case '3':
					message = "Successfully retrieved profile with code " + "[" + retCode + "]" + " for " +  securityProfile.getFirstName() + " " + securityProfile.getLastName();
					break;
				case '2': default:
					message =  "Exception occurred during retieval";					
			}
		}catch (Exception se){
 			message = se.getMessage();
		}
		return message;		
	}
	*/
	
	/*
	@WebMethod(action="VeteranSessionRemote")		
	public String testVeteranSessionServiceRemote() {
		VeteranSessionV2 veteranService = null;
		 String message = "Success";
		try{
			Context initialContext = new InitialContext();
			veteranService = createVeteranServiceRemoteV2();

			Person person = findPerson(veteranService, "422345678");
			if ( person == null ){
				message = "Person is null";
			}
			
		}catch (Exception se){
 			message = se.getMessage();
		}
		return message;		
	}
	
	private Person findPerson(VeteranSessionV2 veteranService, String ssn) {
		Person person =null;
		try {
			person = veteranService.findPerson(ssn, createAuditContext(), new HashMap());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		if (person != null) {
			if (person.getFirstNm()!=null)
			{
			    System.out.println("got person");
			    System.out.println(person.getFirstNm());
			}
			else
			{
				System.out.println("person not found");
				person=null;
			}
		} 
		return person;
	}
	*/
	/*
	public Person findPerson(VeteranSessionV2 veteranService, long key) {
		Person person =null;
		try {
			person = veteranService.findPerson(key, createAuditContext(), new HashMap());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		if (person != null) {
			System.out.println("got person");
			System.out.println(person.getFirstNm());
		} 
		return person;
	}
	*/
	
	private AuditContext createAuditContext()
	{
		AuditContext auditContext=new AuditContext();
		auditContext.setUserId("userid");
		auditContext.setStationID("station id");
		auditContext.setClientIPAddress("1.1.1.1");
		auditContext.setApplicationName("junit");
		return auditContext;
	}
	
	// Remote ejb methods
	private InitialContext getJNDIContext()throws NamingException {
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		p.put(Context.PROVIDER_URL, "t3://localhost:7001");
		p.put(Context.SECURITY_PRINCIPAL, "weblogic");
		p.put(Context.SECURITY_CREDENTIALS, "welcome1");		
		return  new InitialContext(p);
	}
	
	private TuxedoServiceV3 getTuxedoServiceV3Local() throws Exception{
		TuxedoServiceV3 tuxedoService = null;
		Context initialContext = new InitialContext();
		tuxedoService = (TuxedoServiceV3)initialContext.lookup("java:comp/env/ejb/TuxedoServiceV3Local");
		return tuxedoService;
	}

	
    /*
	private VeteranSessionRemoteV2 createVeteranServiceRemoteV2(){
		Object ref = null;
		VeteranSessionRemoteV2 veteranSessionRemoteV2 = null;
		try {
			ref = getJNDIContext().lookup("VeteranSessionEJBV2#"
					+ VeteranSessionRemoteV2.class.getName());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		veteranSessionRemoteV2 = (VeteranSessionRemoteV2) PortableRemoteObject
				.narrow(ref, VeteranSessionRemoteV2.class);
		return veteranSessionRemoteV2;
	}
	*/
}