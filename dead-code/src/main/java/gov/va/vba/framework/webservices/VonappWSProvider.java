/*
 * VonappWSProvider.java
 *
 * Copyright 2009 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */

package gov.va.vba.framework.webservices;

import gov.va.vba.framework.logging.Logger;

import javax.xml.ws.*;
import javax.xml.ws.soap.*;
import javax.xml.ws.http.*;
import javax.xml.soap.*;
import java.io.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.Source;

/* To support SOAP 1.2:
 * 
Modify your WSDL transport to support SOAP 1.2, by taking either one of the following steps:

1)  Replace <wsoap12:binding style="document" transport="http://schemas.xmlsoap.org/soap/http"/> 
with <wsoap12:binding style="document" transport="http://www.w3.org/2003/05/soap/bindings/HTTP/"/> 

*OR*

2) Use <wsoap12:binding style="document" 
transport=""http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/"> 
 */
//SOAPBinding.SOAP11HTTP_BINDING
//SOAPBinding.SOAP12HTTP_BINDING
//http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/
//"http://www.w3.org/2003/05/soap/bindings/HTTP/"
//"http://schemas.xmlsoap.org/wsdl/soap/http"
//HTTPBinding.HTTP_BINDING
public class VonappWSProvider implements Provider<SOAPMessage> {

	private static Logger logger=Logger.getLogger(VonappWSProvider.class.getName());
	private static final String _resp = 
		"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
		"	<soapenv:Body>"+
		"		<VonappResponse xmlns=\"http://VonappService.org/types\">"+
		"			<argument>responseBean</argument>"+
		"		</VonappResponse>"+
		"	</soapenv:Body>"+
		"</soapenv:Envelope>";
	
	/**
	 * 
	 */
	public SOAPMessage invoke(SOAPMessage req) {

		logger.debug("invoke: Request: " + getSOAPMessageAsString(req));
		SOAPMessage res = null;
		try {
			res = makeSOAPMessage(_resp);
		}
		catch (Exception e) {
			logger.debug("Exception: occurred " + e);
		}
		logger.debug("invoke: Response: " + getSOAPMessageAsString(res));
		return res;
	}

	/**
	 * 
	 * @param msg
	 * @return
	 * @throws
	 * @return
	 * @since Nov 20, 2009
	 */
	private String getSOAPMessageAsString(SOAPMessage msg) {

		ByteArrayOutputStream baos = null;
		String s = null;
		try {
			baos = new ByteArrayOutputStream();
			msg.writeTo(baos);
			s = baos.toString();
		}
		catch (Exception e) {
			logger.error("",e);
		}
		return s;
	}

	/**
	 * 
	 * @param msg
	 * @return
	 * @throws
	 * @return
	 * @since Nov 20, 2009
	 */
	private SOAPMessage makeSOAPMessage(String msg) {

		try {
			MessageFactory factory = MessageFactory.newInstance();
			SOAPMessage message = factory.createMessage();
			message.getSOAPPart().setContent((Source)new StreamSource(new StringReader(msg)));
			message.saveChanges();
			return message;
		}
		catch (Exception e) {
			return null;
		}
	}
}
