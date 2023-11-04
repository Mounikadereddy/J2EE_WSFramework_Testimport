/*
 * 
 *
 * Copyright 2006 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.web.actions;

import gov.va.vba.framework.exceptions.BaseException;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.web.security.BaseFilter;
import gov.va.vba.framework.web.security.UserContext;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
//import java.net.CookieHandler;
//import java.net.CookieManager;
//import java.net.CookiePolicy;
//import java.net.CookieStore;
//import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.http.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import org.apache.struts.action.*;

/**
 * 
 */
public class SiteMinderEmulatorNonSAMLAction extends BaseAction  {

	private static Logger logger=Logger.getLogger(SiteMinderEmulatorNonSAMLAction.class.getName());
		
	/**
	 * Main method that must be implemented from BaseAction
	 * 
	 * @param	ActionMapping - ActionMapping for current Action
	 * @param	ActionForm - DynaActionForm for current page
	 * @param	HttpServletRequest - current request obj
	 * @param	HttpServletResponse - current response obj
	 * @param	AuditContext	- the current user's context (logged in user)
	 * @return	an ActionForward to the next Action/jsp 
	 * @throws	a BaseException that gets processed in Base and propagated to the UI
	 */
	public ActionForward executeAction(ActionMapping mapping,
									   ActionForm form,
									   HttpServletRequest req,
									   HttpServletResponse resp,
									   UserContext userCtx) throws BaseException {
				
		logger.debug("SiteMinderEmulatorNonSAMLAction::Module Prefix:- " + mapping.getModuleConfig().getPrefix());
		logger.debug("SiteMinderEmulatorNonSAMLAction::Mapping path:- " + mapping.getPath());	
		
		DynaActionForm loginForm = (DynaActionForm)form;
		
		String userid = loginForm.get("userid").toString().trim();
		String station  = loginForm.get("station").toString().trim();
		String appName  = loginForm.get("appName").toString().trim();
		String ipAddr   = loginForm.get("ipAddr").toString().trim();
		String testUserid = loginForm.get("testuserid").toString().trim();
		String allowOverwrite = "yes";
		if (testUserid.isEmpty()) { allowOverwrite = "no"; } 

        String targetURL = "http://localhost:7001/testservice/process.do?appid="+appName+"&SMPORTALURL=secure/home"; 
        
        List<NameValuePair> queryParams = new ArrayList<NameValuePair>();  // but also throw it in the query Params.. what's the difference... ???
        queryParams.add(new BasicNameValuePair("appid", appName));

		
		HashMap<String,String> httpHeaderAttributes = new HashMap();
		httpHeaderAttributes.put(BaseFilter.SM_USER,userid);
		httpHeaderAttributes.put(BaseFilter.SM_ALLOWUSEROVERWRITE,allowOverwrite);
		httpHeaderAttributes.put(BaseFilter.SM_STATIONID,station);
		httpHeaderAttributes.put(BaseFilter.SM_APPLICATION_NAME, appName);
		httpHeaderAttributes.put(BaseFilter.SM_PROXY_CLIENT_IP_HDR,ipAddr);
		httpHeaderAttributes.put(BaseFilter.SM_PROFILEID,testUserid);
	
		SiteMinderEmulatorResponseVO responseValues = null;	
		try {	
			responseValues = excutePost(targetURL, httpHeaderAttributes, queryParams);	    
		} catch (Exception e) {
			responseValues.smusrmsg = "";
			responseValues.cookies = "";
			responseValues.headers = "";
			responseValues.reply = e.getMessage();
		}
        		
        /* String responseMessage = 
				"userid="+userid+
				", station="+station+
				", appName="+appName+
				", ipAddr="+ipAddr+
				'\r'+'\n'+httpResponseString; */
		
		loginForm.set("smusrmsg", responseValues.smusrmsg);
		loginForm.set("cookies", responseValues.cookies);
		loginForm.set("location", responseValues.location);
		loginForm.set("headers", responseValues.headers);
		loginForm.set("reply", responseValues.reply);
		
		return mapping.findForward("success");
	}
	
