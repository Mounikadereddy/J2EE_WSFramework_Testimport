package gov.va.vba.framework.services;

import java.util.HashMap;
import junit.framework.Assert;

import gov.va.vba.framework.BaseTestCase;
import gov.va.vba.framework.EJBTestCase;
import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.domain.entities.Person;

import javax.naming.NamingException;

import org.junit.Test;

import weblogic.rmi.extensions.PortableRemoteObject;

public class VeteranTester extends EJBTestCase{
	VeteranSessionRemoteV2 veteranSessionRemoteV2=createVeteranServiceRemoteV3();
	@Test
    public void testFindPersonBySSN() throws Exception {
    	Person person=findPerson("111220023");
    	System.out.println("person="+person);
    	Assert.assertNotNull(person);
    }
	@Test
    public void testFindPersonByKey() throws Exception {
    	Person person=findPerson(8000000049L);
    	System.out.println("person="+person);
    	Assert.assertNotNull(person);
    }
	
	public Person findPerson(String ssn) {
		Person person =null;
		try {
			person = veteranSessionRemoteV2.findPerson(ssn, createAuditContext(), new HashMap());
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
	public Person findPerson(long key) {
		Person person =null;
		try {
			person = veteranSessionRemoteV2. findPerson(key, createAuditContext(), new HashMap());
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

    
	private VeteranSessionRemoteV2 createVeteranServiceRemoteV3(){
		setupEJBClient();
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
	
	private AuditContext createAuditContext()
	{
		AuditContext auditContext=new AuditContext();
		auditContext.setUserId("userid");
		auditContext.setStationID("station id");
		auditContext.setClientIPAddress("1.1.1.1");
		auditContext.setApplicationName("junit");
		return auditContext;
	}

}
