package org.example.test.server.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class SearchJndiServlet extends HttpServlet {

	private static final long serialVersionUID = -1757200155468749313L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/search/jndi.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object result;
		String resourceName;
		
		resourceName = request.getParameter("nameResource");
		
		System.out.println("JNDI Name Resource: " + resourceName);
		
		if (resourceName != null && !resourceName.isEmpty()) {
			result = this.searchJNDI(resourceName);
		} else {
			result = "Nombre del Recurso vacio";
		}
		
		request.setAttribute("nameResource", resourceName);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/search/jndi.jsp").forward(request, response);
	}


	private Object searchJNDI(String resourceName) {
		InitialContext context;
		Object result = null;
		Object resource = null;
		
		
		
		try {
			context = new InitialContext();
			
			System.out.println("Enumerates the names bound in the named context, along with the objects bound to them:");
			NamingEnumeration<NameClassPair> list = context.list("");
			while (list.hasMore()) {
				 System.out.println(list.next().getName());
			}
			
			resource = context.lookup(resourceName);
			
			System.out.println("Resource found: " + resource);
			
			if (resource instanceof DataSource) {
				Connection connection = null;
				Statement statement = null;
				ResultSet rs = null;
				try {
					connection = ((DataSource)resource).getConnection();
					statement = connection.createStatement();
					rs = statement.executeQuery("select 1 from dual");
					
		            while (rs.next()) {
		            	System.out.println("Resurso sql: " + rs.getMetaData());
		            }
					
				} finally {
					if (rs != null) {
						rs.close();
					}
					if (statement != null) {
						statement.close();
					}
					if (connection != null) {
						connection.close();
					}
					
				}
			}
			result = resource;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			result = e;
		}
		return result;
	}

}
