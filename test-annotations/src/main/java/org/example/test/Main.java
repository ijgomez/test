package org.example.test;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Registro> registros = new ArrayList<Registro>();
		
		for (int i = 0; i < 100; i++) {
			registros.add(new Registro());
		}
		
		ExportacionService.INSTANCIA.exportar(registros);
		
	}

}
