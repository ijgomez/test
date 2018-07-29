package org.example.test.server.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/*******************************************************************************
 * CLASE QUE DEFINE OPERACIONES ESPECIFICAS CON FECHAS.
 * 
 * 
 * @author lacomision.org
 * @version 2.1.0
 * 
 ******************************************************************************/
public final class Tiempo {

	/**
	 * Metodo que convierte una fecha en formato {@link Date} a {@link String}.
	 * 
	 * @param fecha
	 *            Fecha a convertir
	 * @param patron
	 *            Cadena que define el formato de la fecha. La sintaxis del
	 *            patron debe seguir la misma definia para el objeto
	 *            {@link SimpleDateFormat}.
	 * @return Fecha en formato {@link String}
	 */
	public static String toString(final Date fecha, final String patron) {
		SimpleDateFormat formato = new SimpleDateFormat(patron);
		return formato.format(fecha);
	}

	/**
	 * Constructor de la clase.
	 */
	private Tiempo() {
		super();
	}
}
