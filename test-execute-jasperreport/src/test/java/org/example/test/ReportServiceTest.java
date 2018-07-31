package org.example.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReportServiceTest {

	
	private ReportService reportService;
	
	@BeforeClass
	public static void init() throws SQLException, ClassNotFoundException, IOException { 
		Class.forName("org.hsqldb.jdbcDriver");
		
		// initialize database
		try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
			statement.execute("CREATE TABLE DOCUMENT (id INT NOT NULL, document VARCHAR(50) NOT NULL, path VARCHAR(256) NOT NULL, datetime DATE NOT NULL, PRIMARY KEY (id))");
			connection.commit();
			statement.executeUpdate("INSERT INTO DOCUMENT VALUES (1,'user manual', '/documents/users/apps/reports', '2016-01-01')");
			statement.executeUpdate("INSERT INTO DOCUMENT VALUES (2,'reference manual', '/documents/internal/apps/reports', '2016-01-01')");
			statement.executeUpdate("INSERT INTO DOCUMENT VALUES (3,'administration manual', '/documents/internal/apps/reports', '2016-01-01')");
			connection.commit();
		}

	}
	
	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:hsqldb:mem:report", "sa", "");
	}
	
	
	@Before
	public void initTest() {
		this.reportService = new ReportService();
	}
	
	@Test
	public void testExecute() {
		try {
			String reportTemplate = "src/test/resources/report.jrxml";
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("INIDATE", "01-12-2011 00:00:00");
			parameters.put("ENDDATE", "01-01-2012 00:00:00");
			
			
			this.reportService.execute(getConnection(), reportTemplate, parameters);
			assertTrue(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Not yet implemented");
		}
		
	}

}
