package gov.va.vba.framework.services.mail;

import java.io.File;
import java.util.Collection;

public interface EmailService {
	void sendEmail(String fromName, String from, String to, String cc, String bcc, String subject, String body, ContentType contentType, Collection<File> attachments, boolean encrypt) throws Exception;
}
