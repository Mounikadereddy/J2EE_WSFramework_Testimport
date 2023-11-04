package gov.va.vba.framework.auditing;

import gov.va.vba.framework.common.AuditContext;
import gov.va.vba.framework.common.StringUtils;
import gov.va.vba.framework.domain.vo.ServiceVO;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.services.ejb.TuxParams;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class EJBAuditInterceptor {
	private static Logger logger = Logger.getLogger(EJBAuditInterceptor.class
			.getName());
	private AuditContext auditContext = null;
	private TuxParams tuxParams;
	private String tuxedoServiceName = null;

	@AroundInvoke
	public Object profile(InvocationContext invocationContext) throws Exception {
		Object o=null;
		boolean success=false;
		Calendar startTime=new GregorianCalendar();
		auditContext = null;
		tuxedoServiceName = null;
		tuxParams = null;
		grabParams(invocationContext);
		AuditContext.validate(auditContext);
		if (StringUtils.isEmpty(auditContext.getAuditID()))
			auditContext.setAuditID(AuditIDGenerator.generateAuditID());
		try
		{
			o=invocationContext.proceed();
			success=true;
		}
		catch (Exception e)
		{
			logger.error("", e);
			
			throw e;
		}
		finally
		{
			Calendar endTime=new GregorianCalendar();
			long executionTime=endTime.getTimeInMillis()-startTime.getTimeInMillis();
			new EJBAuditer().audit(auditContext, success, tuxParams, invocationContext.getTarget()
					.toString(), invocationContext.getMethod().getName(), tuxedoServiceName ,executionTime);
		}
		return o; 
	}

	private void grabParams(InvocationContext invocationContext)
	{
		
		for (Object item : invocationContext.getParameters()) {
			logger.debug("parameter is: " + item);
			if (item instanceof AuditContext)
				auditContext = (AuditContext) item;
			if (item instanceof TuxParams)
				tuxParams = (TuxParams) item;
		}
		
		//If the method called is annotated with a Tuxedo Service
		if (invocationContext.getMethod().isAnnotationPresent(AuditedTuxedoService.class)) {
			AuditedTuxedoService auditedTuxedoService = invocationContext.getMethod().getAnnotation(AuditedTuxedoService.class);
			tuxedoServiceName = auditedTuxedoService.name();
			//If the EJB call has been made to an implementation of TuxedoServiceV2 or TuxedoServiceV3
		} else if (invocationContext.getMethod().getDeclaringClass().getInterfaces()[0].getName().equals("gov.va.vba.framework.services.TuxedoServiceV3") ||
				invocationContext.getMethod().getDeclaringClass().getInterfaces()[0].getName().equals("gov.va.vba.framework.services.TuxedoServiceV2")) {
			//If processRequest has been invoked, the service name is in TuxParams
			if (invocationContext.getMethod().getName().equals("processRequest")) {
				for (Object item : invocationContext.getParameters()) {
					logger.debug("parameter is: " + item);
					if (item instanceof TuxParams) {
						tuxedoServiceName = ((TuxParams)item).getActualServiceName();
						break; //found it. Break out of the loop
					}
				}
			} else if (invocationContext.getMethod().getName().equals("execute")) {
				for (Object item : invocationContext.getParameters()) {
					logger.debug("parameter is: " + item);
					if (item instanceof ServiceVO) {
						tuxedoServiceName = ((ServiceVO)item).getService().toString();
						break; //found it. Break out of the loop
					} else if (item instanceof String) {
						//The TuxedoService name is the only String in execute methods
						tuxedoServiceName = (String) item;
					}
				}
			}
		}
	}
}
