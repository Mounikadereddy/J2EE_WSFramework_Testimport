package gov.va.vba.framework;

import gov.va.vba.framework.auditing.EJBAuditer;
import gov.va.vba.framework.auditing.LoginAuditer;
import gov.va.vba.framework.auditing.LoginFailureAuditer;
import gov.va.vba.framework.auditing.LogoutAuditer;
import gov.va.vba.framework.auditing.WebServiceAuditer;
import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.serverconfig.LoggerConfigurator;
import gov.va.vba.framework.services.ejb.TuxParams;
import gov.va.vba.framework.services.ejb.TuxedoSessionEJB;
import junit.framework.TestCase;

public class AuditTester extends TestCase {
	{
		LoggerConfigurator.firstTimeSetup();
	}

	private AuditContext getAuditContext(String testName) {
		AuditContext auditContext = new AuditContext();
		auditContext.setUserId("testUserID");
		auditContext.setApplicationName(testName);
		auditContext.setClientIPAddress("1.1.1.1");
		auditContext.setStationID("101");
		return auditContext;
	}

	private AuditContext getTuxedoAuditContext(String testName) {
		AuditContext auditContext = getAuditContext(testName);
		auditContext.setTuxedoServiceName(testName);
		return auditContext;
	}

	private TuxParams getTuxParamsEnum()
	{
		TuxParams tuxParams=new TuxParams();
		tuxParams.setData("data");
		tuxParams.setTuxedoService(gov.va.vba.framework.domain.vo.ServiceVO.SecurityService.WLSAUTHEN);
		return tuxParams;
	}

	private TuxParams getTuxParamsString()
	{
		TuxParams tuxParams=new TuxParams();
		tuxParams.setData("data");
		tuxParams.setTuxedoServiceName("freeform tuxedo service");
		return tuxParams;
	}

	public void testLoginAuditSuccess() {
		LoginAuditer loginAuditer = new LoginAuditer();
		loginAuditer.audit(getAuditContext("testLoginAuditSuccess"), true);
	}

	public void testLoginAuditFailure() {
		LoginAuditer loginAuditer = new LoginAuditer();
		loginAuditer.audit(getAuditContext("testLoginAuditFailure"), false);
	}

	public void testLoginAuditDeprecated() {
		LoginAuditer loginAuditer = new LoginAuditer();
		loginAuditer.audit(getAuditContext("testLoginAuditDeprecated"));
	}

	public void testLoginFailureAuditDeprecated() {
		LoginFailureAuditer loginFailureAuditer = new LoginFailureAuditer();
		loginFailureAuditer.audit(getAuditContext("testLoginFailureAuditDeprecated"));
	}

	public void testLogoutAuditDeprecated() {
		LogoutAuditer logoutAuditer = new LogoutAuditer();
		logoutAuditer.audit(getAuditContext("testLogoutAuditDeprecated"));
	}

	public void testLogoutAuditFailure() {
		LogoutAuditer logoutAuditer = new LogoutAuditer();
		logoutAuditer.audit(getAuditContext("testLogoutAuditFailure"), false);
	}

	public void testLogoutAuditSuccess() {
		LogoutAuditer logoutAuditer = new LogoutAuditer();
		logoutAuditer.audit(getAuditContext("testLogoutAuditSuccess"), true);
	}

	public void testNonTuxedoEJBV2AuditDeprecated() {
		EJBAuditer ejbAuditer = new EJBAuditer();
		ejbAuditer.audit(getAuditContext("testNonTuxedoEJBV2AuditDeprecated"), 
				TuxedoSessionEJB.class.toString(),	"getBIRLSData", 6666);
	}

	public void testNonTuxedoEJBV2AuditFailure() {
		EJBAuditer ejbAuditer = new EJBAuditer();
		ejbAuditer.audit(getAuditContext("testNonTuxedoEJBV2AuditFailure"), false,
				TuxedoSessionEJB.class.toString(),	"getBIRLSData", 6666);
	}

	public void testNonTuxedoEJBV2AuditSuccess() {
		EJBAuditer ejbAuditer = new EJBAuditer();
		ejbAuditer.audit(getAuditContext("testNonTuxedoEJBV2AuditSuccess"), true,
				TuxedoSessionEJB.class.toString(),	"getBIRLSData", 6666);
	}

	public void testNonTuxedoEJBV3AuditDeprecated() {
		EJBAuditer ejbAuditer = new EJBAuditer();
		ejbAuditer.audit(getAuditContext("testNonTuxedoEJBV3AuditDeprecated"),
				TuxedoSessionEJB.class.toString(),	"getBIRLSData", 6666);
	}

	public void testNonTuxedoEJBV3AuditFailure() {
		EJBAuditer ejbAuditer = new EJBAuditer();
		ejbAuditer.audit(getAuditContext("testNonTuxedoEJBV3AuditFailure"), false,
				TuxedoSessionEJB.class.toString(),	"getBIRLSData", 6666);
	}

