package gov.va.vba.framework.services.mail;

import gov.va.vba.framework.auditing.LegacyEJBAuditer;
import gov.va.vba.framework.common.AuthenticationHelper;
import gov.va.vba.framework.common.SystemUtils;
import gov.va.vba.framework.logging.Logger;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.InvocationContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.mail.smime.SMIMEEnvelopedGenerator;
@Stateless(name = "VbaEmailService", mappedName = "VbaEmailService")
@Local(EmailService.class)
@Remote(EmailServiceRemote.class)
public class EmailServiceImpl implements EmailService {
	private static Logger logger=Logger.getLogger(EmailServiceImpl.class.getName());
	private static DirContext ldapContext;
	private CertificateFactory cf;
	private Session session;
	private static final String separators = ";";
	private static final String CERT_ATTR_NAME = "userCertificate;binary";
	private static String[] tos;
	private static String[] ccs;
	private static String[] bccs;
	private String smtpServer=SystemUtils.getProperty(SystemUtils.Key.SMTP_SERVER);
	private String ldapServer=SystemUtils.getProperty(SystemUtils.Key.LDAP_SERVER_ADDRESS);
	private String ldapVersion=SystemUtils.getProperty(SystemUtils.Key.LDAP_VERSION);
	private String ldapUsername=AuthenticationHelper.getUsername("LDAP");
	private String ldapPassword=AuthenticationHelper.getPassword("LDAP");
	private Hashtable<String, String> ldapConnectionProperties;
	
	@PostConstruct
	@ExcludeClassInterceptors
	public void init() {
		Security.addProvider(new BouncyCastleProvider());
		ldapConnectionProperties = new Hashtable<String, String>(11);
		ldapConnectionProperties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		ldapConnectionProperties.put(Context.PROVIDER_URL, ldapServer);
		ldapConnectionProperties.put("java.naming.ldap.version", ldapVersion);
		if (!StringUtils.isEmpty(ldapUsername))
		{
			ldapConnectionProperties.put(Context.SECURITY_AUTHENTICATION, "simple");
			ldapConnectionProperties.put(Context.SECURITY_PRINCIPAL, ldapUsername);
			if (!StringUtils.isEmpty(ldapPassword))
				ldapConnectionProperties.put(Context.SECURITY_CREDENTIALS, ldapPassword);
		}

		try {
			cf = CertificateFactory.getInstance("X.509", new BouncyCastleProvider());	
		} catch (CertificateException e) {
			throw new RuntimeException(e);
		}
				
		Properties props = new Properties();
		props.put("mail.smtp.host", smtpServer);
		session = Session.getDefaultInstance(props);
	}
	
	  @AroundInvoke
	  public Object manageLdapConnection(InvocationContext ctx) throws Exception {
			try {
				ldapContext = new InitialDirContext(ldapConnectionProperties);
			}
			catch (Exception e)
			{
//				logger.error("couldn't connect to LDAP",e);
			}
			return ctx.proceed();
	  }
	
	@Override
	public void sendEmail(String fromName, String from, String to, String cc, String bcc, String subject, String body, ContentType contentType, Collection<File> attachments, boolean encrypt) throws MessagingException, IOException {
		new LegacyEJBAuditer().audit(EmailServiceImpl.class.getName(), "sendEmail");
		tos = StringUtils.split(to, separators);
		ccs = StringUtils.split(cc, separators);
		bccs = StringUtils.split(bcc, separators);
		if(ccs == null) ccs = new String[0];
		if(bccs == null) bccs = new String[0];
		
		MimeMessage message = createMessage(from, fromName, subject);
				
		MimeBodyPart bodyPart = new MimeBodyPart();
		bodyPart.setContent(body, contentType.MIME);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(bodyPart);
		
		if (attachments != null) {
			for (File attachment : attachments) {
				bodyPart = new MimeBodyPart();
				FileDataSource fds = new FileDataSource(attachment);
				bodyPart.setDataHandler(new DataHandler(fds));
				bodyPart.addHeader("Content-Type", fds.getContentType());
				bodyPart.setFileName(attachment.getName());
				multipart.addBodyPart(bodyPart);
			}
		}
		
		if(encrypt){
			//MessagingException me=new MessagingException();;
			//logger.error("encryption is temporarily turned off for emergency release", me);
			//throw me; 
			bodyPart = new MimeBodyPart();
			bodyPart.setContent(multipart);
			bodyPart = encrypt(bodyPart);
			message.setContent(bodyPart.getContent(), bodyPart.getContentType());
		} else {
			message.setContent(multipart);
		}
		message.saveChanges();
		Transport.send(message);
	}
	
