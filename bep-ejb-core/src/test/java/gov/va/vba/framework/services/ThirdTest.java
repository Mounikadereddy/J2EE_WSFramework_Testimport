package gov.va.vba.framework.services;

import gov.va.vba.framework.common.SystemUtils;
import gov.va.vba.framework.domain.vo.ServiceVO;
import gov.va.vba.framework.esb.connectors.client.ExternalUserFMLFieldTable;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import weblogic.wtc.jatmi.Ferror;
import weblogic.wtc.jatmi.TypedBuffer;
import weblogic.wtc.jatmi.TypedFML;

public class ThirdTest {
	String userId = "VADIRMSDS";
	String stationId = "281";
	String clientIP = "11.220.4.125";
	String applicationName = "MSDS";
	String inputData = "SHARIBPNQ         503034483                    SPIVEAUD            LARRY                                      00  07111922                                               281MSDS      BIRLS Inquiry       ";
	String inputData2 = "SHARIBPNQ         224522500                    HARMON              WILLIAM                                    00  01241947                                               281MSDS      BIRLS Inquiry       ";

	String externalId = "";
	String externalKey = "";

	public String test3() {
		Context jndiContext;
		TuxedoServiceRemote tuxedoService = null;
		try {
			jndiContext = getInitialContext();
			tuxedoService = getSessionRemote(jndiContext);
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String proxyServiceForExternallyAuthnUsers = "wtcsvccall";

		ServiceVO valueObject = new ServiceVO(userId, stationId, clientIP,
				applicationName, ServiceVO.SHAREService.VAAUSIBM);
		valueObject.setData(inputData);
		valueObject.setExternalId(externalId);
		valueObject.setExternalKey(externalKey);

		TypedFML inpBuff = new TypedFML(new ExternalUserFMLFieldTable());
		try {
			inpBuff.Fchg(ExternalUserFMLFieldTable.FML_CLIENT_MODULE_NAME, 0,
					valueObject.getServiceName().getBytes());
			inpBuff.Fchg(ExternalUserFMLFieldTable.FML_USERID, 0, valueObject
					.getUserId().getBytes());

			inpBuff.Fchg(ExternalUserFMLFieldTable.FML_REPLY_DEST_IND, 0, 'N');
			inpBuff.Fchg(ExternalUserFMLFieldTable.FML_COMPUTER_NAME, 0,
					valueObject.getClientIPAddress().getBytes());

			inpBuff.Fchg(ExternalUserFMLFieldTable.FML_PRIOR_SERVICE_NAME, 0,
					"NA".getBytes());
			inpBuff.Fchg(ExternalUserFMLFieldTable.FML_STATION_TO_ROUTE_TO, 0,
					valueObject.getStationId().getBytes());

			inpBuff.Fchg(ExternalUserFMLFieldTable.FML_APPL_DATA, 0,
					valueObject.getData().getBytes());
			//inpBuff.Fchg(16679, 0, 'N'); // COPY_EXTINFO 295 type
			inpBuff.Fchg(ExternalUserFMLFieldTable.APPNAME, 0,
					valueObject.getApplicationName());
			inpBuff.Fchg(ExternalUserFMLFieldTable.SVCNAME, 0, valueObject
					.getService().toString().toLowerCase());

			inpBuff.Fchg(ExternalUserFMLFieldTable.EXTUID, 0,
					valueObject.getExternalId());
			inpBuff.Fchg(ExternalUserFMLFieldTable.EXTKEY, 0,
					valueObject.getExternalKey());
		} catch (Ferror e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TypedBuffer to = null;
		try {
			to = tuxedoService.execute(proxyServiceForExternallyAuthnUsers,
					inpBuff);
		} catch (TuxedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TypedFML typedFML = (TypedFML) to;
		String result="";
		try {
			result = new String((byte[])typedFML.Fget(ExternalUserFMLFieldTable.FML_APPL_DATA, 0));
		} catch (Ferror e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private Context getInitialContext() throws NamingException {
		return getDevContext();
	}

	private Context getLocalContext() throws NamingException {
		String encValue = SystemUtils.getProperty("server.pass.enc");
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");
		p.put(Context.PROVIDER_URL, "t3://localhost:7001");
		p.put(Context.SECURITY_PRINCIPAL, "weblogic");
		p.put(Context.SECURITY_CREDENTIALS, encValue);
		Context context = new InitialContext(p);
		System.out.println("got initial context");
		return context;

	}

	private Context getDevContext() throws NamingException {
		InitialContext ctx = null;
		// Will lookup the remote EJB depending upon the environment.
		// If windows environment, assume developers box and hit the dev server.
		// If not, assume EJB is deployed on same box.
		try {
			System.out.println("os.name=" + System.getProperty("os.name"));
			ctx = new InitialContext();
			System.out.println("got initial context");

		} catch (NamingException ne) {
			// TODO handling
			ne.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ctx;
	}

	private TuxedoServiceRemote getSessionRemote(Context jndiContext) {
		Object ref = null;
		TuxedoServiceRemote tuxedoServiceRemote = null;
		try {
			ref = jndiContext.lookup("VbaTuxedoService#"
					+ TuxedoServiceRemote.class.getName());
			System.out.println("got TuxedoSessionEJB reference");
			tuxedoServiceRemote = (TuxedoServiceRemote) PortableRemoteObject
					.narrow(ref, TuxedoServiceRemote.class);
			System.out.println("tuxedoServiceRemote=" + tuxedoServiceRemote);
			System.out.println("cast successful");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return tuxedoServiceRemote;
	}

	public static void main(String[] args) {

		ThirdTest test = new ThirdTest();
		// String one = test.test1();
		// String two = test.test2();
		String three = test.test3();

		// System.out.println(one);
		// System.out.println(two);
		System.out.println(three);
		// System.out.println(one.equals(two));
		// System.out.println(one.equals(three));
	}
}
