package gov.va.vba.framework.auditing;

import gov.va.vba.framework.common.StringUtils;
import gov.va.vba.framework.exceptions.AuditException;
import gov.va.vba.framework.server.ServerUtil;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;




/*
 * @since	Dec 9, 2010
 * @version	1
 * @author	Joshua Glickman

 AuditIDs will be a 40 character string based on a substring of the weblogic instance ID, 
 date/time and sequence number in the format WWWWWWWWWWWWWWWWWWWWYYMMDDhhmmss99999999 where

 W is a 20 character Weblogic Instance ID the weblogic API 
 		which must be unique in the first 20 characters
 Y is the 2 digit year
 M is the 2 digit month
 D is the 2 digit day of the month
 h is the 2 digit hour of the time in 24 hour time format
 m is the 2 digit minutes of the time
 s is the 2 digit seconds of the time
 99999999 is a sequential number reset every time it reaches the maximum value

 */
public class AuditIDGenerator {

	private static AtomicLong sequence = new AtomicLong(0);

	/**
	 * Generates a unique audit ID
	 * 
	 * @param 	
	 * @return	String
	 * @since	Dec 9, 2010
	 */
	public static String generateAuditID() {
		StringBuilder auditID = generateW().append(generateDateTime()).append(generateq());
		if (auditID.length()!=40)
			throw new AuditException("auditID.length()!=40");
		return auditID.toString();

	}

	private static StringBuilder generateW() {
		StringBuilder p = new StringBuilder();
		//make sure weblogic instance ID string is exactly 20 characters
		p.append(StringUtils.rightPadSubstr(ServerUtil.getInstanceName(),20));
		return p;
	}

	private static StringBuilder generateDateTime()
	{
		StringBuilder dateTime=new StringBuilder();
		Date todaysDate = new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
		dateTime = dateTime.append(formatter.format(todaysDate));
		return dateTime;
	}
	
	private static StringBuilder generateq() {
		StringBuilder q = new StringBuilder();
		NumberFormat formatter = new DecimalFormat("00000000");
		if (sequence.get() >= 99999999)
			sequence.addAndGet(-sequence.get());
		return q.append(formatter.format(sequence.incrementAndGet()));
	}
}
