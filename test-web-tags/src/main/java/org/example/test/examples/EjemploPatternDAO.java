package org.example.test.examples;

import java.sql.SQLException;

import org.example.test.examples.patterns.dao.FactoriaDAO;
import org.example.test.examples.patterns.dao.Tabla1DAO;
import org.example.test.examples.patterns.dao.Tabla2DAO;
import org.example.test.examples.patterns.dao.beans.Tabla1;

public class EjemploPatternDAO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Se crea la factoria DAO.
		FactoriaDAO factoria = FactoriaDAO.getFactoriaDAO(FactoriaDAO.NAME_DATA_BASE);
		
		
		Tabla1DAO tabla1 = factoria.getTabla1DAO();
		Tabla2DAO tabla2 = factoria.getTabla2DAO();
		
		
		try {
			tabla1.insert(new Tabla1(null, null, null));
			tabla1.find("criterio");
			tabla1.selectLIST("");
			tabla2.selectRS("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