	public static SiteMinderEmulatorResponseVO excutePost(String targetURL, Map<String, String> httpHeaderAttributes, List<NameValuePair> queryParams) {
		HttpURLConnection connection = null;
		SiteMinderEmulatorResponseVO siteMinderEmuResp = new SiteMinderEmulatorResponseVO();
		try {
			// Create connection
			connection = createPOSTConnection(connection, targetURL,
					httpHeaderAttributes);

			// Send request
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			
			wr.writeBytes(getQuery(queryParams));
			wr.flush();
			wr.close();

			// Get Response
			//InputStream es = connection.getErrorStream();
			
			//String msg = connection.getResponseMessage();
	
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));

			StringBuilder headers = new StringBuilder(); 
			StringBuilder cookies = new StringBuilder(); 
			String smUsrMsg = "";
			String location = "";
	
			Map<String,List<String>> headersMap = connection.getHeaderFields();
			Set<String> headerKeys = headersMap.keySet();
			
			for (String headerKey: headerKeys) {
				String headerValue = connection.getHeaderField(headerKey);
			
				headers.append(headerKey+": ");
				headers.append(headerValue);
				headers.append('\r');
				headers.append('\n');
					
				if (headerValue.startsWith("SMUSRMSG=")) {
					smUsrMsg = headerValue.substring(9);
					if (smUsrMsg.contains(";")) {
						smUsrMsg = smUsrMsg.substring(0, smUsrMsg.indexOf(';'));
					}
				}
				if (headerKey != null && headerKey.equalsIgnoreCase("Location")) {
					location = headerValue;
				}
                if (headerKey != null && headerKey.equalsIgnoreCase("Set-Cookie")) {
                	cookies.append(headerValue);
    				cookies.append('\r');
    				cookies.append('\n');
                }
			}
			siteMinderEmuResp.smusrmsg = smUsrMsg;
			siteMinderEmuResp.location = location;
			siteMinderEmuResp.headers = headers.toString();
			siteMinderEmuResp.cookies = cookies.toString();
			
			
			StringBuilder reply = new StringBuilder(); 
			String line;
			while ((line = rd.readLine()) != null) {
				reply.append(line);
				reply.append('\r');
				reply.append('\n');
			}
			rd.close();
			
			siteMinderEmuResp.reply = reply.toString();

			return siteMinderEmuResp;
		} catch (Exception e) {
			e.printStackTrace();
			siteMinderEmuResp.smusrmsg = "";
			siteMinderEmuResp.cookies = "";
			siteMinderEmuResp.location = "";
			siteMinderEmuResp.headers = "";
			siteMinderEmuResp.reply = e.getMessage();
			return siteMinderEmuResp;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	
	public static HttpURLConnection createPOSTConnection(
			HttpURLConnection connection, String stringURL, Map<String, String> httpHeaderAttributes) 
	throws java.net.MalformedURLException, java.io.IOException, java.net.ProtocolException, java.net.URISyntaxException {
		

		URL url = new URL(stringURL);
	    connection = (HttpURLConnection)url.openConnection();
	    connection.setRequestMethod("POST");
	    connection.setRequestProperty("Content-Type", 
	        "application/x-www-form-urlencoded"); 

	    /* connection.setRequestProperty("Content-Length", 
	        Integer.toString( <THE LENGTH >));*/
	    connection.setRequestProperty("Content-Language", "en-US");
	    connection.setRequestProperty("Cookie", "SMSESSION=Emulated SiteMinder Session Cookie;YetAnotherTestCookie=This test cookie content");

	    for (Map.Entry<String, String> item : httpHeaderAttributes.entrySet()) {
	    	String key = item.getKey();
	    	String value = item.getValue();
	    	connection.setRequestProperty(key,value);
	    }     

	    connection.setUseCaches(false);
	    connection.setDoOutput(true);
	    connection.setFollowRedirects(false);
	    connection.setInstanceFollowRedirects(false);
        return connection;		
	}
	private static String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
	{
	    StringBuilder result = new StringBuilder();
	    boolean first = true;

	    for (NameValuePair pair : params)
	    {
	        if (first)
	            first = false;
	        else
	            result.append("&");

	        result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
	        result.append("=");
	        result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
	    }

	    return result.toString();
	}
	private static class SiteMinderEmulatorResponseVO {
		private String smusrmsg = null;
		private String cookies = null;
		private String location = null;
		private String headers = null;
		private String reply = null;

	}
}

