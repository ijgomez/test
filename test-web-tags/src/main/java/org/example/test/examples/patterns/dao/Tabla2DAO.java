package org.example.test.examples.patterns.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import org.example.test.examples.patterns.dao.beans.ITabla;

public class Tabla2DAO extends BaseDatosDAO implements ITablaDAO {

	public int delete(String where) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public ITabla find(String where) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public int insert(ITabla tabla) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public Collection selectCOLL(String where) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultSet selectRS(String where) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector selectVEC(String where) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public int update(ITabla tabla, String where) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
