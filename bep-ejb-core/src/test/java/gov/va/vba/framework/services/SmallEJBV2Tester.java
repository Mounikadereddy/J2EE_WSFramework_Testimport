package gov.va.vba.framework.services;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

public class SmallEJBV2Tester {

	public static void main(String[] args) {
		TuxedoServiceRemote tuxedoServiceRemote = null;
		try {

			Properties p = new Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			p.put(Context.PROVIDER_URL, "t3://vbaausappdev2.vba.va.gov:9015");
			Context jndiContext = new InitialContext(p);

			Object ref = jndiContext.lookup("VbaTuxedoService#"
					+ TuxedoServiceRemote.class.getName());
			tuxedoServiceRemote = (TuxedoServiceRemote) PortableRemoteObject
					.narrow(ref, TuxedoServiceRemote.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("done");

	}

}
