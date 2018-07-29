package org.example.test.server.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

/*******************************************************************************
 * CLASE QUE MODELA UNA PETICION HTTP, ASI COMO LAS ACCIONES CORRESPONDIENTES.
 * 
 * @author lacomision.org
 * @version 1.0
 * 
 ******************************************************************************/
public class PeticionHTTP {

	/** Constante que define el metodo de peticion <code>POST</code>. */
	public static final String METODO_POST = "POST";

	/** Constante que define el metodo de peticion <code>GET</code>. */
	public static final String METODO_GET = "GET";

	/** Constante que define el metodo de peticion <code>HEAD</code>. */
	public static final Object METODO_HEAD = "HEAD";

	/** Atributo que define el log donde escribe la clase. */
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Atributo que contiene el metodo por el cual se ha realizado la peticion.
	 * Valores: <code>GET</code> o <code>POST</code>
	 */
	private String metodo;

	/** Atributo con la URL solicitada por la peticion. */
	private String url;

	/** Atributo con los parametros de la peticion. */
	private HashMap<String, String> parametros;

	/** Atributo con los parametros contenidos en la cabecera. */
	private HashMap<String, String> cabecera;

	/** Atributo con la version del protocolo. */
	private String version;

	/***************************************************************************
	 * CONSTRUCTOR DE LA CLASE.
	 * 
	 **************************************************************************/
	public PeticionHTTP() {
		this.parametros = new HashMap<String, String>();
		this.cabecera = new HashMap<String, String>();
	}

	/***************************************************************************
	 * Metodo que analiza la peticion htttp recibida por un flujo de datos de
	 * entrada.
	 * 
	 * @param flujoEntrada
	 *            Flujo de datos de entrada, donde esta contenida la peticion
	 *            HTTP.
	 * @throws IOException
	 *             Error de Entrada/Salida.
	 **************************************************************************/
	public final void analizando(final BufferedReader flujoEntrada)
			throws IOException {
		List<String> datos;
		String trama;
		StringTokenizer lineaPeticion;
		StringTokenizer listaParametro;
		StringTokenizer parametro;
		int entero;
		String key, valor;

		datos = new ArrayList<String>();

		try {
			logger.debug("Analizando Peticion HTTP.");

			logger
					.debug("Leyendo el socket hasta obtener una linea en blanco.");
			logger.debug("");
			trama = flujoEntrada.readLine();
			while (!trama.equals("")) {
				// logger.debug("\t" + trama);
				datos.add(trama);
				trama = flujoEntrada.readLine();
			}
			logger.debug("");

			/* Desmenuza la linea inicial de la peticion */
			trama = (String) datos.get(0);
			lineaPeticion = new StringTokenizer(trama, " ");

			/* Se obtiene el metodo de la peticion */
			metodo = lineaPeticion.nextToken();
			logger.debug("Metodo: " + metodo);

			/*
			 * Se obtiene la ruta local de la peticion y se almacenan los pares
			 * variable valor en una tabla hash.
			 */
			url = lineaPeticion.nextToken();
			logger.debug("Ruta Local Solicitada: " + url);

			logger.debug("Analizando los parametros de la peticion: ");
			entero = url.indexOf('?');
			if (metodo.equals(METODO_GET) && entero != -1) {
				/* Obtengo los parametros de la URL, situados posteriormente al ? */
				trama = url.substring(entero + 1, url.length() - 1);
				/* Limpiamos la ruta local de los parametros */
				url = url.substring(0, entero);

				/* Analizando los Parametros de la Peticion */
				listaParametro = new StringTokenizer(trama, "&");
				while (listaParametro.hasMoreTokens()) {
					trama = listaParametro.nextToken();
					parametro = new StringTokenizer(trama, "=");
					key = parametro.nextToken();
					if (parametro.hasMoreTokens()) {
						valor = parametro.nextToken();
					} else {
						valor = "";
					}
					logger.debug("\t Parametro: " + key + "=" + valor);
					parametros.put(key, valor);
				}
			}

			/* Se elimina la barra de la primera linea de la ruta local */
			if (url.charAt(0) == '/') {
				url = url.substring(1);
			}

			/*
			 * Se sustituyen las barras de la peticion por los separadores de
			 * carpetas del sistema operativo del servidor
			 */
			url = url.replace('/', File.separatorChar);

			/* Se obtiene el protocolo de la peticion */
			version = lineaPeticion.nextToken();

			/* Leyendo la cabecera de la peticion. */
			logger.debug("Analizando la cabecera de la peticion: ");
			for (entero = 1; entero < datos.size(); entero++) {
				trama = (String) datos.get(entero);
				parametro = new StringTokenizer(trama, ":");

				while (parametro.hasMoreTokens()) {
					key = "";
					valor = "";
					key = parametro.nextToken();
					if (parametro.hasMoreTokens()) {
						valor = parametro.nextToken();
						if (valor.charAt(0) == ' ') {
							valor = valor.substring(1, valor.length());
						}
					}
					logger.debug("\t Parametro: " + key + "=" + valor);
					cabecera.put(key, valor);
				}
			}

			/*
			 * Si el metodo es POST y existe cuerpo de mensaje lo lee del socket
			 * y lo separa en pares variable valor.
			 */
			trama = (String) cabecera.get("Content-Length");
			if (metodo.equals(METODO_POST) && trama != null) {
				logger.debug("Analizando datos propios de las peticiones del tipo POST.");

				/*
				 * Almacena el numero de caracteres a leer del socket es decir
				 * el numero de caracteres del mensaje y los lee del socket con
				 * un bucle for
				 */
				entero = 0;
				entero = Integer.parseInt(trama);
				trama = "";

				for (int i = 0; i < entero; i++) {
					trama += (char) flujoEntrada.read();
				}

				/* Separando los pares en key y valor */
				listaParametro = new StringTokenizer(trama, "&");
				while (listaParametro.hasMoreTokens()) {
					/* Separa las variables y los valores */
					key = "";
					valor = "";

					trama = listaParametro.nextToken();

					parametro = new StringTokenizer(trama, "=");
					key = parametro.nextToken();
					if (parametro.hasMoreTokens()) {
						valor = parametro.nextToken();
					}
					parametros.put(key, valor);
				}
			}

			logger.debug("Fin del Analisis de la peticion HTTP.");
		} catch (IOException e) {
			throw e;
		}
	}

