package org.example.test.fwk.utilidades;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*******************************************************************************
 * CLASE QUE CONTROLA LA VALIDACION DE VALORES, EN DIFERENTES FORMATOS O TIPOS
 * DE DATOS.
 * 
 * @author jizquierdo
 * @version 1.0
 * 
 ******************************************************************************/
public class Validacion {

	/** Letras validas en la composicion de un NIF */
	private static final String NIF_STRING_ASOCIATION = "TRWAGMYFPDXBNJZSQVHLCKET";

	/**
	 * Patron basico de definicion de un numero en formato NIF. Este patron no
	 * valida que la letra de control sea correcta.
	 */
	private static final String PATRON_NIF = "[0-9]{7,8}(-[A-Z])?";

	/***************************************************************************
	 * Metodo que obtiene el numero NIF a partir del DNI. Es decir, calcula la
	 * letra de control a partir del DNI, y se lo a�ade al DNI en el formato
	 * <code>NNNNNNNN-L</code>.
	 * 
	 * @param dni
	 *            dni al que se quiere a�adir la letra del NIF
	 * @return NIF completo.
	 **************************************************************************/
	public static String dniToNif(String dni) {
		return String.valueOf(dni) + "-" + getLetraNIF(dni);
	}

	/***************************************************************************
	 * Comprueba si una cadena contiene una direccion IP que sigue la version 4
	 * del protocolo.
	 * 
	 * @param cadena
	 *            cadena con la direccion IP
	 * @return <code>true</code> Direccion IP Valida. <code>false</code>
	 *         Direccion IP Invalida.
	 **************************************************************************/
	public static boolean esDireccionIP(String cadena) {

		if (cadena.toCharArray().length < 7) {
			return false;
		}
		StringTokenizer tokens = new StringTokenizer(cadena, ".");
		if (tokens.countTokens() == 4) {
			while (tokens.hasMoreTokens()) {
				String grupo = tokens.nextToken();
				try {
					Integer valor = new Integer(grupo);
					if (valor.intValue() > 255) {
						return false;
					}
				} catch (NumberFormatException e) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	/***************************************************************************
	 * Metodo que analiza si una cadena de texto sigue el formato de una
	 * direccion de correo electronico, segun la especificacion RFC 2822.
	 * 
	 * @param cadena
	 *            cadena de texto con la direccion e-mail
	 * @return <code>true</code> Formato de la Direccion Valido.
	 *         <code>false</code> Formato de la direccion Invalido.
	 **************************************************************************/
	public static boolean esEmail(String cadena) {
		/*
		 * Definicion de Tokens RFC 2822 para la validacion de e-mails. Solo
		 * usar junto al objeto <code>java.util.regex.Pattern</code>.
		 */
		String sp = "\\!\\#\\$\\%\\&\\'\\*\\+\\-\\/\\=\\?\\^\\_\\`\\{\\|\\}\\~";

		/*
		 * Definicion de Tokens RFC 2822 para la validacion de e-mails. Solo
		 * usar junto al objeto <code>java.util.regex.Pattern</code>.
		 */
		String atext = "[a-zA-Z0-9" + sp + "]";

		/*
		 * Definicion de Tokens RFC 2822 para la validacion de e-mails. Solo
		 * usar junto al objeto <code>java.util.regex.Pattern</code>: (one or
		 * more atext chars).
		 */
		String atom = atext + "+";

		/*
		 * Definicion de Tokens RFC 2822 para la validacion de e-mails. Solo
		 * usar junto al objeto <code>java.util.regex.Pattern</code>.
		 */
		String dotAtom = "\\." + atom;

		/*
		 * Definicion de Tokens RFC 2822 para la validacion de e-mails. Solo
		 * usar junto al objeto <code>java.util.regex.Pattern</code>: (one
		 * atom followed by 0 or more dotAtoms).
		 */
		String localPart = atom + "(" + dotAtom + ")*";

		/* Tokens definidos por RFC 1035 para los nombres de dominio. */
		String letDig = "[a-zA-Z0-9]";

		/* Tokens definidos por RFC 1035 para los nombres de dominio. */
		String letDigHyp = "[a-zA-Z0-9-]";

		/* Tokens definidos por RFC 1035 para los nombres de dominio. */
		String letter = "[a-zA-Z]";

		/* Tokens definidos por RFC 1035 para los nombres de dominio. */
		String rfcLabel = letDig + "(" + letDigHyp + "{0,61}" + letDig + ")?";

		/* Tokens definidos por RFC 1035 para los nombres de dominio. */
		String domain = rfcLabel + "(\\." + rfcLabel + ")*\\." + letter
				+ "{2,6}";

		/*
		 * Combinacion de los Tokens de las especificaciones RFC 2822 y RFC
		 * 1035, para definir el formato de un e-mail segun la especificacion
		 * RFC 2822
		 */
		String addrSpec = "^" + localPart + "@" + domain + "$";

		/*
		 * Constante que define el patron de validacion que se debe seguir para
		 * validar un e-mail, segun la especificacion RFC 2822.
		 */
		Pattern PATRON_EMAIL = Pattern.compile(addrSpec);
		return (cadena != null) && PATRON_EMAIL.matcher(cadena).matches();
	}

	/***************************************************************************
	 * Metodo que valida una cadena de texto en formato NIF (o DNI, en teoria el
	 * DNI es la cadena numerica sin la letra posterior). El formato que debe
	 * seguir la cadena es <code>NNNNNNNN-L</code>: Una cadena de entre 7 u 8
	 * caracteres numericos, seguidos de un guion y una letra valida-
	 * 
	 * @param cadena
	 *            Cadena de texto en formato NIF.
	 * @return <code>true</code> NIF valido. <code>false</code> NIF no
	 *         valido.
	 **************************************************************************/
	public static boolean esNIF(String cadena) {
		Pattern patron = Pattern.compile(PATRON_NIF);
		Matcher encaja = patron.matcher(cadena);

		if (encaja.matches()) {
			String letra = cadena.substring(cadena.length() - 1, cadena
					.length());
			String dni = cadena.substring(0, cadena.length() - 2);

			String letraValida = getLetraNIF(dni);
			if (letraValida.equalsIgnoreCase(letra)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/***************************************************************************
	 * Comprueba si un numero esta en formato <code>java.lang.Double</code>.
	 * 
	 * @param cadena
	 *            Cadena Numerica
	 * @return <code>true</code> Numero Valido. <code>false</code> Numero No
	 *         Valido.
	 **************************************************************************/
	public static boolean esNumeroDecimal(String cadena) {
		if (cadena == null || cadena.equals("")) {
			return false;
		} else {
			try {
				Double.parseDouble(cadena);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
	}

	/***************************************************************************
	 * Comprueba si un numero esta en formato <code>java.lang.Integer</code>.
	 * 
	 * @param cadena
	 *            Cadena Numerica
	 * @return <code>true</code> Numero Valido. <code>false</code> Numero No
	 *         Valido.
	 **************************************************************************/
	public static boolean esNumeroEntero32(String cadena) {
		if (cadena == null || cadena.equals("")) {
			return false;
		} else {
			try {
				Integer.parseInt(cadena);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
	}

	/***************************************************************************
	 * Comprueba si un numero esta en formato <code>java.lang.Long</code>.
	 * 
	 * @param cadena
	 *            Cadena Numerica
	 * @return <code>true</code> Numero Valido. <code>false</code> Numero No
	 *         Valido.
	 **************************************************************************/
	public static boolean esNumeroEntero64(String cadena) {
		if (cadena == null || cadena.equals("")) {
			return false;
		} else {
			try {
				Long.parseLong(cadena);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
	}

	/***************************************************************************
	 * Metodo que obtiene la letra de control de un NIF.
	 * 
	 * @param dni
	 *            Cadena de texto con el DNI. El DNI, es la parte numerica del
	 *            NIF.
	 * @return Cadena de texto con un caracter. El caracter es la letra de
	 *         control del NIF.
	 **************************************************************************/
	public static String getLetraNIF(String dni) {

		if (esNumeroEntero64(dni)) {

			return String.valueOf(NIF_STRING_ASOCIATION.charAt(Integer
					.parseInt(dni) % 23));
		} else {
			throw new NumberFormatException(
					"El parametro 'dni' debe ser un valor numerico de entre 7 y 8 digitos, enteros.");
		}

	}
}
