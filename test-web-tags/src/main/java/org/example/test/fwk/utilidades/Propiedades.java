package org.example.test.fwk.utilidades;

import java.util.ResourceBundle;

/*******************************************************************************
 * CLASE QUE OBTIENE DE UN ARCHIVO DE PROPIEDADES INFORMACION.
 * 
 * @author jizquierdo
 * @version 1.1
 * 
 ******************************************************************************/
public class Propiedades {

	private static ResourceBundle bundle = ResourceBundle
			.getBundle("org.framework.FrameWork");

	/***************************************************************************
	 * Metodo que obtiene informacion del fichero de propiedades a partir de una
	 * clave. El Fichero de Propiedades es el del Framework, con el nombre
	 * <code>org.framework.FrameWork</code>.
	 * 
	 * @param key
	 *            Clave que define la informacion que se quiere obtener.
	 * @return Informacion en formato <code>java.lang.String</code>.
	 **************************************************************************/
	public static String getValor(String key) {
		return bundle.getString(key);
	}

	/***************************************************************************
	 * Metodo que obtiene informacion del fichero de propiedades a partir de una
	 * clave.
	 * 
	 * @param recurso
	 *            Nombre del Fichero de Propiedades.
	 * @param key
	 *            Clave que define la informacion que se quiere obtener.
	 * @return Informacion en formato <code>java.lang.String</code>.
	 **************************************************************************/
	public static String getValor(String recurso, String key) {
		ResourceBundle bundle = ResourceBundle.getBundle(recurso);
		return bundle.getString(key);
	}

	/***************************************************************************
	 * Metodo que obtiene informacion del fichero de propiedades a partir de una
	 * clave. El Fichero de Propiedades es el del Framework, con el nombre
	 * <code>org.framework.FrameWork</code>.
	 * 
	 * @param key
	 *            Clave que define la informacion que se quiere obtener.
	 * @return Informacion en formato <code>int</code>.
	 **************************************************************************/
	public static int getValorEntero32(String key) {
		return Integer.parseInt(bundle.getString(key));
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
	public static int getValorEntero32(String recurso, String key) {
		ResourceBundle bundle = ResourceBundle.getBundle(recurso);
		return Integer.parseInt(bundle.getString(key));
	}
}
