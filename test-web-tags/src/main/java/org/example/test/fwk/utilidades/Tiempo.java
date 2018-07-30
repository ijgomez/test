package org.example.test.fwk.utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/*******************************************************************************
 * CLASE QUE DEFINE OPERACIONES ESPECIFICAS CON FECHAS.
 * 
 * 
 * @author jizquierdo
 * @version 1.0
 * 
 ******************************************************************************/
public class Tiempo {

	/** Indicador de que la fecha esta en el Horario de Invierno */
	public static final String HORARIO_INVIERNO = "I";

	/** Indicador de que la fecha esta en el Horario de Verano */
	public static final String HORARIO_VERANO = "V";

	private static final String PATRON_DEFECTO = "dd/MM/yyyy HH:mm:ss";

	/***************************************************************************
	 * Metodo que compara de dos fechas.
	 * 
	 * @param fechaIni
	 *            Fecha a comparar con el formato
	 *            <code>dd/MM/yyyy HH:mm:ss</code>.
	 * @param fechaFin
	 *            Fecha a comparar con el formato
	 *            <code>dd/MM/yyyy HH:mm:ss</code>.
	 * @return Se podran dar los siguientes resultados: [0] fechaIni y fechaFin
	 *         son iguales [-1] fechaIni es anterior a fechaFin [1] fechaIni es
	 *         posterior a fechaFin.
	 **************************************************************************/
	public static int compararFechas(String fechaIni, String fechaFin) {
		return compararFechas(fechaIni, fechaFin, PATRON_DEFECTO);
	}

	/***************************************************************************
	 * Metodo que compara de dos fechas.
	 * 
	 * @param fechaIni
	 *            Primera Fecha a comparar.
	 * @param fechaFin
	 *            Segunda Fecha a comparar.
	 * @param patron
	 *            Cadena que define el formato de la fecha. La sintaxis del
	 *            patron debe seguir la misma definia para el objeto
	 *            <code>java.text.SimpleDateFormat</code>.
	 * @return Se podran dar los siguientes resultados: [0] fechaIni y fechaFin
	 *         son iguales [-1] fechaIni es anterior a fechaFin [1] fechaIni es
	 *         posterior a fechaFin.
	 **************************************************************************/
	public static int compararFechas(String fechaIni, String fechaFin, String patron) {
		SimpleDateFormat formato = new SimpleDateFormat(patron);
		Date fechaI, fechaF;

		try {
			fechaI = formato.parse(fechaIni);
			fechaF = formato.parse(fechaFin);
		} catch (ParseException e) {
			System.out
					.println("ERROR: Una de las dos fechas no cumple el formato especifico de la fecha.");
			return 0;
		}

		return fechaI.compareTo(fechaF);
	}

	/***************************************************************************
	 * Metodo que indica si el dia es un dia de cambio de hora (Day Save Time),
	 * para el cambion de horario de Invierno a Verano.
	 * 
	 * @param fecha
	 *            Fecha de la cual queremos saber el numero de Horas que tiene
	 *            el dia, que debe seguir el patron
	 *            <code>dd/MM/yyyy HH:mm:ss</code>.
	 * @return <code>true</code> Es Dia de Cambio de Horario I-V.
	 *         <code>false</code> No es dia de Cambio de Horario I-V.
	 **************************************************************************/
	public static boolean esDiaCambioHorarioIV(String fecha) {
		return esDiaCambioHorarioIV(fecha, PATRON_DEFECTO, TimeZone.getDefault());
	}

	/***************************************************************************
	 * Metodo que indica si el dia es un dia de cambio de hora (Day Save Time),
	 * para el cambion de horario de Invierno a Verano.
	 * 
	 * @param fecha
	 *            Fecha de la cual queremos saber el numero de Horas que tiene
	 *            el dia.
	 * @param patron
	 *            Cadena que define el formato de la fecha. La sintaxis del
	 *            patron debe seguir la misma definia para el objeto
	 *            <code>java.text.SimpleDateFormat</code>.
	 * @param zonaHoraria
	 *            Zona Horaria en la queremos realizar las operaciones.
	 * @return <code>true</code> Es Dia de Cambio de Horario I-V.
	 *         <code>false</code> No es dia de Cambio de Horario I-V.
	 **************************************************************************/
	public static boolean esDiaCambioHorarioIV(String fecha, String patron, TimeZone zonaHoraria) {
		if (getNumeroHorasDia(fecha, patron, zonaHoraria) == 23) {
			return true;
		} else {
			return false;
		}
	}

