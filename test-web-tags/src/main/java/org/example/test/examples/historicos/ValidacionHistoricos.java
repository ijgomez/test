package org.example.test.examples.historicos;

import org.example.test.fwk.utilidades.Tiempo;

public class ValidacionHistoricos {
	


	public static boolean evaluarHistorico(String valor1, String fxIni1, String fxFin1, String valor2, String fxIni2, String fxFin2) throws HistoricosException {
		int modificacion;
		String estadoIni;
		
		modificacion = getModificacion(valor1, fxIni1, fxFin1, valor2, fxIni2, fxFin2);
		
		if (modificacion != 0){
			estadoIni = getEstadoINI(valor1, fxIni1, fxFin1);
			
			if (estadoIni.equals("1") && modificacion == 1){
				return evaluarCasoE1M1();
			}
			if (estadoIni.equals("1") && modificacion == 2){
				
			}
		}
		
		throw new HistoricosException("HISTORICO", "Modificacion no contemplada en los Casos de Uso.");
	}
	
	private static boolean evaluarCasoE1M1(){
		return true;
	}
	
	
	private static int getModificacion(String valor1, String fxIni1, String fxFin1, String valor2, String fxIni2, String fxFin2)throws HistoricosException{
		if (valor1.equals(valor2) && fxIni1.equals(fxIni2) && fxFin1.equals(fxFin2)){
			return 0;
		}
		
		if (!valor1.equals(valor2) && fxIni1.equals(fxIni2) && fxFin1.equals(fxFin2)){
			return 1;
		}
		
		if (valor1.equals(valor2) && !fxIni1.equals(fxIni2) && fxFin1.equals(fxFin2)){
			return 2;
		}
		
		if (valor1.equals(valor2) && fxIni1.equals(fxIni2) && !fxFin1.equals(fxFin2)){
			return 3;
		}
		
		if (!valor1.equals(valor2) && !fxIni1.equals(fxIni2) && fxFin1.equals(fxFin2)){
			return 4;
		}
		
		if (!valor1.equals(valor2) && fxIni1.equals(fxIni2) && !fxFin1.equals(fxFin2)){
			return 5;
		}
		
		if (valor1.equals(valor2) && !fxIni1.equals(fxIni2) && !fxFin1.equals(fxFin2)){
			return 6;
		}
		
		if (!valor1.equals(valor2) && !fxIni1.equals(fxIni2) && !fxFin1.equals(fxFin2)){
			return 7;
		}
		throw new HistoricosException("MODIFICACION", "Modificacion no definida.");
	}
	
	private static String getEstadoINI(String valor, String fxIni, String fxFin) throws HistoricosException{
		
		String hoy = Tiempo.getHoy();
		
		if (fxIni.equals("1900") && fxFin.equals("9999")){
			return "1";
		}
		
		if (fxIni.equals("1900") && Tiempo.compararFechas(fxFin, hoy) < 0){
			return "2.1";
		}
		
		if (fxIni.equals("1900") && Tiempo.compararFechas(fxFin, hoy) >= 0){
			return "2.2";
		}
		
		if (fxFin.equals("9999") && Tiempo.compararFechas(fxIni, hoy) < 0){
			return "3.1";
		}
		
		if (fxFin.equals("9999") && Tiempo.compararFechas(fxIni, hoy) >= 0){
			return "3.2";
		}
		
		if (Tiempo.compararFechas(fxIni, hoy) < 0 && Tiempo.compararFechas(fxFin, hoy) < 0){
			return "4.1";
		}
		
		if (Tiempo.compararFechas(fxIni, hoy) < 0 && Tiempo.compararFechas(fxFin, hoy) >= 0){
			return "4.2";
		}
		
		if (Tiempo.compararFechas(fxIni, hoy) >= 0 && Tiempo.compararFechas(fxFin, hoy) >= 0){
			return "4.3";
		}
		throw new HistoricosException("ESTADO", "Estado no definido.");
	}
}
