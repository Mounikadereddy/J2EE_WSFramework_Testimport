package gov.va.vba.framework.services;

import gov.va.vba.framework.BaseTestCase;
import gov.va.vba.framework.EJBTestCase;
import gov.va.vba.framework.services.mail.ContentType;
import gov.va.vba.framework.services.mail.EmailServiceRemote;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.NamingException;

import junit.framework.Assert;
import weblogic.rmi.extensions.PortableRemoteObject;

public class EmailServiceTester extends EJBTestCase{
	
	private EmailServiceRemote emailServiceRemote = createEmailServiceRemote();
	
	public EmailServiceTester()
	{
		super();
	}
	
	
	public void testEmailService()  {
		System.out.println("in testEmailService");

		try {
			Collection<File> attachments=new ArrayList<File>();
			emailServiceRemote.sendEmail("fromtest@test.com", "fromtest@test.com", "ivan.vanegas@va.gov", "", "", "junit test", "body from "+env, ContentType.TEXT, attachments, false);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void testEmailServiceEncrypted()  {
		System.out.println("in testEmailServiceEncrypted");

		try {
			Collection<File> attachments=new ArrayList<File>();
			emailServiceRemote.sendEmail("ivan.vanegas@va.gov", "ivan.vanegas@va.gov", "ivan.vanegas@va.gov", "", "", "junit test", "body from "+env, ContentType.TEXT, attachments, true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}


	private EmailServiceRemote createEmailServiceRemote() {
		setupEJBClient();
		Object ref = null;
		try {
			ref = getJNDIContext().lookup("VbaEmailService#"
					+ EmailServiceRemote.class.getName());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return (EmailServiceRemote) PortableRemoteObject
				.narrow(ref, EmailServiceRemote.class);

	}


}