	/***************************************************************************
	 * Metodo que indica si el dia es un dia de cambio de hora (Day Save Time),
	 * para el cambion de horario de Verano a Invierno.
	 * 
	 * @param fecha
	 *            Fecha de la cual queremos saber el numero de Horas que tiene
	 *            el dia, que debe seguir el patron <code>dd/MM/yyyy HH:mm:ss</code>.
	 * @return <code>true</code> Es Dia de Cambio de Horario V-I.
	 *         <code>false</code> No es dia de Cambio de Horario V-I.
	 **************************************************************************/
	public static boolean esDiaCambioHorarioVI(String fecha) {
		return esDiaCambioHorarioVI(fecha, PATRON_DEFECTO, TimeZone.getDefault());
	}

	/***************************************************************************
	 * Metodo que indica si el dia es un dia de cambio de hora (Day Save Time),
	 * para el cambion de horario de Verano a Invierno.
	 * 
	 * @param fecha
	 *            Fecha de la cual queremos saber el numero de Horas que tiene
	 *            el dia.
	 * @param patron
	 *            Cadena que define el formato de la fecha. La sintaxis del
	 *            patron debe seguir la misma definia para el objeto
	 *            <code>java.text.SimpleDateFormat</code>.
	 * @param zonaHoraria
	 *            Zona Horaria en la queremos realizar las operaciones.
	 * @return <code>true</code> Es Dia de Cambio de Horario V-I.
	 *         <code>false</code> No es dia de Cambio de Horario V-I.
	 **************************************************************************/
	public static boolean esDiaCambioHorarioVI(String fecha, String patron, TimeZone zonaHoraria) {
		if (getNumeroHorasDia(fecha, patron, zonaHoraria) == 25) {
			return true;
		} else {
			return false;
		}
	}
	
	/***************************************************************************
	 * Metodo que especifica si la fecha pasada como parametro de entrada esta
	 * en el formato <code>dd/MM/yyyy HH:mm:ss</code>. Tambien identifica si
	 * los valores de dia, mes, a�o, hora,... estan acordes con la fecha (por
	 * ejemplo, que el mes de Febrero solo puede tener 28 dias).
	 * 
	 * @param cadena
	 *            Fecha a analizar en el formato
	 *            <code>dd/MM/yyyy HH:mm:ss</code>.
	 * @return <code>true</code>: La fecha es en el formato especificado.
	 *         <code>false</code>: La fecha no esta en ele formato
	 *         especificado.
	 **************************************************************************/
	public static boolean estaFormatoFecha(String cadena) {
		return estaFormatoFecha(cadena, PATRON_DEFECTO);
	}

	/***************************************************************************
	 * Metodo que especifica si la fecha esta en el formato. Tambien identifica
	 * si los valores de dia, mes, a�o, hora,... estan acordes con la fecha (por
	 * ejemplo, que el mes de Febrero solo puede tener 28 dias).
	 * 
	 * @param cadena
	 *            Fecha a analizar
	 * @param patron
	 *            Cadena que define el formato de la fecha. La sintaxis del
	 *            patron debe seguir la misma definia para el objeto
	 *            <code>java.text.SimpleDateFormat</code>.
	 * @return <code>true</code>: La fecha es en el formato especificado.
	 *         <code>false</code>: La fecha no esta en el formato
	 *         especificado.
	 **************************************************************************/
	public static boolean estaFormatoFecha(String cadena, String patron) {
		SimpleDateFormat formato = new SimpleDateFormat(patron);
		formato.setLenient(false);
		try {
			Date date = formato.parse(cadena);
			if (date == null) {
				return false;
			} else {
				return true;
			}
		} catch (ParseException e) {
			return false;
		}
	}

	/***************************************************************************
	 * Metodo que obtiene la fecha del dia actual siguiendo el formato:
	 * <code>dd/MM/yyyy HH:mm:ss</code>
	 * 
	 * @return Dia Actual en formato de Cadena.
	 **************************************************************************/
	public static String getHoy() {
		SimpleDateFormat formato = new SimpleDateFormat(PATRON_DEFECTO);
		return formato.format(new Date());
	}

	/***************************************************************************
	 * Metodo que obtiene la fecha del dia actual siguiendo un formato
	 * 
	 * @param patron
	 *            Cadena que define el formato de la fecha. La sintaxis del
	 *            patron debe seguir la misma definia para el objeto
	 *            <code>java.text.SimpleDateFormat</code>.
	 * @return Dia Actual en formato de Cadena.
	 **************************************************************************/
	public static String getHoy(String patron) {
		SimpleDateFormat formato = new SimpleDateFormat(patron);
		return formato.format(new Date());
	}

