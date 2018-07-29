package org.example.test.server.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/*******************************************************************************
 * CLASE QUE OBTIENE DE UN ARCHIVO DE PROPIEDADES INFORMACION.
 * 
 * @author lacomision.org
 * @version 1.4
 * 
 ******************************************************************************/
public class Propiedades {

	/**
	 * Constante que contiene el nombe base del fichero de propiedades aceptado
	 * por el metodo {@link ResourceBundle#getBundle(String)}.
	 */
	public static final String BASE_NAME = "servidor";
	
	/**
	 * Atributo estatico que hace referencia al fichero de propiedades de la
	 * aplicacion, permitiendo las operaciones sobre el fichero.
	 */
	private static ResourceBundle bundle = ResourceBundle.getBundle(BASE_NAME);
	
	/***************************************************************************
	 * Metodo que obtiene informacion del fichero de propiedades a partir de una
	 * clave. El Fichero de Propiedades vendra definida por al constante
	 * {@link Propiedades#BASE_NAME}.
	 * 
	 * @param key
	 *            Clave que define la informacion que se quiere obtener.
	 * @return Informacion en formato {@link String}.
	 **************************************************************************/
	public static String getValor(final String key) {
		return Propiedades.getValor(bundle, key);
	}

	/***************************************************************************
	 * Metodo que obtiene informacion del fichero de propiedades a partir de una
	 * clave.
	 * 
	 * @param recurso
	 *            Nombre del Fichero de Propiedades.
	 * @param key
	 *            Clave que define la informacion que se quiere obtener.
	 * @return Informacion en formato {@link String}.
	 **************************************************************************/
	public static String getValor(final String recurso, final String key) {
		return Propiedades.getValor(ResourceBundle.getBundle(recurso), key);
	}

	/***************************************************************************
	 * Metodo que obtiene informacion del fichero de propiedades a partir de una
	 * clave.
	 * 
	 * @param bundle
	 *            Atributo estatico que hace referencia al fichero de
	 *            propiedades de la aplicacion, permitiendo las operaciones
	 *            sobre el fichero.
	 * @param key
	 *            Clave que define la informacion que se quiere obtener.
	 * @return Informacion en formato {@link String}.
	 **************************************************************************/
	private static String getValor(final ResourceBundle bundle, final String key) {
		return bundle.getString(key);
	}

	/***************************************************************************
	 * Metodo que obtiene informacion del fichero de propiedades a partir de una
	 * clave. El Fichero de Propiedades vendra definida por al constante
	 * {@link Propiedades#BASE_NAME}.
	 * 
	 * @param key
	 *            Clave que define la informacion que se quiere obtener.
	 * @return Informacion en formato <code>int</code>.
	 **************************************************************************/
	public static int getValorEntero32(final String key) {
		return Propiedades.getValorEntero32(bundle, key);
	}

	/***************************************************************************
	 * Metodo que obtiene informacion del fichero de propiedades a partir de una
	 * clave.
	 * 
	 * @param recurso
	 *            Nombre del Fichero de Propiedades.
	 * @param key
	 *            Clave que define la informacion que se quiere obtener.
	 * @return Informacion en formato <code>int</code>.
	 **************************************************************************/
	public static int getValorEntero32(final String recurso, final String key) {
		return Propiedades.getValorEntero32(ResourceBundle.getBundle(recurso), key);
	}
	
	/***************************************************************************
	 * Metodo que obtiene informacion del fichero de propiedades a partir de una
	 * clave.
	 * 
	 * @param bundle
	 *            Atributo estatico que hace referencia al fichero de
	 *            propiedades de la aplicacion, permitiendo las operaciones
	 *            sobre el fichero.
	 * @param key
	 *            Clave que define la informacion que se quiere obtener.
	 * @return Informacion en formato <code>int</code>.
	 **************************************************************************/
	private static int getValorEntero32(final ResourceBundle bundle, final String key) {
		return Integer.parseInt(bundle.getString(key));
	}
	
	/***************************************************************************
	 * Metodo que obtiene informacion del fichero de propiedades a partir de una
	 * clave. El Fichero de Propiedades vendra definida por al constante
	 * {@link Propiedades#BASE_NAME}.
	 * 
	 * @param key
	 *            Clave que define la informacion que se quiere obtener.
	 * @return Informacion en formato <code>long</code>.
	 **************************************************************************/
	public static long getValorEntero64(final String key) {
		return Propiedades.getValorEntero64(bundle, key);
	}
	
