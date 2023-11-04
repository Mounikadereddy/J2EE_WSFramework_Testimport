package gov.va.vba.framework.exceptions.handler;

import gov.va.vba.framework.exceptions.BaseException;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * Abstracts the cross cutting concern of Exception Handling in Ejb Layer. This
 * can be configured to be apply across ejb implementations globally or
 * selectively.
 * 
 * @author psimyeru
 * 
 */
public class EjbExceptionInterceptor {

	/**
	 * Intercepts Ejb method call and handles any exceptions raised.
	 * 
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	@AroundInvoke
	public Object handleException(InvocationContext ctx) throws Exception {
		try {
			return ctx.proceed();
		} catch (Exception e) {
			// Do not log/handle any business checked exceptions as they were
			// thrown intentionally.
			//
			if (!(e instanceof BaseException)) {
				new EjbExceptionHandler().handleException(e);
			}
			throw e;
		}
	}
}