	/***************************************************************************
	 * Metodo que obtiene el numero de horas que tiene un dia especifico.
	 * Normalmente, se obtendra 24 horas, excepto los dias de cambio de hora, en
	 * el que se obtendra 23 horas el dia de cambio de horario de Invierno a
	 * Verano, y 25 horas el dia de cambio horario de Verano a Invierno. La Zona
	 * Horaria es la definida por defecto en la maquina donde se ejecuta la
	 * aplicacion.
	 * 
	 * @param fecha
	 *            Fecha en el formato <code>dd/MM/yyyy HH:mm:ss</code>, de la
	 *            cual queremos saber el numero de Horas que tiene el dia.
	 * @return <code>-1</code> ha habido algun problema. [<code>23</code>,
	 *         <code>24</code>, <code>25</code>] horas del dia.
	 **************************************************************************/
	public static int getNumeroHorasDia(String fecha) {
		return getNumeroHorasDia(fecha, PATRON_DEFECTO, TimeZone.getDefault());
	}

	/***************************************************************************
	 * Metodo que obtiene el numero de horas que tiene un dia especifico.
	 * Normalmente, se obtendra 24 horas, excepto los dias de cambio de hora, en
	 * el que se obtendra 23 horas el dia de cambio de horario de Invierno a
	 * Verano, y 25 horas el dia de cambio horario de Verano a Invierno.
	 * 
	 * @param fecha
	 *            Fecha de la cual queremos saber el numero de Horas que tiene
	 *            el dia.
	 * @param patron
	 *            Cadena que define el formato de la fecha. La sintaxis del
	 *            patron debe seguir la misma definia para el objeto
	 *            <code>java.text.SimpleDateFormat</code>.
	 * @param zonaHoraria
	 *            Zona Horaria en la queremos realizar las operaciones.
	 * @return <code>-1</code> ha habido algun problema. [<code>23</code>,
	 *         <code>24</code>, <code>25</code>] horas del dia.
	 **************************************************************************/
	public static int getNumeroHorasDia(String fecha, String patron, TimeZone zonaHoraria) {
		SimpleDateFormat formato = new SimpleDateFormat(patron);
		Date date;
		try {
			date = formato.parse(fecha);

			Calendar calendario1 = Calendar.getInstance(zonaHoraria);
			calendario1.setTime(date);
			calendario1.set(Calendar.HOUR_OF_DAY, 0);
			calendario1.set(Calendar.MINUTE, 0);
			calendario1.set(Calendar.SECOND, 0);
			calendario1.set(Calendar.MILLISECOND, 0);
			Calendar calendario2 = Calendar.getInstance(zonaHoraria);
			calendario2.setTime(date);
			calendario2.set(Calendar.HOUR_OF_DAY, 23);
			calendario2.set(Calendar.MINUTE, 59);
			calendario2.set(Calendar.SECOND, 59);
			calendario2.set(Calendar.MILLISECOND, 999);

			boolean esDiaCambioHorario1 = (zonaHoraria).inDaylightTime(calendario1.getTime());
			boolean esDiaCambioHorario2 = (zonaHoraria).inDaylightTime(calendario2.getTime());

			if (esDiaCambioHorario1 == esDiaCambioHorario2) {
				return 24;
			} else if (!esDiaCambioHorario1) {
				return 23;
			} else {
				return 25;
			}
		} catch (ParseException e) {
			System.err.println("ERROR: La fecha no cumple el formato especifico.");
			return -1;
		}
	}

	/***************************************************************************
	 * Metodo que devuelve un String que identifica el horario en la que se
	 * encuentra una fecha: Horario de Verano o Horario de Invierno.
	 * 
	 * @param fecha
	 *            fecha a identificar el tipo de horario. Formato de la fecha:
	 *            <code>dd/MM/yyyy HH:mm:ss</code>
	 * @return Devuelve los siguientes valores: <code>I</code> Horario de
	 *         Invierno. <code>V</code> Horario de Verano. <code>null</code>
	 *         Se han producido errores en el metodo.
	 **************************************************************************/
	public static String getTipoHorario(String fecha) {
		return getTipoHorario(fecha, PATRON_DEFECTO, TimeZone.getDefault());
	}

	/***************************************************************************
	 * Metodo que devuelve un String que identifica el horario en la que se
	 * encuentra una fecha: Horario de Verano o Horario de Invierno.
	 * 
	 * @param fecha
	 *            Fecha a identificar el tipo de horario.
	 * @param patron
	 *            Cadena que define el formato de la fecha. La sintaxis del
	 *            patron debe seguir la misma definia para el objeto
	 *            <code>java.text.SimpleDateFormat</code>.
	 * @param zonaHoraria
	 *            Zona Horaria en la queremos realizar las operaciones.
	 * @return Devuelve los siguientes valores: <code>I</code> Horario de
	 *         Invierno. <code>V</code> Horario de Verano. <code>null</code>
	 *         Se han producido errores en el metodo.
	 **************************************************************************/
	public static String getTipoHorario(String fecha, String patron, TimeZone zonaHoraria) {
		SimpleDateFormat formato = new SimpleDateFormat(patron);
		try {
			if (zonaHoraria.inDaylightTime(formato.parse(fecha))) {
				return HORARIO_VERANO;
			} else {
				return HORARIO_VERANO;
			}
		} catch (ParseException e) {
			System.err.println("ERROR: La fecha no cumple el formato especifico.");
			return null;
		}
	}
	
