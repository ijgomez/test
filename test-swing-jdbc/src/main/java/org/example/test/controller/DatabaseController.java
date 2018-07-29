package org.example.test.controller;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.bind.JAXBException;

import org.example.test.helpers.XMLHelper;
import org.example.test.model.ConnectionData;
import org.example.test.views.components.ApplicationLogComponent;

public class DatabaseController {
	
	private Logger logger = Logger.getLogger(DatabaseController.class.getName());

	public void executeQuery(ConnectionData connectionData, List<String> queries, ApplicationLogComponent logView) throws SQLException {
		Connection connection = null;
		try {
			logView.info("Configurando driver ...");
			Class.forName(connectionData.getDriverName());
			
			logView.info("Estableciendo conexion ...");
			connection = DriverManager.getConnection(connectionData.getUrl(), connectionData.getUsername(), connectionData.getPassword());
			for (String sql : queries) {
				try {
					if (sql.toUpperCase().startsWith("SELECT")) {
						this.executeSelectQuery(connection, sql, logView);
					} else {
						this.executeUpdateQuery(connection, sql, logView);
					}
				} catch (Exception e) {
					logView.error(e);
				}
			}
		
		} catch (Exception e) {
			logView.error("... error: " + e.getMessage());
		} finally {
			if (connection != null) {
				logView.info("...cerrando conexion");
				connection.close();
			}
		}
	}
	
	private void executeSelectQuery(Connection connection, String sql, ApplicationLogComponent logView) throws SQLException {
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		List<Object> data;
		try {
			logView.info("ejecutando ..." + sql);
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			
			
			ResultSetMetaData metaData = resultSet.getMetaData();

        	
        		List<String> md = new ArrayList<String>();
	        	for (int i = 1; i <= metaData.getColumnCount(); i++) {
	        		md.add(metaData.getColumnName(i));
				}
	        	logView.info(md.toString());
        	
        	
	        while (resultSet.next()) {
	        	
	        	
	        	data = new ArrayList<Object>();
	        	for (int i = 1; i <= metaData.getColumnCount(); i++) {
					data.add(resultSet.getObject(i));
	        		
				}
	        	logView.info(data.toString());
	        	
	        		        	
	        }
		} catch (Exception e) {
			logView.error(e);
			
	    } finally {
	    	if (resultSet != null) { resultSet.close(); }
	        if (statement != null) { statement.close(); }
	    }
	}
	
	private void executeUpdateQuery(Connection connection, String sql, ApplicationLogComponent logView) throws SQLException {
		Statement statement = null;
		ResultSet resultSet = null;
		int result = 0;
		try {
			logView.info("ejecutando ..." + sql);
			
			statement = connection.createStatement();
			result = statement.executeUpdate(sql);
			
			logView.info("Registros actualizados... "+  result);
		} catch (Exception e) {
			logView.error(e);
			
	    } finally {
	    	if (resultSet != null) { resultSet.close(); }
	        if (statement != null) { statement.close(); }
	    }
	}
	
	public void saveConnectionDataFile(File file, ConnectionData connectionData) throws JAXBException {
		logger.info("saveConnectionData...");
		XMLHelper.marshal(connectionData, file);
	}
	
	public ConnectionData openConnectionDataFile(File file) throws JAXBException {
		logger.info("openConnectionData...");
		return XMLHelper.unmarshal(file, ConnectionData.class);
	}
	
//	public List<RespuestaDS> execute(Connection connection, List<String> queries) {
//
//		List<RespuestaDS> results;
//		
//		
//		results = new ArrayList<RespuestaDS>();
//		for (String sql : queries) {
//			LOGGER.debug("User Query: {}", sql);
//			try {
//				if (sql.toUpperCase().startsWith("SELECT")) {
//					Object[] r = this.executeRS(connection, sql);
//					if (limit > 0 && (r.length > limit + 1)) {
//						LOGGER.warn("Reached limit ({}) results.", limit);
//						
//						results.add(new RespuestaDS(sql, Arrays.copyOfRange(r, 0, limit+1), true));
//					} else {
//						results.add(new RespuestaDS(sql, r));
//					}
//				} else {
//					int r = this.executeInt(connection, sql);
//					results.add(new RespuestaDS(sql, Integer.valueOf(r)));
//				}
//			} catch (SQLException e) {
//				results.add(new RespuestaDS(sql, e));
//			}
//
//		}
//		
//		return results;
//	}
//
//	
//
//	private Object[] executeRS(Connection connection, String sql) throws SQLException {
//		Statement statement = null;
//		ResultSet resultSet = null;
//		List<Object[]> results = null;
//		List<Object> data;
//		try {
//			statement = connection.createStatement();
//			resultSet = statement.executeQuery(sql);
//			results = new ArrayList<Object[]>();
//			
//			
//			ResultSetMetaData metaData = resultSet.getMetaData();
//
//        	if (results.isEmpty()) {
//        		List<String> md = new ArrayList<String>();
//	        	for (int i = 1; i <= metaData.getColumnCount(); i++) {
//	        		md.add(metaData.getColumnName(i));
//				}
//	        	results.add(md.toArray(new String[md.size()]));
//        	}
//			
//	        while (resultSet.next()) {
//	        	
//	        	
//	        	data = new ArrayList<Object>();
//	        	for (int i = 1; i <= metaData.getColumnCount(); i++) {
//					data.add(resultSet.getObject(i));
//	        		
//				}
//	        	results.add(data.toArray(new Object[data.size()]));
//	        	
//	        }
//
//	    } finally {
//	    	if (resultSet != null) { resultSet.close(); }
//	        if (statement != null) { statement.close(); }
//	    }
//		
//		return results.toArray(new Object[results.size()]);
//	}
//	
//	
//	public void executeScriptSQL(Connection connection, String fileName) throws ClassNotFoundException, SQLException {
//
//		try {
//			PrintWriter fw = new PrintWriter("target/test.sql.result");
//			
//			// Initialize object for ScripRunner
//			ScriptRunner runner = new ScriptRunner(connection);
//			runner.setAutoCommit(false);
//			runner.setStopOnError(false);
////			runner.setDelimiter("]");
////			runner.setLogWriter(null);
//			runner.setLogWriter(fw);
////			runner.setErrorLogWriter(null);
//			
//			runner.setErrorLogWriter(fw);
//
//			
//			// Give the input file to Reader
//			Reader reader = new BufferedReader(new FileReader(fileName));
// 
//			// Exctute script
//			runner.runScript(reader);
// 
//		} catch (Exception e) {
//			System.err.println("Failed to Execute" + fileName + " The error is " + e.getMessage());
//		}
//	}

}
