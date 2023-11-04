package gov.va.vba.framework.css.cssiam.stationselector;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class DateVersionInterceptor extends HandlerInterceptorAdapter
{
	public final String DATE_FORMAT = "EEE MMM dd hh:mm:ss a z yyyy";
	
	@Autowired
	@Qualifier("buildVersion")
	private String buildVersion;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        String date = df.format(new Date());
        
        request.setAttribute("date", date);
		request.setAttribute("version", buildVersion);
		
		return true;
	}
}