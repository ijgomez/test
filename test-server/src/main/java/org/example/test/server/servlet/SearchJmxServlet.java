package org.example.test.server.servlet;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Set;

import javax.management.JMException;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.test.server.jmx.JVMMBeanDataDisplay;

/**
 * https://blogs.oracle.com/jmxetc/entry/a_small_program_that_prints
 * http://jsonviewer.stack.hu/
 * 
 * @author jizquierdo
 *
 */
public class SearchJmxServlet extends HttpServlet {

	private static final long serialVersionUID = 6949724384214804405L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/search/jmx.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object result = null;
		String name;
		ObjectName objectName = null;
		
		name = request.getParameter("objectName");
		
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("JMX Object Name: " + name);

		
		try {
			if (name != null && !name.isEmpty()) {
				objectName = new ObjectName(name);
			}
			
			result = displayJSON(objectName);
			
			request.setAttribute("result", result);
		} catch (Exception e) {
			e.printStackTrace(System.err);
			
			request.setAttribute("error", e);
		}
		
		request.setAttribute("objectName", name);
		request.getRequestDispatcher("/search/jmx.jsp").forward(request, response);
	}
	

	private String displayJSON(ObjectName objectName) throws IOException, JMException {
		MBeanServerConnection conn = ManagementFactory.getPlatformMBeanServer();
		StringBuffer results = new StringBuffer();
		
		final JVMMBeanDataDisplay display = new JVMMBeanDataDisplay(conn);

		Set<ObjectName> mbeans = conn.queryNames(objectName, null);
		
		int i = 0;
		for (ObjectName mbean : mbeans) {
			
			String r = display.toString(mbean);
			System.out.println(r);
			results.append(r);
			i++;
			System.out.println("----------------------------------------------------------");
			if (i < mbeans.size()) {
				results.append(",");
			}
		}
		
		return results.toString();
	}
}
