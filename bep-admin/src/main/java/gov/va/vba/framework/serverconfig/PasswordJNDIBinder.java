package gov.va.vba.framework.serverconfig;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NamingException;

import org.apache.commons.lang.StringUtils;

import weblogic.jndi.WLContext;

/**
 * An in-container utility class that binds usernames and password attributes to
 * the WebLogic JNDI cluster that this class runs within. This class should only
 * be run by admin personnel who understand the effects of binding usernames and
 * passwords to the JNDI tree.
 * <p>
 * This class is deployed as part of the bep-admin.jar and is referenced in the
 * "Startup & Shutdown Classes" section of a WebLogic Domain.
 * 
 * @since January 4, 2013
 * @version
 * @author Joshua Glickman
 */

public class PasswordJNDIBinder {

	// WLS admin console input:
	// ClassName: gov.va.vba.framework.serverconfig.PasswordJNDIBinder
	// Deployment Order: 1
	// Arguments: startup correspondence/liveCycle:[username]:[password]
	// LDAP:[username]:[password]
	private static final String USAGE = "Usage: PasswordJNDIBiner startup app1:[username]:[password] app2:[username]:[password] ...";
	private static final String USERNAME = "userId";
	private static final String PASSKEY = "password";
	private static final String STARTUP = "startup";
	private static final String SHUTDOWN = "shutdown";
	private static final String HELP = "-help";
	private static final String H = "-h";

	/**
	 * Loops through input arguments and binds values based on names Value are
	 * not replicated across nodes in the cluster. This is to prevent
	 * irregularities during fail over
	 * 
	 * @param args
	 * @throws
	 * @return
	 * @since Jan 4, 2013
	 */
	public static void main(String[] args) throws Exception {
		/*
		 * output from LiveCycleJNDIBinder: Bound JNDI Context Name
		 * 'correspondence/liveCycle/fileSystem' to: /AdobeDoc/devl Bound JNDI
		 * Context Name 'correspondence/liveCycle/url' to:
		 * t3://vbaausappdev1.vba.va.gov:62015 Bound JNDI Context Name
		 * 'correspondence/liveCycle/userId' to: ************* Bound JNDI
		 * Context Name 'correspondence/liveCycle/password' to: ******** Bound
		 * JNDI Context Name 'mail/smtp/host' to: smtp.va.gov
		 */

		// Name
		// testName=createCompositeName("correspondence/liveCycle/username",
		// null);
		if (STARTUP.equals(args[0]))
			startup(args);
		else if (SHUTDOWN.equals(args[0]))
			shutdown(args);
		else if (HELP.equals(args[0]) || H.equals(args[0]))
			help(args);
	}

	private static void help(String[] args) {
		System.out.println(USAGE);
	}

	private static void shutdown(String[] args) {
		// TODO implement this
	}

	private static void startup(String[] args) {

		System.out.println("*** PasswordJNDIBinder ***");
		Context ctx = null;
		Map<String, AuthenticationBean> authenticationMap = new HashMap<String, AuthenticationBean>();
		int i = 1;
		while (i < args.length) {
			//System.out.println("arg[" + i + "]=\"" + args[i] + "\"");
			String[] fields = StringUtils.split(args[i], ":");
			//System.out.println("fields.length="+fields.length);
			//for (int j=0;j<fields.length;j++)
				//System.out.println("fields["+j+"]=" + fields[j]);
			if (fields.length >=2 ) {
				//System.out.println("fields.length=" + fields.length);
				String app = fields[0];
				String username = fields[1];
				String password = null;
				if (fields.length>2)
					password = fields[2];
				//System.out.println("app=" + app);
				//System.out.println("username=" + username);
				//System.out.println("password=" + password);
				AuthenticationBean authenticationBean = new AuthenticationBean(
						username, password);
				//System.out.println("authenticationBean.getUsername()="
				//		+ authenticationBean.getUsername());
				//System.out.println("authenticationBean.getPassword()="
				//		+ authenticationBean.getPassword());
				authenticationMap.put(app, authenticationBean);
			} else {
				System.err.println("Invalid argument: ");
			}
			i++;
		}

		try {
			Hashtable<String, String> ht = new Hashtable<String, String>();
			ht.put(WLContext.REPLICATE_BINDINGS, "false");
			ctx = new InitialContext(ht);
			for (String app : authenticationMap.keySet()) {
				//System.out.println("in loop");
				//System.out.println("app=" + app);
				//System.out.println("authenticationMap.get(app)="
				//		+ authenticationMap.get(app));
				//System.out.println("authenticationMap.get(app).getUsername()="
				//		+ authenticationMap.get(app).getUsername());
				Name name = createCompositeName(app + "/" + USERNAME, ctx);
				//System.out.println("name=" + name);
				ctx.bind(name.add(USERNAME), authenticationMap.get(app)
						.getUsername());

				name = createCompositeName(app + "/" + PASSKEY, ctx);
				ctx.bind(name.add(PASSKEY), authenticationMap.get(app)
						.getPassword());
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			try {
				ctx.close();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		
	}

	private static Name createCompositeName(String app, Context ctx)
			throws NamingException {
		//System.out.println("in createCompositeName()");
		String[] subcontexts = app.split("[\\/]");
		//System.out.println("***** app=" + app);
		//System.out.println("subcontexts.length=" + subcontexts.length);
		Name name = new CompositeName();
		for (int k = 0; k < subcontexts.length - 1; k++) {
			//System.out.println("k=" + k);
			//System.out.println("subcontexts[" + k + "]" + subcontexts[k]);
			name.add(subcontexts[k]);
			ctx.createSubcontext(name);
		}
		return name;
	}

}
