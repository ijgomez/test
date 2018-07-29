package org.example.test.jdbcpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class JDBCConnectionPool extends ObjectPool {
	
	private static Logger LOGGER = Logger.getLogger(JDBCConnectionPool.class);

	private String url;
	
	private String user;
	
	private String password;

	public JDBCConnectionPool(String driver, String dsn, String usr, String pwd) {
		try {
			Class.forName(driver).newInstance();
		} catch (Exception e) {
			LOGGER.error("Fallo al registar el driver de base de datos", e);
		}
		this.url = dsn;
		this.user = usr;
		this.password = pwd;
	}

	protected Object create() {
		try {
			return (DriverManager.getConnection(url, user, password));
		} catch (SQLException e) {
			LOGGER.error("Fallo al obtener nueva conexion de base de datos", e);
			return null;
		}
	}

	protected boolean validate(Object o) {
		try {
			return (!((Connection) o).isClosed());
		} catch (SQLException e) {
			LOGGER.error("Fallo al comprobar el estado de la conexion", e);
			return (false);
		}
	}

	protected void expire(Object o) {
		try {
			((Connection) o).close();
		} catch (SQLException e) {
			LOGGER.error("Fallo al cerrar la conexion", e);
		}

	}

	public Connection borrowConnection() {
		return ((Connection) super.checkOut());
	}

	public void returnConnection(Connection c) {
		super.checkIn(c);
	}
}
