package org.examples.test.web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class SearchJndi
 */
@WebServlet(name = "searchjndi", description = "Comprueba que un recurso JNDI existe", urlPatterns = { "/searchjndi" })
public class SearchJndi extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(SearchJndi.class);


	private static final long serialVersionUID = -6976989162716364524L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (LOGGER.isInfoEnabled()) LOGGER.info(request);
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (LOGGER.isInfoEnabled()) LOGGER.info(request);
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jndiResourceName = request.getParameter("jndiNameResource");
		LOGGER.debug("doPost->" + jndiResourceName);
		
		Object result = searchJndi(jndiResourceName);
		
		request.setAttribute("jndiNameResource", jndiResourceName);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jndi.jsp").forward(request, response);
	}

	private Object searchJndi(String jndiResourceName) {
		InitialContext context;
		
		try {
			context = new InitialContext();
			Object resource = context.lookup(jndiResourceName);
			if (LOGGER.isInfoEnabled()) { LOGGER.info("Resurso encontrado: " + resource); }
			
			Connection connection = ((DataSource)resource).getConnection();
			
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery("select 1");
            while (rs.next()) {
            	if (LOGGER.isInfoEnabled()) { LOGGER.info("Resurso sql: " + rs.getMetaData()); }
            	
            }
			
			rs.close();
			
			statement.close();
			
			return resource;	
		} catch (Exception e) {
			LOGGER.error("No encentrado el recurso", e);
			return e;
		}
		
	}

}
