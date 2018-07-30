package org.example.test.examples.patterns.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import org.example.test.examples.patterns.dao.beans.ITabla;

public interface ITablaDAO {
	public int insert(ITabla tabla) throws SQLException;

	public int update(ITabla tabla, String where) throws SQLException;

	public ITabla find(String where) throws SQLException;

	public Vector selectVEC(String where) throws SQLException;
	
	public Collection selectCOLL(String where) throws SQLException;
	
	public ResultSet selectRS(String where) throws SQLException;

	public int delete(String where) throws SQLException;
}
