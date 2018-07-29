package org.example.test.server.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

/*******************************************************************************
 * CLASE QUE IMPLEMENTA LAS OPERACIONES BASICAS DE CONEXIONES HTTP.
 * 
 * @author lacomision.org
 * @version 1.1
 * 
 ******************************************************************************/
public abstract class ServidorBaseFactory {
	
	/** Time-out por defecto. */
	public static final int TIME_OUT = 30;

	/** Atributo que define el log donde escribe la clase. */
	private Logger logger = Logger.getLogger(this.getClass());

	/** Puerto de Conexion TCP/IP. */
	private int puerto;

	/** Tiempo de Espera de la Peticion HTTP. */
	private int timeOut = TIME_OUT;

	/** Atributo que define la conexion de escucha de peticiones HTTP. */
	private ServerSocket servidor;

	/**
	 * Ruta de los Archivos del Servidor preparados para enviar como respuesta a
	 * las peticiones.
	 */
	private String rutaArchivos;

	/**
	 * Nombre del Archivo por defecto que se enviara al cliente ante una
	 * respuesta sin especificar el archivo.
	 */
	private String nombreArchivoDefault;

	/**
	 * Atributo que define las conexiones entrantes con las peticiones del
	 * cliente HTTP.
	 */
	protected Socket cliente;

	/**
	 * CONSTRUCTOR DE LA CLASE.
	 * 
	 */
	public ServidorBaseFactory() {

	}

	/***************************************************************************
	 * Metodo que inicia la conexion de escucha para recibir peticiones.
	 * 
	 * @throws IOException
	 *             Error producido por el intento de uso de un puerto fisico del
	 *             puerto ocupado por otro proceso.
	 **************************************************************************/
	public void iniciar() throws IOException {
		try {
			logger.debug("Abriendo puerto de escucha para peticiones.");
			servidor = new ServerSocket(puerto);

			while (true) {
				logger.debug("Esperando Conexiones.");
				cliente = servidor.accept();
				this.procesarPeticion(cliente);
			}
		} catch (IOException e) {
			throw e;
		}
	}

	/***************************************************************************
	 * Metodo que analiza la peticion realizada por el cliente y realiza las
	 * operaciones correspondentes.
	 * 
	 * @param socketCliente
	 *            Conexion Entrate con la peticion realizada.
	 **************************************************************************/
	protected abstract void procesarPeticion(Socket socketCliente);

	/***************************************************************************
	 * Metodo que para el servidor, cerrando los puertos de conexion.
	 * 
	 * @throws IOException
	 *             Error producido por el intento de liberacion del puerto
	 *             fisico.
	 **************************************************************************/
	public void detener() throws IOException {
		try {
			servidor.close();
			cliente.close();
		} catch (IOException e) {
			throw e;
		}
	}

	/***************************************************************************
	 * Obtiene el Puerto de Comunicaciones.
	 * 
	 * @return Entero con el puerto de Comunicaciones.
	 **************************************************************************/
	public final int getPuerto() {
		return puerto;
	}

	/***************************************************************************
	 * Metodo que establece el puerto de comunicaciones.
	 * 
	 * @param puertoCom
	 *            Entero con el puerto.
	 **************************************************************************/
	public final void setPuerto(final int puertoCom) {
		this.puerto = puertoCom;
	}

	/***************************************************************************
	 * Metodo que Obtiene el TimeOut del Servidor.
	 * 
	 * @return Timeout en segundos.
	 **************************************************************************/
	public final int getTimeOut() {
		return timeOut;
	}

	/***************************************************************************
	 * Metodo que establece el timeout del servidor.
	 * 
	 * @param nuevoTimeOut
	 *            Timeout en segundos.
	 **************************************************************************/
	public final void setTimeOut(final int nuevoTimeOut) {
		this.timeOut = nuevoTimeOut;
	}

	/***************************************************************************
	 * Metodo que obtiene la ruta de disco duro, donde se encuentran los
	 * archivos del servidor.
	 * 
	 * @return Directorio del Disco Duro.
	 **************************************************************************/
	public final String getRutaArchivos() {
		return rutaArchivos;
	}

	/***************************************************************************
	 * Establece la ruta del disco duro donde se encuentran los archivos de
	 * respuesta a peticiones.
	 * 
	 * @param nuevaRuta
	 *            Directorio del Disco Duro.
	 **************************************************************************/
	public final void setRutaArchivos(final String nuevaRuta) {
		this.rutaArchivos = nuevaRuta;
	}

	/***************************************************************************
	 * Metodo que obtiene nombre del archivo por defecto que se enviara al
	 * cliente, en el caso, de que no se especifique archivo.
	 * 
	 * @return Nombre del Archivo (con extension incluida)
	 **************************************************************************/
	public final String getNombreArchivoDefault() {
		return nombreArchivoDefault;
	}

	/***************************************************************************
	 * Establece el nombre del archivo que por defecto se servira ante una
	 * peticion sin especificar archivo.
	 * 
	 * @param nuevoNombreArchivo
	 *            Nombre del Archivo (con extension incluida)
	 **************************************************************************/
	public final void setNombreArchivoDefault(final String nuevoNombreArchivo) {
		this.nombreArchivoDefault = nuevoNombreArchivo;
	}

	
}
