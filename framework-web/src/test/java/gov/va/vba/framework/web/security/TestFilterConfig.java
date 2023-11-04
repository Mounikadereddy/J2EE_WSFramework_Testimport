package gov.va.vba.framework.web.security;

import java.util.Enumeration;

import javax.naming.Context;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;

public class TestFilterConfig implements FilterConfig {
	
	private Context context;
	
	public TestFilterConfig(Context context) {
		this.context = context;
	}

	@Override
	public String getFilterName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInitParameter(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration getInitParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletContext getServletContext() {
		return (ServletContext) context;
	}

}