	private InternetAddress getAddress(String email) throws AddressException {
		InternetAddress ia = new InternetAddress(email);
		ia.validate();
		return ia;
	}
	
	private MimeMessage createMessage(String from, String fromName, String subject) throws MessagingException {
		MimeMessage message = new MimeMessage(session);
		InternetAddress ia = getAddress(from);
		try {ia.setPersonal(fromName);} catch (UnsupportedEncodingException e) {throw new MessagingException(e.getMessage(), e);}
		message.setFrom(ia);
		for(String address: tos) message.addRecipient(Message.RecipientType.TO, getAddress(address));
		for(String address: ccs) message.addRecipient(Message.RecipientType.CC, getAddress(address));
		for(String address: bccs)message.addRecipient(Message.RecipientType.BCC,getAddress(address));
		message.setSubject(subject);
		return message;
	}
	
	private MimeBodyPart encrypt(MimeBodyPart body) {
		SMIMEEnvelopedGenerator generator = new SMIMEEnvelopedGenerator();
		for(X509Certificate cert: getCertificates())
			generator.addKeyTransRecipient(cert);
		try {
			return generator.generate(body, SMIMEEnvelopedGenerator.DES_EDE3_CBC, BouncyCastleProvider.PROVIDER_NAME);	
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private List<X509Certificate> getCertificates() {
		List<X509Certificate> certs = new ArrayList<X509Certificate>();
		for(String address: tos){
			X509Certificate cert = getCertificate(address);
			if(cert != null) certs.add(cert);
		}
		for(String address: ccs){
			X509Certificate cert = getCertificate(address);
			if(cert != null) certs.add(cert);
		}
		for(String address: bccs){
			X509Certificate cert = getCertificate(address);
			if(cert != null) certs.add(cert);
		}
		return certs;
	}
	
	private X509Certificate getCertificate(String email) {
		X509Certificate cert = null;
		NamingEnumeration<SearchResult> results = null; 
			
		SearchControls controls = new SearchControls();
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		controls.setReturningAttributes(new String[] {CERT_ATTR_NAME});// limit search results to "certificate"
		
		try {
			results = ldapContext.search("", "(mail=" + email.trim() + ")", controls);
			while (results.hasMore()) {
				Attribute att = results.next().getAttributes().get(CERT_ATTR_NAME);
				if(att.get() == null) break;
				
				try {
					cert = (X509Certificate)cf.generateCertificate(new ByteArrayInputStream((byte[])att.get()));
					cert.checkValidity();
				} catch (CertificateException e) {
					logger.error("",e);
				} catch (ClassCastException e) {
					logger.error("",e); //Certificate is not of X.509 type
				}
			}
		} catch (NamingException e) {
			logger.error("",e);
		} finally {
			if (results != null) try {results.close();} catch (Exception e) {/* Ignore any exceptions from closing results */}
		}
		//TODO Add certificate absence handling
		return cert;
	}
	
	/**
	 * Test to make sure that JCE UCSPF (Unlimited Cryptographic Strength Policy Files) are installed in the JRE. UCSPF's contain no restrictions 
	 * on cryptographic strengths or import control restrictions.
	 * 
	 * @throws Exception	
	 * @throws	
	 * @return	
	 * @since	May 7, 2010
	 */
	//TODO Change to use
	/* private void testUnlimitedStrengthPolicy() {
		try {
			byte[] data = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 };
			SecretKey key64 = new SecretKeySpec(new byte[] {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 },"Blowfish");
			Cipher c = Cipher.getInstance("Blowfish/ECB/NoPadding");
			c.init(Cipher.ENCRYPT_MODE, key64);
			c.doFinal(data);
			SecretKey key192 = new SecretKeySpec(
				new byte[] {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
							0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
							0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17},
				"Blowfish");
			c.init(Cipher.ENCRYPT_MODE, key192);
			c.doFinal(data);	
		} catch (Exception e) {
			// TODO: handle exception
		}
	}*/
}
