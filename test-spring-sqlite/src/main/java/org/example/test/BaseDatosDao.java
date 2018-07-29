package org.example.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class BaseDatosDao extends JdbcDaoSupport {

	private static class FicheroMapper implements RowMapper<Fichero> {

		@Override
		public Fichero mapRow(ResultSet rs, int rowNum) throws SQLException {
			Fichero fichero = new Fichero();
			fichero.setId(rs.getLong("id"));
			fichero.setFileName(rs.getString("fileName"));
			fichero.setPath(rs.getString("path"));
			fichero.setExtension(rs.getString("extension"));
			fichero.setFxCreationFile(rs.getDate("fxCreationFile"));
			//CheckSum
			fichero.setMediaInfo(rs.getString("mediaInfo"));
			fichero.setLength(rs.getLong("LENGTH"));
			
			//TODO

			return fichero;
		}
		
	}
	
	public List<Fichero> findAll(){
		
		List<Fichero> lista = new ArrayList<Fichero>();
		
		String sql = "SELECT id, fileName, path, extension, fxCreationFile, checksum, mediaInfo, LENGTH FROM TB_FICHEROS";
		
		try {
		lista = super.getJdbcTemplate().query(sql, new FicheroMapper());
		} catch (EmptyResultDataAccessException e){
			throw e;
		}
		return lista;
	}
	
	public void insert(Fichero fichero) throws DataAccessException {
	
		String sql = "INSERT INTO TB_FICHEROS (fileName, path, extension, fxCreationFile, mediaInfo, LENGTH) VALUES (?, ?, ?, ?, ?, ?)";
		
		Object[] args = {fichero.getFileName(), fichero.getPath(), fichero.getExtension(), fichero.getFxCreationFile(), fichero.getMediaInfo(), fichero.getLength()};
		
		int[] argTypes = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.VARCHAR, Types.NUMERIC};
		
		super.getJdbcTemplate().update(sql, args, argTypes);
	}
}
