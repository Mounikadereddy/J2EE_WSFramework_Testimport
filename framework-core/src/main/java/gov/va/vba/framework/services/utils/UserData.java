package gov.va.vba.framework.services.utils;

import gov.va.vba.framework.logging.Logger;


public class UserData {
	private UserData() {
	}
	private static Logger logger=Logger.getLogger(UserData.class.getName());

	private static ThreadLocal<User> user = new ThreadLocal<User>();

	public static void clear() {
		user.remove();
	}

	public static void set(User frmwrkUser) {
		user.set(frmwrkUser);
	}

	public static User get() {
		logger.debug("user : "+user.toString());
		return user.get();
	}

}
