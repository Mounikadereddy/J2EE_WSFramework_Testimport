package gov.va.vba.framework.services;

import gov.va.vba.framework.BaseTestCase;
import gov.va.vba.framework.EJBTestCase;
import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.services.ejb.ContentType;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import junit.framework.Assert;
import weblogic.rmi.extensions.PortableRemoteObject;

public class EmailServiceV2Tester extends EJBTestCase{
	
	private EmailServiceRemoteV2 emailServiceRemote = createEmailServiceRemote();
	
	public EmailServiceV2Tester()
	{
		super();
	}
		
	public void testEmailService()  {
		System.out.println("in testEmailService");

		try {
			Collection<File> attachments=new ArrayList<File>();
			AuditContext auditContext=new AuditContext();
			auditContext.setUserId("userid");
			auditContext.setStationID("station id");
			auditContext.setClientIPAddress("1.1.1.1");
			auditContext.setApplicationName("junit");
			
			emailServiceRemote.sendEmail("fromtest@test.com", "fromtest@test.com", "ivan.vanegas@va.gov", "", "", "junit test", "body from "+env, ContentType.TEXT, attachments, false, auditContext, new HashMap());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void testEmailServiceEncrypted()  {
		System.out.println("in testEmailServiceEncrypted");

		try {
			Collection<File> attachments=new ArrayList<File>();
			AuditContext auditContext=new AuditContext();
			auditContext.setUserId("userid");
			auditContext.setStationID("station id");
			auditContext.setClientIPAddress("1.1.1.1");
			auditContext.setApplicationName("junit");
			
			emailServiceRemote.sendEmail("fromtest@test.com", "fromtest@test.com", "ivan.vanegas@va.gov", "", "", "junit test", "body from "+env, ContentType.TEXT, attachments, true, auditContext, new HashMap());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}


	private EmailServiceRemoteV2 createEmailServiceRemote() {
		setupEJBClient();
		Object ref = null;
		
		try {
			ref = getJNDIContext().lookup("VbaEmailServiceV2#"
					+ EmailServiceRemoteV2.class.getName());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return (EmailServiceRemoteV2) PortableRemoteObject
				.narrow(ref, EmailServiceRemoteV2.class);

	}


}
