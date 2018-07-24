package org.example.test;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileHelperTest {
	
	private Connection connection;

	@Before
	public void beforeTest() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:test", "sa", "");
	}
	
	@Test
	public void testExecuteScriptSQL() throws Exception {
		
		FileHelper.executeScriptSQL(connection, "src/test/resources/script.sql");
		
		assertTrue(true);
	}
	
	@After
	public void afterTest() throws Exception {
		if (connection != null) {
			connection.commit();
			connection.close();
		}
	}
	
}