	public void testNonTuxedoEJBV3AuditSuccess() {
		EJBAuditer ejbAuditer = new EJBAuditer();
		ejbAuditer.audit(getAuditContext("testNonTuxedoEJBV3AuditSuccess"), true,
				TuxedoSessionEJB.class.toString(),	"getBIRLSData", 6666);
	}

	public void testTuxedoEJBV2AuditDeprecated() {
		EJBAuditer ejbAuditer = new EJBAuditer();
		ejbAuditer.audit(getTuxedoAuditContext("testTuxedoEJBV2AuditDeprecated"),
				TuxedoSessionEJB.class.toString(), "getBIRLSData", 6666);
	}

	public void testTuxedoEJBV2AuditFailure() {
		EJBAuditer ejbAuditer = new EJBAuditer();
		ejbAuditer.audit(getTuxedoAuditContext("testTuxedoEJBV2AuditFailure"), false,
				TuxedoSessionEJB.class.toString(), "getBIRLSData", 6666);
	}

	public void testTuxedoEJBV2AuditSuccess() {
		EJBAuditer ejbAuditer = new EJBAuditer();
		ejbAuditer.audit(getTuxedoAuditContext("testTuxedoEJBV2AuditSuccess"), true,
				TuxedoSessionEJB.class.toString(), "getBIRLSData", 6666);
	}

	public void testTuxedoEJBV3EnumAuditDeprecated() {
		EJBAuditer ejbAuditer = new EJBAuditer();
		ejbAuditer.audit(getTuxedoAuditContext("testTuxedoEJBV3EnumAuditDeprecated"),
				getTuxParamsEnum(),
				TuxedoSessionEJB.class.toString(), "getBIRLSData", 6666);
	}

	public void testTuxedoEJBV3EnumAuditFailure() {
		EJBAuditer ejbAuditer = new EJBAuditer();
		ejbAuditer.audit(getTuxedoAuditContext("testTuxedoEJBV3EnumAuditFailure"), false,
				getTuxParamsEnum(),
				TuxedoSessionEJB.class.toString(), "getBIRLSData", 6666);
	}


	public void testTuxedoEJBV3EnumAuditSuccess() {
		EJBAuditer ejbAuditer = new EJBAuditer();
		ejbAuditer.audit(getTuxedoAuditContext("testTuxedoEJBV3EnumAuditSuccess"), true,
				getTuxParamsEnum(),
				TuxedoSessionEJB.class.toString(), "getBIRLSData", 6666);
	}

	public void testTuxedoEJBV3FreeformAuditDeprecated() {
		EJBAuditer ejbAuditer = new EJBAuditer();
		ejbAuditer.audit(getTuxedoAuditContext("testTuxedoEJBV3FreeformAuditDeprecated"),
				getTuxParamsString(),
				TuxedoSessionEJB.class.toString(), "getBIRLSData", 6666);
	}

	public void testTuxedoEJBV3FreeformAuditFailure() {
		EJBAuditer ejbAuditer = new EJBAuditer();
		ejbAuditer.audit(getTuxedoAuditContext("testTuxedoEJBV3FreeformAuditFailure"), false,
				getTuxParamsString(),
				TuxedoSessionEJB.class.toString(), "getBIRLSData", 6666);
	}

	public void testTuxedoEJBV3FreeformAuditSuccess() {
		EJBAuditer ejbAuditer = new EJBAuditer();
		ejbAuditer.audit(getTuxedoAuditContext("testTuxedoEJBV3FreeformAuditSuccess"), true,
				getTuxParamsString(),
				TuxedoSessionEJB.class.toString(), "getBIRLSData", 6666);
	}

	//no longer valid scenario
	/*
	public void testTuxedoWebServiceAudit() {
		WebServiceAuditer webServiceAuditer = new WebServiceAuditer();
		webServiceAuditer.audit(getTuxedoAuditContext(), "TestWebService",
				"getBIRLSData", 6666);
	}
	*/
	public void testWebServiceAuditDeprecated() {
		WebServiceAuditer webServiceAuditer = new WebServiceAuditer();
		webServiceAuditer.audit(getAuditContext("testWebServiceAuditDeprecated"), "TestWebService",
				"getBIRLSData", 6666);
	}
	
	public void testWebServiceAuditFailure() {
		WebServiceAuditer webServiceAuditer = new WebServiceAuditer();
		webServiceAuditer.audit(getAuditContext("testWebServiceAuditFailure"), false, "TestWebService",
				"getBIRLSData", 6666);
	}
	public void testWebServiceAuditSuccess() {
		WebServiceAuditer webServiceAuditer = new WebServiceAuditer();
		webServiceAuditer.audit(getAuditContext("testWebServiceAuditSuccess"), true, "TestWebService",
				"getBIRLSData", 6666);
	}

}
