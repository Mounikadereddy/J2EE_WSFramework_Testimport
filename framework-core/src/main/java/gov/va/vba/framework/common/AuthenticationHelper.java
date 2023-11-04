package gov.va.vba.framework.common;

import java.util.Properties;

import gov.va.vba.framework.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class AuthenticationHelper {

	private static Logger logger = Logger.getLogger(AuthenticationHelper.class);
	private static final String USERNAME = "userId";
	private static final String PASSKEY = "password";
	private static Properties authenticationProperties = new Properties();

	public static String getUsername(String application) {
		String username = getFromJNDI(application + "/" + USERNAME);
		return username;
	}

	public static String getPassword(String application) {
		String password = getFromJNDI(application + "/" + PASSKEY);
		return password;
	}

	private static String getFromJNDI(String key) {

		logger.debug("key="+key);
		String value = "";
		if (key != null) {
			if (authenticationProperties.containsKey(key))
				value = authenticationProperties.getProperty(key);
			else {
				javax.naming.Context _ctx = null;
				try {
					_ctx = new InitialContext();

					value = (String) _ctx.lookup(key);
				} catch (NamingException e) {
					logger.error("unable to get authentication from JNDI", e);
				} finally {
					try {
						_ctx.close();
					} catch (NamingException e) {
						logger.error(
								"error closing context during get authentication from JNDI",
								e);
					}
				}
				if (key != null && value != null)
					authenticationProperties.put(key, value);
			}
		}

		return value;
	}

}
