package gov.va.vba.framework.common;

import gov.va.vba.framework.logging.Logger;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class SystemUtils {
	private static Logger logger = Logger
			.getLogger(SystemUtils.class);

	@Deprecated
	public static final String LOAD_BALANCING_HOST = getLoadBalancingHost();
	@Deprecated
	public static final String LOAD_BALANCING_PORT = getLoadBalancingPort();

	public static final String REMOTE_INTERFACE="remote";
	public static final String LOCAL_INTERFACE="local";
	
	public enum Key {
		LOAD_BALANCING_HOST {
			public String toString() {
				return "load.balancing.host";
			}
		},
		LOAD_BALANCING_PORT {
			public String toString() {
				return "load.balancing.port";
			}
		},
		LOAD_BALANCING_PROTOCOL {
			public String toString() {
				return "load.balancing.protocol";
			}
		},
		SMTP_SERVER {
			public String toString() {
				return "smtp.server";
			}
		},
		LDAP_SERVER_ADDRESS {
			public String toString() {
				return "ldap.server.address";
			}
		},
		LDAP_VERSION {
			public String toString() {
				return "ldap.version";
			}
		},
		ENVIRONMENT {
			public String toString() {
				return "environment";
			}
		},
		EXTERNAL {
			public String toString() {
				return "external";
			}
		},
		OPTIMIZED_HOST {
			public String toString() {
				return "optimized.host";
			}
		},
		OPTIMIZED_PORT {
			public String toString() {
				return "optimized.port";
			}
		},
		OPTIMIZED_PROTOCOL {
			public String toString() {
				return "optimized.protocol";
			}
		},
		CORRESPONDENCE_LIVECYCLE_FILESYSTEM {
			public String toString() {
				return "correspondence.liveCycle.fileSystem";
			}
		},
		CORRESPONDENCE_LIVECYCLE_URL {
			public String toString() {
				return "correspondence.liveCycle.url";
			}
		},
		CORRESPONDENCE_LIVECYCLE_ENDPOINT {
			public String toString() {
				return "correspondence.liveCycle.endpoint";
			}
		},
		JNDI_INTERFACE_TYPE {
			public String toString() {
				return "jndi.interface.type";
			}
		},
		JNDI_PROVIDER_URL {
			public String toString() {
				return "jndi.provider.url";
			}
		},
		UI_ALLOW_USER_OVERWRITE {
			public String toString() {
				return "ui.allow.user.overwrite";
			}
		},
		PIV_LOGIN_TARGET {
			public String toString() {
				return "pivlogintarget";
			}
		}
	}

	public enum Environments {
		cert, dev, integ, nrel, perf, preprod, prod, prodtest, webdev, webtest
	}

	public static String getFrameworkVersion() {
		String version = "unknown";

		String fullPath = SystemUtils.class.getResource("SystemUtils.class")
				.toString();
		int frameworkPosition = fullPath.indexOf("/framework-");
		int jarPosition = fullPath.indexOf(".jar!");
		if (jarPosition > frameworkPosition && frameworkPosition > 0) {
			try {
				version = fullPath.substring(frameworkPosition + 11,
						jarPosition);
			} catch (Exception e) {
				logger.error("problem parsing framework version number", e);
			}
		}
		return version;
	}

	@Deprecated
	public static String getLoadBalancingHost() {
		return getProperty(Key.LOAD_BALANCING_HOST);
	}

	@Deprecated
	public static String getLoadBalancingPort() {
		return getProperty(Key.LOAD_BALANCING_PORT);
	}

	public static String getProperty(Key key) {
		return getProperty(key.toString());
	}
	
	public static String getProperty(String key)
	{
		String result = "";
		if (key != null) {
			try {
				ResourceBundle resourceBundle = ResourceBundle.getBundle("BEP");
				result = (String) resourceBundle.getObject(key);
				logger.debug(key + "=" + result);
			} catch (MissingResourceException rnfe) {
				logger.error("", rnfe);
			}
		}
		return result;
	}

	/*
	 * This will default to false if no value is present.
	 */
	public static boolean getBooleanProperty(Key key) {
		return getBooleanProperty(key.toString());
	}
	public static boolean getBooleanProperty(String key) {		
		String stringResult = "";
		boolean result = false;
		if (key != null) {
			try {
				ResourceBundle resourceBundle = ResourceBundle.getBundle("BEP");
				stringResult = (String) resourceBundle
						.getObject(key);
				result = Boolean.parseBoolean(stringResult);
				logger.debug(key + "=" + result);
			} catch (MissingResourceException rnfe) {
				logger.error("", rnfe);
			}
		}
		return result;
	}

}
