package org.example.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.openmbean.CompositeDataSupport;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchJMXServlet extends HttpServlet {

	private static final long serialVersionUID = 9218361701005627354L;


	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method <br/>");
		
		out.println("<br/><br/><br/>");
		
		
		ArrayList<MBeanServer> findMBeanServer = MBeanServerFactory.findMBeanServer(null);
		
		for (MBeanServer server : findMBeanServer) {
			out.println("MBeanServer: " + server.getClass() +"<br/>");
			
			out.println("DefaultDomain: " + server.getDefaultDomain() +"<br/>");
			
			
			try {
				Object attribute;
				CompositeDataSupport compositeDataSupport;
				
				out.println("<br/>-----------------HEAP MEMORY---------------------------------<br/>");
				
				attribute = server.getAttribute(new ObjectName("java.lang:type=Memory"), "HeapMemoryUsage");
				compositeDataSupport = (CompositeDataSupport) attribute;
				
				out.println("<b>committed: " + compositeDataSupport.get("committed") +"</b> - " + Runtime.getRuntime().totalMemory() + " runtime.totalMemory<br/>");
				out.println("init:      " + compositeDataSupport.get("init") +"<br/>");
				out.println("max:       " + compositeDataSupport.get("max") +" - " + Runtime.getRuntime().maxMemory() + " runtime.maxMemory<br/>");
				out.println("used:      " + compositeDataSupport.get("used") + "<br/>");
				out.println("<br/>");
				out.println("<b>committed - used:      " + (((Long)compositeDataSupport.get("committed")) - ((Long)compositeDataSupport.get("used"))) +" </b> - " + Runtime.getRuntime().freeMemory() + " runtime.freeMemory<br/>");
				
//				out.println("<br/>-----------------NonHEAP MEMORY---------------------------------<br/>");
//				
//				attribute = server.getAttribute(new ObjectName("java.lang:type=Memory"), "NonHeapMemoryUsage");
//				compositeDataSupport = (CompositeDataSupport) attribute;
//				
//				out.println("committed: " + compositeDataSupport.get("committed") +"<br/>");
//				out.println("init:      " + compositeDataSupport.get("init") +"<br/>");
//				out.println("max:       " + compositeDataSupport.get("max") +"<br/>");
//				out.println("used:      " + compositeDataSupport.get("used") +"<br/>");
				
				
//				out.println("<br/>-----------------RUNTIME---------------------------------<br/>");
//				
//				out.println("free Memory:      " + Runtime.getRuntime().freeMemory() +"<br/>");
//				out.println("max  Memory:      " + Runtime.getRuntime().maxMemory() +"<br/>");
//				out.println("total  Memory:    " + Runtime.getRuntime().totalMemory() +"<br/>");
				
				
				out.println("<br/>-----------------SERVER---------------------------------<br/>");
				attribute = server.getAttribute(new ObjectName("jboss.as:management-root=server"), "serverState");
				
				out.println("serverState: " + attribute +"<br/>");
				
				out.println("<br/>-----------------SERVER environment---------------------------------<br/>");
				attribute = server.getAttribute(new ObjectName("jboss.as:core-service=server-environment"), "hostName");
				
				out.println("<b>hostName: " + attribute +"</b> - HostName <br/>");
				
				
				InetAddress inetAddress = InetAddress.getLocalHost();
				String hostIP = inetAddress.getHostAddress();
				
				out.println("HostAddress: " + hostIP +"<br/>");
				
				
				
				
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AttributeNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstanceNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedObjectNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MBeanException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ReflectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
