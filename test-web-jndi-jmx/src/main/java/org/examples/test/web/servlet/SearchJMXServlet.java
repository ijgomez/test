package org.examples.test.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class SearchJMX
 */
@WebServlet(name = "searchjmx", description = "Comprueba los recusos JMX disponibles del Servidor", urlPatterns = { "/searchjmx" })
public class SearchJMXServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(SearchJMXServlet.class);


	private static final long serialVersionUID = -6976989162716364524L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (LOGGER.isInfoEnabled()) LOGGER.info("doGet -> "  + request);
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (LOGGER.isInfoEnabled()) LOGGER.info("doPost -> "  + request);
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		this.processJMX();

		request.getRequestDispatcher("/jmx.jsp").forward(request, response);
	}

	private void processJMX() {
		// TODO Auto-generated method stub
		ArrayList<MBeanServer> findMBeanServer = MBeanServerFactory.findMBeanServer(null);
		
		for (MBeanServer server : findMBeanServer) {
			
			LOGGER.info(server);
			
			LOGGER.info("defaultDomain=" + server.getDefaultDomain());
			
			String[] domains = server.getDomains();
			for (String domain : domains) {
				LOGGER.info("domain=" + domain);
				
				
				
			}
			
			try {
				Object attribute;
				
				attribute = server.getAttribute(new ObjectName("jboss.as:core-service=server-environment"), "hostName");
				LOGGER.info(attribute);
				
				attribute = server.getAttribute(new ObjectName("java.lang:type=Memory"), "NonHeapMemoryUsage");
				LOGGER.info(attribute);
				
				attribute = server.getAttribute(new ObjectName("jboss.as:management-root=server"), "serverState");
				LOGGER.info(attribute);
				
//				Runtime.getRuntime().freeMemory()
				
//				Runtime.getRuntime().totalMemory()
				
				
				
//				Date startDate = (Date)server.getAttribute(new ObjectName("jboss.system:type=Server"), "StartDate");  
//	    		String hostAddress = (String)server.getAttribute(new ObjectName("jboss.system:type=ServerInfo"), "HostAddress");
//	    		String hostName = (String)server.getAttribute(new ObjectName("jboss.system:type=ServerInfo"), "HostName");
//	    		Long totalMemory = (Long)server.getAttribute(new ObjectName("jboss.system:type=ServerInfo"), "TotalMemory");
//	    		Long freeMemory = (Long)server.getAttribute(new ObjectName("jboss.system:type=ServerInfo"), "FreeMemory");
//	    		Boolean started = (Boolean)server.getAttribute(new ObjectName("jboss.system:type=Server"), "Started");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//jboss.as:subsystem=ee
			
				
			
			
			LOGGER.info("mBeanCount=" + server.getMBeanCount());
		}
	}



}