	/***************************************************************************
	 * Metodo que obtiene todos los parametros definidos dentros de la cabecera
	 * HTTP.
	 * 
	 * @return Conjunto de Valores <code>(Key-Variable, Valor-Variable)</code>
	 **************************************************************************/
	public final HashMap<String, String> getCabecera() {
		return cabecera;
	}

	/***************************************************************************
	 * Metodo que obtiene el valor de una variable o parametro definidos dentro
	 * dentro de la cabecera.
	 * 
	 * @param key
	 *            Identificador de la Variable.
	 * @return Valor de la Variable.
	 **************************************************************************/
	public final String getValorParametroCabecera(final String key) {
		return cabecera.get(key);
	}

	/***************************************************************************
	 * Metodo que define los parametos de la peticion HTTP que iran posicionados
	 * dentro de la cabecera.
	 * 
	 * @param header
	 *            Conjunto de Variables.
	 *            <code>(Key-Variable, Valor-Variable)</code>
	 **************************************************************************/
	public final void setCabecera(final HashMap<String, String> header) {
		this.cabecera = header;
	}

	/***************************************************************************
	 * Metodo que obtiene el metodo de la peticion HTTP: <code>GET</code> o
	 * <code>POST</code>.
	 * 
	 * @return Posibles valores: <code>GET</code> o <code>POST</code>
	 **************************************************************************/
	public final String getMetodo() {
		return metodo;
	}

	/***************************************************************************
	 * Metodo que define el metodo de la peticion: <code>GET</code> o
	 * <code>POST</code>.
	 * 
	 * @param metod
	 *            Posibles valores: <code>GET</code> o <code>POST</code>
	 **************************************************************************/
	public final void setMetodo(final String metod) {
		this.metodo = metod;
	}

	/***************************************************************************
	 * Metodo que obtiene todos los parametros definidos dentros de la URL.
	 * 
	 * @return Conjunto de Valores <code>(Key-Variable, Valor-Variable)</code>
	 **************************************************************************/
	public final HashMap<String, String> getParametros() {
		return parametros;
	}

	/***************************************************************************
	 * Metodo que obtiene el valor de una variable o parametro definidos dentro
	 * dentro de la URL.
	 * 
	 * @param key
	 *            Identificador de la Variable.
	 * @return Valor de la Variable.
	 **************************************************************************/
	public final String getValorParametro(final String key) {
		return parametros.get(key);
	}

	/***************************************************************************
	 * Metodo que define los parametos de la peticion HTTP que iran posicionados
	 * dentro de la URL.
	 * 
	 * @param params
	 *            Conjunto de Variables.
	 *            <code>(Key-Variable, Valor-Variable)</code>
	 **************************************************************************/
	public final void setParametros(final HashMap<String, String> params) {
		this.parametros = params;
	}

	/***************************************************************************
	 * Metodo que obtiene la URL solicitada por la peticion.
	 * 
	 * @return URL
	 **************************************************************************/
	public final String getUrl() {
		return url;
	}

	/***************************************************************************
	 * Metodo que define la URL que solicita la peticion.
	 * 
	 * @param direccionURL
	 *            URL
	 **************************************************************************/
	public final void setUrl(final String direccionURL) {
		this.url = direccionURL;
	}

	/***************************************************************************
	 * Metodo que obtiene la version del protocolo HTTP.
	 * 
	 * @return Cadena con la Version del Protocolo.
	 **************************************************************************/
	public final String getVersion() {
		return version;
	}

	/***************************************************************************
	 * Metodo que define la version de protocolo HTTP.
	 * 
	 * @param vers
	 *            Cadena de Caracteres.
	 **************************************************************************/
	public final void setVersion(final String vers) {
		this.version = vers;
	}

}
