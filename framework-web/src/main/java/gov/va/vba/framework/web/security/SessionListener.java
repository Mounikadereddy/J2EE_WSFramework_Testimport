/*
 * SessionListener.java
 *
 * Copyright 2007 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */

package gov.va.vba.framework.web.security;

import java.io.Serializable;
import javax.servlet.http.*;


/**
 * An interface that a Class should implement if it wants to clean up services to be
 * executed by the VBA framework's <code>UserContext</code> class. Objects that implement
 * this interface can be registered with the <code>UserContext</code> using it's
 * <code>registerListener(SessionListener)</code> method. Once a user's HttpSession is
 * terminated or dies out, the object's disconnect() method is called.
 *
 * @since	Feb 20, 2007
 * @version	
 * @author	Mario Rodrigues
 */
public interface SessionListener extends Serializable {
	
	public void disconnect(UserContext uc);
}
