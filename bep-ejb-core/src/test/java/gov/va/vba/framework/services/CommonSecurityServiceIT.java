package gov.va.vba.framework.services;

import gov.va.vba.framework.EJBTestCase;
import gov.va.vba.framework.auditing.AuditIDGenerator;
import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.css.cssiam.domain.entities.CssUser;
import gov.va.vba.framework.css.cssiam.domain.entities.UserStation;

import java.util.List;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import weblogic.rmi.extensions.PortableRemoteObject;



/**
 * This class test that the following errors are being generated:
 * - css.cssprofile.application.unknown
 * - css.cssprofile.user.unknown
 * - css.cssprofile.user.station.allStationsLocked
 * - css.cssprofile.user.application.noAccess
 * - css.cssprofile.user.application.locked
 * - css.cssprofile.user.station.disable
 * 
 * Missing:
 * - css.cssprofile.application.globallyLocked
 * - css.cssprofile.user.application.allUserStationsLocked
 * - css.cssprofile.user.application.locked
 * 
 * @author VHAISPVANEGI
 *
 */
public class CommonSecurityServiceIT extends EJBTestCase {

	CommonSecurityServiceV2 cssUserRepo = createServiceRemote();
	AuditContext auditContext;
	
	public CommonSecurityServiceIT() {
		super();
	}
	
	public CommonSecurityServiceIT(String arg0)
	{
		super(arg0);
	}
	