	/***************************************************************************
	 * Metodo que obtiene informacion del fichero de propiedades a partir de una
	 * clave.
	 * 
	 * @param recurso
	 *            Nombre del Fichero de Propiedades.
	 * @param key
	 *            Clave que define la informacion que se quiere obtener.
	 * @return Informacion en formato <code>long</code>.
	 **************************************************************************/
	public static long getValorEntero64(final String recurso, final String key) {
		return Propiedades.getValorEntero64(ResourceBundle.getBundle(recurso), key);
	}
	
	/***************************************************************************
	 * Metodo que obtiene informacion del fichero de propiedades a partir de una
	 * clave.
	 * 
	 * @param bundle
	 *            Atributo estatico que hace referencia al fichero de
	 *            propiedades de la aplicacion, permitiendo las operaciones
	 *            sobre el fichero.
	 * @param key
	 *            Clave que define la informacion que se quiere obtener.
	 * @return Informacion en formato <code>int</code>.
	 **************************************************************************/
	private static long getValorEntero64(final ResourceBundle bundle, final String key) {
		return (new Long(bundle.getString(key))).longValue();
	}
	
	/***************************************************************************
	 * Metodo que obtiene una lista de valores a partir de una lista de
	 * propiedades definidas. La listas de propiedades deben temer un formato
	 * siguiente: <br/> <code>keyDeLaLista.1 = valor</code><br/>
	 * <code>keyDeLaLista.2 = valor</code><br/>
	 * <code>keyDeLaLista.3 = valor</code><br/>
	 * 
	 * @param initKey
	 *            Parte comun de las propiedades que formen la lista. Por
	 *            Ejemplo: <code>keyDeLaLista</code>
	 * @return Lista de los valores que forman la lista de Key. Por ejemplo, una
	 *         lista de servidores.
	 **************************************************************************/
	public static List<?> getValores(final String initKey) {
		return Propiedades.getValores(bundle, initKey);
	}
	
	/***************************************************************************
	 * Metodo que obtiene una lista de valores a partir de una lista de
	 * propiedades definidas. La listas de propiedades deben temer un formato
	 * siguiente: <br/> <code>keyDeLaLista.1 = valor</code><br/>
	 * <code>keyDeLaLista.2 = valor</code><br/>
	 * <code>keyDeLaLista.3 = valor</code><br/>
	 * 
	 * @param recurso
	 *            Nombre del Fichero de Propiedades.
	 * @param initKey
	 *            Parte comun de las propiedades que formen la lista. Por
	 *            Ejemplo: <code>keyDeLaLista</code>
	 * @return Lista de los valores que forman la lista de Key. Por ejemplo, una
	 *         lista de servidores.
	 **************************************************************************/
	public static List<?> getValores(final String recurso, final String initKey) {
		return Propiedades.getValores(ResourceBundle.getBundle(recurso), initKey);
	}
	
	/***************************************************************************
	 * Metodo que obtiene una lista de valores a partir de una lista de
	 * propiedades definidas. La listas de propiedades deben temer un formato
	 * siguiente: <br/> <code>keyDeLaLista.1 = valor</code><br/>
	 * <code>keyDeLaLista.2 = valor</code><br/>
	 * <code>keyDeLaLista.3 = valor</code><br/>
	 * 
	 * @param bundle
	 *            Atributo estatico que hace referencia al fichero de
	 *            propiedades de la aplicacion, permitiendo las operaciones
	 *            sobre el fichero.
	 * @param initKey
	 *            Parte comun de las propiedades que formen la lista. Por
	 *            Ejemplo: <code>keyDeLaLista</code>
	 * @return Lista de los valores que forman la lista de Key. Por ejemplo, una
	 *         lista de servidores.
	 **************************************************************************/
	private static List<?> getValores(final ResourceBundle bundle, final String initKey) {
		List<String> valores = new ArrayList<String>();
		
		for (int i = 0; true; i++) {
			String valor = bundle.getString(initKey + "." + (i + 1));
			if (valor == null) {
				break;
			} else {
				valores.add(valor);
			}
		}
		return valores;
	}
	
	/***************************************************************************
	 * CONSTRUCTOR DE LA CLASE.
	 * 
	 **************************************************************************/
	protected Propiedades() {
		super();
	}
}
