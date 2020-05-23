package org.example.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FileHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileHelper.class);

	public static void executeScriptSQL(Connection connection, String fileName) throws ClassNotFoundException, SQLException {

		try {
			// Initialize object for ScripRunner
			ScriptRunner runner = new ScriptRunner(connection);
			runner.setAutoCommit(true);
			runner.setStopOnError(false);
//			runner.setDelimiter("]");
//			runner.setLogWriter(null);
			runner.setLogWriter(new LogPrintWriter(LOGGER));
//			runner.setErrorLogWriter(null);
			runner.setErrorLogWriter(new LogPrintWriter(LOGGER));
 
			// Give the input file to Reader
			Reader reader = new BufferedReader(new FileReader(fileName));
 
			// Exctute script
			runner.runScript(reader);
 
		} catch (Exception e) {
			System.err.println("Failed to Execute" + fileName + " The error is " + e.getMessage());
		}
	}
	
}