	/***************************************************************************
	 * Metodo que transforma una fecha en formato <code>java.lang.String</code>
	 * a <code>java.util.Calendar</code>. La Zona Horaria es la definida por
	 * defecto en la maquina donde se ejecuta la aplicacion.
	 * 
	 * @param fecha
	 *            Fecha a convertir. Formato de la fecha:
	 *            <code>dd/MM/yyyy HH:mm:ss</code>
	 * @return Fecha en el objeto <code>java.util.Calendar</code>
	 **************************************************************************/
	public static Calendar toCalendar(String fecha) {
		return toCalendar(fecha, PATRON_DEFECTO, TimeZone.getDefault());
	}

	/***************************************************************************
	 * Metodo que transforma una fecha en formato <code>java.lang.String</code>
	 * a <code>java.util.Calendar</code>
	 * 
	 * @param fecha
	 *            Fecha a convertir.
	 * @param patron
	 *            Cadena que define el formato de la fecha. La sintaxis del
	 *            patron debe seguir la misma definia para el objeto
	 *            <code>java.text.SimpleDateFormat</code>.
	 * @param zonaHoraria
	 *            Zona Horaria en la queremos realizar las operaciones.
	 * @return Fecha en el objeto <code>java.util.Calendar</code>
	 **************************************************************************/
	public static Calendar toCalendar(String fecha, String patron, TimeZone zonaHoraria) {
		SimpleDateFormat formato = new SimpleDateFormat(patron);
		try {
			Calendar calendario = Calendar.getInstance(zonaHoraria);
			calendario.setTime(formato.parse(fecha));
			return calendario;
		} catch (ParseException e) {
			System.err.println("ERROR: La fecha no cumple el formato especifico.");
			return null;
		}
	}

	/***************************************************************************
	 * Metodo que transforma una fecha en formato <code>java.lang.String</code>
	 * a <code>java.util.Date</code>.
	 * 
	 * @param fecha
	 *            Fecha en formato String, siguiendo el patron
	 *            <code>dd/MM/yyyy HH:mm:ss</code>.
	 * @return Fecha en el objeto <code>java.util.Date</code>
	 **************************************************************************/
	public static Date toDate(String fecha) {
		return toDate(fecha, PATRON_DEFECTO);
	}

	/***************************************************************************
	 * Metodo que transforma una fecha en formato <code>java.lang.String</code>
	 * a <code>java.util.Date</code>.
	 * 
	 * @param fecha
	 *            Fecha en formato String
	 * @param patron
	 *            Cadena que define el formato de la fecha. La sintaxis del
	 *            patron debe seguir la misma definia para el objeto
	 *            <code>java.text.SimpleDateFormat</code>.
	 * @return Fecha en el objeto <code>java.util.Date</code>
	 **************************************************************************/
	public static Date toDate(String fecha, String patron) {
		SimpleDateFormat formato = new SimpleDateFormat(patron);
		try {
			return formato.parse(fecha);
		} catch (ParseException e) {
			System.err.println("ERROR: La fecha no cumple el formato especifico.");
			return null;
		}
	}

	/***************************************************************************
	 * Metodo que convierte una fecha en formato <code>java.util.Date</code> a
	 * <code>java.lang.String</code>.
	 * 
	 * @param fecha
	 *            Fecha a convertir que debe seguir el patron
	 *            <code>dd/MM/yyyy HH:mm:ss</code>.
	 * @return Fecha en formato <code>java.lang.String</code>
	 **************************************************************************/
	public static String toString(Date fecha) {
		return toString(fecha, PATRON_DEFECTO);
	}

	/***************************************************************************
	 * Metodo que convierte una fecha en formato <code>java.util.Date</code> a
	 * <code>java.lang.String</code>.
	 * 
	 * @param fecha
	 *            Fecha a convertir
	 * @param patron
	 *            Cadena que define el formato de la fecha. La sintaxis del
	 *            patron debe seguir la misma definia para el objeto
	 *            <code>java.text.SimpleDateFormat</code>.
	 * @return Fecha en formato <code>java.lang.String</code>
	 **************************************************************************/
	public static String toString(Date fecha, String patron) {
		SimpleDateFormat formato = new SimpleDateFormat(patron);
		return formato.format(fecha);
	}
}
