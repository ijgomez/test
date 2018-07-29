package org.example.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Properties;

import org.apache.log4j.Logger;

/*******************************************************************************
 * CLASE QUE OBTIENE DE UN ARCHIVO DE PROPIEDADES DE LA APLICACION.
 * 
 * @author jizquierdo
 * @version 1.0
 * 
 ******************************************************************************/
public class RMIServerProperties {

	private static Logger logger = Logger.getLogger(RMIServerProperties.class);
	
	/**
	 * Atributo estatico que define el nombre y ubicacion del archivo de
	 * propiedades.
	 */
	private static Properties p = new Properties();
	
	public static void load(String file) throws IOException {
		try {
			p.load(new FileInputStream(new File(file)));
		} catch (FileNotFoundException e) {
			logger.error("No encuentra el archivo de propiedades. Ruta: " + file);
			throw e;
		} catch (IOException e) {
			logger.error("Error general de Entrada/Salida. Ruta: " + file);
			throw e;
		}
	}
	
	

	/***************************************************************************
	 * Metodo que obtiene informacion del fichero de propiedades a partir de una
	 * clave.
	 * 
	 * @param key
	 *            Clave que define la informacion que se quiere obtener.
	 * @return Informacion en formato <code>java.lang.String</code>.
	 **************************************************************************/
	public static String getPropiedad(final String key) {
		return p.getProperty(key);
	}

	/***************************************************************************
	 * Metodo que obtiene informacion del fichero de propiedades a partir de una
	 * clave.
	 * 
	 * @param key
	 *            Clave que define la informacion que se quiere obtener.
	 * @return Informacion en formato <code>int</code>.
	 **************************************************************************/
	public static int getPropiedadEntero32(final String key) {
		return Integer.parseInt(p.getProperty(key));
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
	public static List<String> getPropiedades(final String initKey) {
		String valor;
		List<String> valores = new ArrayList<String>();

		for (int i = 0; true; i++) {
			try {
				valor = p.getProperty(initKey + "." + (i + 1));
				if (valor != null) {
					valores.add(valor);
				}
			} catch (MissingResourceException mrEx) {
				break;
			}
		}
		return valores;
	}

}
