/*
 * WebTest.java
 *
 * Copyright 2005 U.S. Dept Of Veterans Affais. All rights reserved.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */
package gov.va.vba.framework.web;

import javax.servlet.*;
import java.io.*;
import java.util.*;
import java.text.*;
import gov.va.vba.framework.common.*;
import gov.va.vba.framework.esb.connectors.client.TuxedoConnector;
import gov.va.vba.framework.logging.Logger;

import javax.servlet.http.*;


/**
 * Handles all requests and routes them to the desired applications.  
 *
 * @since	Sep 23, 2005
 * @version	
 * @author	Mario Rodrigues
 */
public class CoversServlet extends HttpServlet {
	private static Logger logger=Logger.getLogger(CoversServlet.class.getName());
	
	/**
	 *  
	 * @param
	 * @return
	 * @exception
	 */ 
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws 
		ServletException, IOException {
		
		//HttpSession s = req.getSession(true);		
		TestBean vo = null;
		RequestDispatcher dispatcher = null;
		//System.out.println("\nTestServlet.doGet(): Client IP addr: "+req.getRemoteAddr()+". ClientHost: "+req.getRemoteHost());
		String now = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
		
		if (req.getParameterMap().size() > 5) {
			logger.debug(now+": Found > 5 params...");
			logger.debug("\nPW: "+req.getParameter("priorService"));
			vo = new TestBean();
			//vo.setClientModule(req.getParameter("clientModule"));
			//vo.setCompName(req.getParameter("compName"));
			//vo.setReplyDestInd(req.getParameter("replyDestInd"));
			//vo.setPriorService(req.getParameter("priorService"));
			vo.setStation(req.getParameter("station"));
			vo.setUserid(req.getParameter("userId"));			
			vo.setApplData(req.getParameter("applData"));
			vo.setReply("");
			try {
				TuxedoConnector t = new TuxedoConnector();
				//vo.setReply(t.invokeTUXservice(vo, "192.1.158.100"));
			}
			catch (Exception e) {
				logger.error("",e);
				StackTraceElement[] ste = e.getStackTrace();
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < ste.length; i++)
					sb.append(ste[i]).append("\n");
				vo.setReply("Tuxedo Service ERROR: \n"+sb.toString());
			}
		}
		else {
			vo = new TestBean();
			/*
			vo.setClientModule("TuxTest");
			vo.setCompName("281-MRODRIGUES");
			vo.setReplyDestInd("Y");
			vo.setPriorService("Test");
			vo.setStation("281");
			vo.setUserid("281mrodr");	
			vo.setApplData("NORMAL   50010150 C01FILES FILES");
			vo.setReply("");*/
			logger.debug("CoversServlet: "+now+": Getting forwarded Attrib TMP1 and TMP2: "+req.getAttribute("tmp1")+", "+req.getAttribute("tmp2"));
			//vo.setClientModule((String)req.getAttribute("tmp1"));
			//vo.setCompName((String)req.getAttribute("tmp2"));
		}
		req.setAttribute("bean", vo);
		dispatcher = req.getRequestDispatcher("covers.jsp");
		//dispatcher = req.getRequestDispatcher("login.jsp");					
		dispatcher.forward(req, resp);
	}
	
	/**
	 *  
	 * @param
	 * @return
	 * @exception
	 */ 
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws 
		ServletException, IOException{

		logger.debug("\nEntered Servlet::doGet()...");
		doGet(req, resp);
	}
}
