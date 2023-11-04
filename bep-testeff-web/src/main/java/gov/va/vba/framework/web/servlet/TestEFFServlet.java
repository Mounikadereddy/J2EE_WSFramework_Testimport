package gov.va.vba.framework.web.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TestEFFServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String message;

	public void init() throws ServletException {
		message = "testEFFResponse";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set response content type
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<h1>" + message + "</h1>");
	
	}

	public void destroy() {
		// do nothing.
	}
}