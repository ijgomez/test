package org.example.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class Main {

	public static void main(String[] args) {
		try {
			if (args == null || args.length != 1) {
				System.err.println("Falta argumento: [ORACLE, POSTGESQL, SQLSERVER, MYSQL]");
			} else {
				
				if (args[0].equals("ORACLE")) testOracle();
				if (args[0].equals("POSTGRESQL")) testPostgreSQL();
				if (args[0].equals("SQLSERVER")) testSQLServer();
				if (args[0].equals("MYSQL")) testMySQL();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	private void execute(String driver, String url, String username, String password, Work work) throws SQLException {
		Connection connection = null;
		long t = System.currentTimeMillis();

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		try {
			System.out.println();
			System.out.println("Testing Database Connection...");
			System.out.println("--------------------------------------------------------------------");
			System.out.println(String.format("Locale: %s", Locale.getDefault()));
			System.out.println(String.format("Driver: %s", driver));
			System.out.println(String.format("URL: %s", url));
			System.out.println(String.format("UserName: %s", username));
			System.out.println(String.format("Password: %s", password));
			System.out.println();
			
			Class.forName(driver);
			
			System.out.println("Getting Connection from the dataSource of the application.");
			connection = DriverManager.getConnection(url, username, password);
			
			DatabaseMetaData metaData = connection.getMetaData();
			System.out.println(String.format("Database: %s (%s)", metaData.getDatabaseProductName(), metaData.getDatabaseProductVersion()));
			System.out.println(String.format("Driver: %s (%s)", metaData.getDriverName(), metaData.getDriverVersion()));
		
			if (connection != null) {
				System.out.println("Executing work...");
				work.execute(connection);
				System.out.println();
			}
		} catch (ClassNotFoundException e) {
			System.err.println(String.format("Database Driver not found: %s", driver));
			e.printStackTrace(System.err);
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		
		System.out.println(String.format("Running user query... in %s msec.", (System.currentTimeMillis() - t)));
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}

	/**
	 * Operaciones de prueebas con PostgreSQL.
	 * 
	 * @throws SQLException
	 */
	private static void testPostgreSQL() throws SQLException {
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql://192.168.1.1:9999/BBDD";
		String username = "USUARIO";
		String password = "P@ssw0rd";
		
		//TODO Operacion 1
		(new Main()).execute(driver, url, username , password, new Work() {
			
			private static final String SQL = "SELECT * FROM TABLE order by id";
			
			public void execute(Connection connection) throws SQLException {
				Statement statement = null;
				ResultSet rs = null;
				
				try {
					System.out.println(String.format("Query SQL: %s", SQL));
					
					statement = connection.createStatement();
					rs = statement.executeQuery(SQL);
					
//				ResultSetMetaData metaData = rs.getMetaData();
					
					
					while (rs.next()) {
						System.out.println(String.format("Result: %s", rs.getString(1)));
					}
				
				} finally {
					if (rs != null) {
						rs.close();
					}
					if (statement != null) {
						statement.close();
					}
				}
				
			}
		});
	}

	/**
	 * Operaciones de prueebas con Oracle.
	 * 
	 * @throws SQLException
	 */
	private static void testOracle() throws SQLException {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.1.1:1521:BBDD";
		String username = "USUARIO";
		String password = "P@ssw0rd";
		
		//TODO Operacion 1
		(new Main()).execute(driver, url, username , password, new Work() {

			private static final String SQL = "SELECT TO_NUMBER('1,1') FROM DUAL";
			
			public void execute(Connection connection) throws SQLException {
				Statement statement = null;
				ResultSet rs = null;
				
				try {
					System.out.println(String.format("Query SQL: %s", SQL));
					
					statement = connection.createStatement();
					rs = statement.executeQuery(SQL);
					
					while (rs.next()) {
						System.out.println(String.format("Result: %s", rs.getString(1)));
					}
				
				} finally {
					if (rs != null) {
						rs.close();
					}
					if (statement != null) {
						statement.close();
					}
				}
				
			}
		});
	}
	
	/**
	 * Operaciones de prueebas con SQL Server.
	 * 
	 * @throws SQLException
	 */
	private static void testSQLServer() throws SQLException {
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://192.168.1.1:1433;databaseName=BBDD;packetSize=32767";
		String username = "USUARIO";
		String password = "P@ssw0rd";
		
		//TODO Operacion 1
		(new Main()).execute(driver, url, username , password, new Work() {

			private static final String SQL = "SELECT COUNT(*) FROM ELEMENT";
		
			public void execute(Connection connection) throws SQLException {
				Statement statement = null;
				ResultSet rs = null;
				
				try {
					System.out.println(String.format("Query SQL: %s", SQL));
					
					statement = connection.createStatement();
					rs = statement.executeQuery(SQL);
					
					while (rs.next()) {
						System.out.println(String.format("Result: %s", rs.getString(1)));
					}
				
				} finally {
					if (rs != null) {
						rs.close();
					}
					if (statement != null) {
						statement.close();
					}
				}
				
			}
		});
		
		//TODO Operacion 2
		(new Main()).execute(driver, url, username , password, new Work() {

			private static final String SQL = "INSERT INTO TABLE (VALUE) values ('6.5070')";

			public void execute(Connection connection) throws SQLException {
				PreparedStatement prepareStatement = null;
				boolean hasErrors = false;

				try {
					System.out.println(String.format("Query SQL: %s", SQL));
					
					connection.setAutoCommit(false);
					prepareStatement = connection.prepareStatement(SQL);
					
					int result = prepareStatement.executeUpdate();
					
					System.out.println(String.format("Result: %s", result));
				} catch (SQLException e) {
					hasErrors = true;
					throw e;
				} finally {
					if (prepareStatement != null) {
						prepareStatement.close();
					}
					if (hasErrors) {
						connection.rollback();
						System.out.println("ROLLBACK!!!");
					} else {
						connection.commit();
						System.out.println("COMMIT");
					}
				}
			}
		});
	}
	
	private static void testMySQL() throws SQLException {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/company";
		String username = "USUARIO";
		String password = "P@ssw0rd";
		
		//TODO Operacion 1
		(new Main()).execute(driver, url, username , password, new Work() {

			private static final String SQL = "SELECT COUNT(*) FROM employees";
		
			public void execute(Connection connection) throws SQLException {
				Statement statement = null;
				ResultSet rs = null;
				
				try {
					System.out.println(String.format("Query SQL: %s", SQL));
					
					statement = connection.createStatement();
					rs = statement.executeQuery(SQL);
					
					while (rs.next()) {
						System.out.println(String.format("Result: %s", rs.getString(1)));
					}
				
				} finally {
					if (rs != null) {
						rs.close();
					}
					if (statement != null) {
						statement.close();
					}
				}
				
			}
		});
		
		
	}
}
