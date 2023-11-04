package gov.va.vba.framework;

import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EJBTestCase extends BaseTestCase{
	private static Context jndiContext;
	private static boolean isEJBClientSetup = false;
	
	public EJBTestCase(String arg0)
	{
		super(arg0);
	}

	public EJBTestCase()
	{
		super();
	}
	
	protected void setupEJBClient() {
		if (!isEJBClientSetup) {
			isEJBClientSetup=true;
			String initialContextFactory = null;
			String providerURL = null;
			String securityPrinciple = null;
			String securityCredentials = null;
			try {
				Map<String, String> envMap = convertResourceBundleToMap(resourceBundle);
				initialContextFactory = (String) envMap
						.get(Context.INITIAL_CONTEXT_FACTORY);
				providerURL = (String) envMap.get(Context.PROVIDER_URL);
				securityPrinciple = (String) envMap
						.get(Context.SECURITY_PRINCIPAL);
				securityCredentials = (String) envMap
						.get(Context.SECURITY_CREDENTIALS);

				System.out.println("initialContextFactory=" + initialContextFactory);
				System.out.println("providerURL=" + providerURL);

				// TODO: use service locator
				createInitialContext(initialContextFactory,
						providerURL, securityPrinciple, securityCredentials);


//				BATCH_FILE_PATH = (String) jndiContext
//						.lookup(LiveCycleContexts.BASE_FILESYSTEM.getName())
//						+ "/batch";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void createInitialContext(String contextFactory,
			String providerUrl, String principal, String credentials)
			throws NamingException {
		setupEJBClient();
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY, contextFactory);
		p.put(Context.PROVIDER_URL, providerUrl);
		if (principal != null) {
			p.put(Context.SECURITY_PRINCIPAL, principal);
		}
		if (credentials != null) {
			p.put(Context.SECURITY_CREDENTIALS, credentials);
		}
		jndiContext= new InitialContext(p);
	}
	
	public Context getJNDIContext()
	{
		setupEJBClient();
		return jndiContext;
	}
	


}
