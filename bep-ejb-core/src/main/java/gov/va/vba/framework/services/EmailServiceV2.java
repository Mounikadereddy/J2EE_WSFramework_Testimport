
package gov.va.vba.framework.services;

import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.services.ejb.ContentType;
import java.io.File;
import java.util.Collection;
import java.util.Map;
public interface EmailServiceV2 {
  void sendEmail(String fromName, String from, String to, String cc, String bcc, String subject, String body, ContentType contentType, Collection<File> attachments, boolean encrypt, AuditContext auditContext, Map extensions) throws Exception ;

}
