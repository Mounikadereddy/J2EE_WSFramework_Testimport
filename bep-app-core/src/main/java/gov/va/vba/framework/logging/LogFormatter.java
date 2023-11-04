/*
 * %PM%, revision %PR%, %PRT%
 * 
 * Copyright 2008 U.S. Dept. Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.LogRecord;

/**
 * Class containing methods to format a Java Logging log record.
 * 
 * @author Tony D. Williams
 * 
 */
public class LogFormatter extends java.util.logging.Formatter {

	private static final DateFormat timestamp = new SimpleDateFormat(
			"<MMM dd, yyyy h:mm:ss a z>");
	private String jobid;

	public LogFormatter(String jobid) {

		this.jobid = jobid;
	}

	/**
	 * Method to format a Java Logging log record. The log record is formatted
	 * as:
	 * <p>
	 * <code>&lt;timestamp&gt; &lt;log level&gt; &lt;project name&gt; &lt;tid&gt;
	 * &lt;method&gt; &lt;message&gt;</code>
	 * <p>
	 * Example:
	 * <p>
	 * <code>&lt;Apr 21, 2009 1:40:54 PM CDT&gt; &lt;INFO&gt &lt;VETSNET&gt;
	 * &lt;TID:14&gt; &lt;format&gt; &lt;Hello World!&gt;</code>
	 */
	@Override
	public String format(LogRecord record) {

		String[] token = record.getSourceClassName().split("\\.");
		StringBuffer buffer = new StringBuffer();

		buffer.append(timestamp.format(new Date(record.getMillis())));
		buffer.append(" ");

		buffer.append("<" + record.getLevel() + ">");
		buffer.append(" ");

		buffer.append("<VETSNET>");

		buffer.append(" ");
		buffer.append("<JID:" + jobid + ">");
		buffer.append(" ");
		buffer.append("<TID:" + record.getThreadID() + ">");
		buffer.append(" ");

		buffer.append("<" + token[token.length - 1]);
		buffer.append(".");
		buffer.append(record.getSourceMethodName());
		buffer.append(">");

		buffer.append(" ");
		buffer.append("<" + record.getMessage() + ">");
		buffer.append("\n");

		Throwable throwable = record.getThrown();
		if (throwable != null) {
			StackTraceElement stackTraceElements[] = throwable.getStackTrace();

			buffer.append(throwable.getClass().getName());
			buffer.append(": ");
			buffer.append(throwable.getMessage());
			buffer.append("\n");
			for (StackTraceElement stackTrace : stackTraceElements) {
				buffer.append("\t");
				buffer.append("at ");
				buffer.append(stackTrace.toString());
				buffer.append("\n");
			}
		}
		return buffer.toString();
		// return record.getLevel() + ":" + token[token.length - 1] +
		// ":" +
		// record.getSourceMethodName() + ": " + record.getMessage() +
		// "\n";
	}
}
