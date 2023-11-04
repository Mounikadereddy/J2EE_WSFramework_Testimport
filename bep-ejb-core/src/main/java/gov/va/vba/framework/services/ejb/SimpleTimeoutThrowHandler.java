package gov.va.vba.framework.services.ejb;

import java.util.Properties;

import com.adobe.idp.Context;
import com.adobe.idp.dsc.DSCException;
import com.adobe.idp.dsc.InvocationRequest;
import com.adobe.idp.dsc.clientsdk.ServiceClient;
import com.adobe.idp.dsc.clientsdk.ServiceClientFactory;
import com.adobe.idp.dsc.clientsdk.ServiceClientFactory.ThrowHandler;
import com.adobe.idp.dsc.provider.MessageDispatcher;
import com.adobe.idp.um.api.AuthenticationManager;
import com.adobe.idp.um.api.UMConstants;
import com.adobe.idp.um.api.UMException;
import com.adobe.idp.um.api.infomodel.AuthResult;
import com.adobe.livecycle.usermanager.client.AuthenticationManagerServiceClient;

import gov.va.vba.framework.logging.Logger;

/**
 * 
 * This ThrowHandler caches the user credentials and uses them
 * 
 * to refresh the Context in the
 * 
 * ServiceClientFactory upon expiry.
 * 
 */

public class SimpleTimeoutThrowHandler implements ThrowHandler {
	private static Logger logger=Logger.getLogger(SimpleTimeoutThrowHandler.class);

	private String username;
	private String password;
	private Properties connectionProps;

	public SimpleTimeoutThrowHandler(String username, String password, Properties connectionProps) {
		this.username = username;
		this.password = password;
		this.connectionProps = connectionProps;
	}
	
	public boolean handleThrowable(Throwable t, ServiceClient sc, ServiceClientFactory scf, MessageDispatcher md, InvocationRequest ir, int numTries) throws DSCException {
		if(timeoutError(t)) {
			AuthenticationManager am = new AuthenticationManagerServiceClient(ServiceClientFactory.createInstance (connectionProps));
			AuthResult ar = null;

			try {
				logger.debug("Authenticating after timeout");
				ar = am.authenticate(username,password.getBytes());
			} catch (UMException e) {
				throw new IllegalStateException(e);
			}
			
			//Create new context and refresh the ServiceClientFactory instance with the new context
			Context ctx = new Context();
			ctx.initPrincipal(ar);
			scf.setContext(ctx);

			logger.debug("Refreshed the context associated with ServiceClientFactory");

			//Now tell SCF to try the invocation again
			return true;
		}

		//Check so that we do not wrap the exception again 
		if(t instanceof DSCException)
			throw (DSCException)t;

		if(t instanceof RuntimeException)
			throw (RuntimeException)t;

		// how is it possible to get this far?
		throw new IllegalStateException(t);
	}

	private boolean timeoutError(Throwable t) { 
		if(!(t.getCause() instanceof UMException)){ 
			return false;
		}

		UMException ue = (UMException) t.getCause();

		//Check that UMException is due to the assertion/context expiry
		if(UMConstants.ErrorCodes.E_TOKEN_INVALID == ue.getErrCode()){
			logger.debug("Timeout error occurred because of assertion/context expiration");
			return true;
		}

		return false;
	}

}
