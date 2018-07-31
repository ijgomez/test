package org.example.test.examples.patterns.dao;

public abstract class FactoriaDAO {
	/**
	 * Identificador de la base de datos o tipo de base de datos a la que
	 * apuntara la factoria DAO.
	 */
	public static final int NAME_DATA_BASE = 1;
	
	public abstract Tabla1DAO getTabla1DAO();
	public abstract Tabla2DAO getTabla2DAO();

	
	public static FactoriaDAO getFactoriaDAO(int factoria){
		switch (factoria){
		case NAME_DATA_BASE: return new BaseDatosDAOFactoria();
		default: return null;
		}
	}
}
