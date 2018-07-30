package org.example.test.examples.patterns.dao;

import java.sql.Connection;

public class BaseDatosDAOFactoria extends FactoriaDAO {
	public static final String DRIVER = "";

	public static final String DBURL = "";

	/**
	 * method to create connections
	 * 
	 * @return
	 */
	public static Connection createConnection() {
		// Use DRIVER and DBURL to create a connection. Recommend connection
		// pool implementation/usage
		return null;
	}

	@Override
	public Tabla1DAO getTabla1DAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tabla2DAO getTabla2DAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
