package gov.va.vba.framework.services;
import gov.va.vba.benefits.arch.fml.fldtbl.ArchTbl;
import gov.va.vba.benefits.arch.fml.fldtbl.VNTbl;
import gov.va.vba.framework.EJBTestCase;
import gov.va.vba.framework.common.AuditContext;

import javax.naming.NamingException;

import weblogic.rmi.extensions.PortableRemoteObject;
import weblogic.wtc.jatmi.FldTbl;
import weblogic.wtc.jatmi.TypedFML32;


/**
 *  
 *
 * @since	Jun 18, 2012
 * @version	
 * @author	Mario Rodrigues
 */
public class TuxedoFML32Test extends EJBTestCase{

	/**
	 * @param args	
	 * @throws	
	 * @return	
	 * @since	Jun 18, 2012
	 */
	public void testFML32() {

/*
This error comes from tbl files not available:
2012-07-11 08:45:02 ArchTbl [FATAL] Failure: <arch.tbl> not loaded, the tuxedo connector will not work!
2012-07-11 08:45:03 VNTbl [FATAL] VNTbl did not load vn.tbl, the tuxedo connector will not work w/o vn.tbl!
*/
		
		TypedFML32 outBuff = null;
	    VNTbl vnTbl = null;
	    ArchTbl archTbl = null;

	    try {
			
	    	TuxedoServiceV3 svc = createTuxedoServiceRemoteV3();
	    	AuditContext auditCtx = new AuditContext();
	    	
	    	auditCtx.setApplicationName("EBENEFITS");
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
	        TypedFML32 fml32 = new TypedFML32(tblArray);
	        fml32.Fchg(archTbl.name_to_Fldid("CALL_ID"), 0, new Integer(17));
	        fml32.Fchg(archTbl.name_to_Fldid("DBDEBUG"), 0, 0);
            fml32.Fchg(archTbl.name_to_Fldid("CLIENT_NAME"), 0, "MarioTestCL");
            fml32.Fchg(archTbl.name_to_Fldid("CLIENT_MACH"), 0, "MarioPC");
            fml32.Fchg(archTbl.name_to_Fldid("JRN_LCTN_ID"), 0, "100");
            fml32.Fchg(archTbl.name_to_Fldid("LCTN_ID"), 0, "800001");
            fml32.Fchg(archTbl.name_to_Fldid("RTE_STN"), 0, "281");
            fml32.Fchg(archTbl.name_to_Fldid("PTCPNT_ID"), 0, "91320");
            fml32.Fchg(archTbl.name_to_Fldid("CLNT_TIME"), 0, "");
            fml32.Fchg(archTbl.name_to_Fldid("APPLICATION_ID"), 0, "121");
            System.out.println("Executing service....");
            outBuff = (TypedFML32)svc.execute("SVNTYPE", fml32, false, auditCtx, null);
            if (outBuff == null) {
                System.out.println("Returned FML32 buff is null. Exiting....");
            	System.exit(9);
            }
	    	System.out.println("Response: "+outBuff);
	    } 
	    catch (Exception e) {	    	
	    	System.out.println("Exception: " + e);
	    	e.printStackTrace();
	    } 
	    finally {
	    }

	}
	private TuxedoServiceRemoteV3 createTuxedoServiceRemoteV3(){
		setupEJBClient();
		Object ref = null;
		TuxedoServiceRemoteV3 tuxedoServiceRemoteV3 = null;
		try {
			ref = getJNDIContext().lookup("VbaTuxedoServiceV3#"
					+ TuxedoServiceRemoteV3.class.getName());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		tuxedoServiceRemoteV3 = (TuxedoServiceRemoteV3) PortableRemoteObject
				.narrow(ref, TuxedoServiceRemoteV3.class);
		return tuxedoServiceRemoteV3;
	}

}