	/**
	 * Setup
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		auditContext = new AuditContext();
		auditContext.setApplicationName(AuditIDGenerator.generateAuditID());
		auditContext.setStationID("-1");
		auditContext.setClientIPAddress("127.0.0.1");
	}

	public void testGetCssUserWrongAppName() {
		
		System.out.println("\nStart testing getCssUserWrongAppName");
		try {
			auditContext.setApplicationName("WRONGAPPNAME");
			auditContext.setUserId("VHAISPVANEGI");
			CssUser cssUser = cssUserRepo.getCssUserStationsByApplication("VHAISPVANEGI", "WRONGAPPNAME", auditContext);
			//fail if the execution made it this far
			fail();
		} catch (Exception ex) {
			if (ex.getCause() instanceof CssUserRepositoryException) {
				CssUserRepositoryException  e = (CssUserRepositoryException)ex.getCause();
				assertEquals("css.cssprofile.application.unknown",e.getReasonCode());
				System.out.println("Database succesfully returned "+e.getReasonCode());
			} else {
				fail();
			}
		}
	}
	
	public void testGetCssUserWrongUsername() {
		System.out.println("\nStart testing getCssUserWrongUsername");
		try {
			auditContext.setApplicationName("VBMS");
			auditContext.setUserId("WRONGUSERNAME");
			CssUser cssUser = cssUserRepo.getCssUserStationsByApplication("WRONGUSERNAME", "VBMS", auditContext);
			//fail if the execution made it this far
			fail();
		} catch (Exception ex) {
			if (ex.getCause() instanceof CssUserRepositoryException) {
				CssUserRepositoryException  e = (CssUserRepositoryException)ex.getCause();
				assertEquals("css.cssprofile.user.unknown",e.getReasonCode());
				System.out.println("Database succesfully returned "+e.getReasonCode());
			} else {
				fail();
			}
		}
	}
	
	public void testGetCssUserAllStationsLocked() {

		System.out.println("\nStart testing getCssUserAllStationsLocked");
		try {
			auditContext.setApplicationName("FRAMEWORK");
			auditContext.setUserId("BGSLCK01");
			CssUser cssUser = cssUserRepo.getCssUserStationsByApplication("BGSLCK01", "FRAMEWORK", auditContext);
			//fail if the execution made it this far
			fail();
		} catch (Exception ex) {
			if (ex.getCause() instanceof CssUserRepositoryException) {
				CssUserRepositoryException  e = (CssUserRepositoryException)ex.getCause();
				assertEquals("css.cssprofile.user.station.allStationsLocked",e.getReasonCode());
				System.out.println("Database succesfully returned "+e.getReasonCode());
			} else {
				fail();
			}
		}
	}
	
	public void testGetCssUserNoApplicationAccess() {
		System.out.println("\nStart testing getCssUserNoApplicationAccess");
		try {
			auditContext.setApplicationName("FRAMEWORK");
			auditContext.setUserId("BGSREG03");	
			CssUser cssUser = cssUserRepo.getCssUserStationsByApplication("BGSREG03", "FRAMEWORK", auditContext);
			//fail if the execution made it this far
			fail();
		} catch (Exception ex) {
			if (ex.getCause() instanceof CssUserRepositoryException) {
				CssUserRepositoryException  e = (CssUserRepositoryException)ex.getCause();
				assertEquals("css.cssprofile.user.application.noAccess",e.getReasonCode());
				System.out.println("Database succesfully returned "+e.getReasonCode());
			} else {
				fail();
			}
		}
	}
	
	public void testGetCssUserOneStationApplicationLocked() {

		System.out.println("\nStart testing getCssUserOneStationApplicationLocked");
		try {
			auditContext.setApplicationName("CH33 FET");
			auditContext.setUserId("VBAHINBERRYR");
			CssUser cssUser = cssUserRepo.getCssUserStationsByApplication("VBAHINBERRYR", "CH33 FET", auditContext);
			List<UserStation> stations =  cssUser.getUserStations();
			UserStation lockedStation = null;
			for (UserStation station : stations) {
				if (!station.isEnabled()) {
					lockedStation = station;
				}
			}
			assertEquals(lockedStation.getReasonCode(), "css.cssprofile.user.application.locked");
			System.out.println("Database returned succesfully at least one access locked by application: \n"+cssUser);
		} catch (Exception e) {
			fail();
		}
	}

	public void testGetCssUserOneStationApplicationNotApproved() {

		System.out.println("\nStart testing getCssUserOneStationApplicationNotApproved");
		try {
			auditContext.setApplicationName("FRAMEWORK");
			auditContext.setUserId("BGSREG01");
			CssUser cssUser = cssUserRepo.getCssUserStationsByApplication("BGSREG01", "FRAMEWORK", auditContext);
			List<UserStation> stations =  cssUser.getUserStations();
			UserStation lockedStation = null;
			for (UserStation station : stations) {
				if (!station.isEnabled()) {
					lockedStation = station;
				}
			}
			assertEquals(lockedStation.getReasonCode(), "css.cssprofile.user.application.notApproved");
			System.out.println("Database returned succesfully at least one access locked by application not approved: \n"+cssUser);
		} catch (Exception e) {
			fail();
		}
	}
	
	public void testGetCssUserOneStationLocked() {

		System.out.println("\nStart testing getCssUserOneStationLocked");
		try {
			auditContext.setApplicationName("FRAMEWORK");
			auditContext.setUserId("BGSREG02");
			CssUser cssUser = cssUserRepo.getCssUserStationsByApplication("BGSREG02", "FRAMEWORK", auditContext);
			List<UserStation> stations =  cssUser.getUserStations();
			UserStation lockedStation = null;
			for (UserStation station : stations) {
				if (!station.isEnabled()) {
					lockedStation = station;
				}
			}
			assertEquals(lockedStation.getReasonCode(), "css.cssprofile.user.station.disable");
			System.out.println("Database returned successfully at least one locked station: \n"+cssUser);
		} catch (Exception e) {
			fail();
		}
	}
	
	/**
	 * Test for application if globally locked
	 */
	public void testGetCssUserApplicationGloballyLocked() {

		System.out.println("\nStart testing getCssUserApplicationGloballyLocked: [BGSREG01, VBMS-R]");
		try {
			auditContext.setApplicationName("VBMS-R");
			auditContext.setUserId("BGSREG01");
			CssUser cssUser = cssUserRepo.getCssUserStationsByApplication("BGSREG01", "VBMS-R", auditContext);
			//fail if the execution made it this far
			fail();
		} catch (Exception ex) {
			if (ex.getCause() instanceof CssUserRepositoryException) {
				CssUserRepositoryException  e = (CssUserRepositoryException)ex.getCause();
				assertEquals("css.cssprofile.application.globallyLocked",e.getReasonCode());
				System.out.println("Database returned successfully: " + e.getReasonCode());
			} else {
				fail();
			}
		}
	}
	
	public CommonSecurityServiceV2 createServiceRemote()
	{
		setupEJBClient();
		Object ref = null;
		try {
			ref = getJNDIContext().lookup("VbaSecurityServiceV2#"
					+ CommonSecurityServiceRemoteV2.class.getName());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return (CommonSecurityServiceV2) PortableRemoteObject
				.narrow(ref, CommonSecurityServiceV2.class);
	}

}