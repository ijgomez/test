package org.example.test.server.core;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;

import org.apache.log4j.Logger;
import org.example.test.server.ServidorHTTP;
import org.example.test.server.utils.Tiempo;

/*******************************************************************************
 * CLASE QUE IMPLEMENTA LA CONEXIONES MEDIANTE SOCKETS PARA ESTABLECER CANALES
 * DE COMUNICACIONES DEL SERVIDOR HTTP.
 * 
 * @author lacomision.org
 * @version 1.0
 * 
 ******************************************************************************/
public class Conexion implements Runnable {

	/** Atributo que define el log donde escribe la clase. */
	private Logger logger = Logger.getLogger(this.getClass());

	/** Socket que establece la conexion con el cliente. */
	private Socket cliente;

	/** Hilo sobre el que se establecera la conexion. */
	private Thread hilo;

	/** Atributo que define las propiedades del servidor HTTP. */
	private ServidorHTTP servidorHTTP;

	/***************************************************************************
	 * CONSTRUCTOR DE LA CLASE.
	 * 
	 * @param client
	 *            Conexion con el cliente.
	 * @param httpServer
	 *            Servidor HTTP.
	 **************************************************************************/
	public Conexion(final Socket client, final ServidorHTTP httpServer) {
		this.cliente = client;
		this.servidorHTTP = httpServer;
		this.hilo = new Thread(this);
	}

	public synchronized void run() {

		BufferedReader flujoEntrada = null;
		PrintStream flujoSalida = null;
		boolean condicionSalida;
		double timeOut;
		PeticionHTTP peticion;
		String nombreArchivo;
		File solicitud;
		FileInputStream archivoSolicitado;
		int codigoRespuesta;
		String mensajeRespuesta;

		condicionSalida = true;
		timeOut = (double) servidorHTTP.getTimeOut();

		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		try {
			logger.debug("Estableciendo Flujos de Conexion.");
			flujoEntrada = new BufferedReader(new InputStreamReader(new BufferedInputStream(cliente.getInputStream())));
			flujoSalida = new PrintStream(cliente.getOutputStream());

			/*
			 * Repite el proceso hasta que haya que cerrar la conexion, eso
			 * puede pasar por dos motivos: Si la peticion es HTTP/1.0 se
			 * cerrara la conexion tras una peticion o El cliente solicita la
			 * desconexion.
			 */
			do {
				logger.debug("Leyendo Socket... y almacenando peticion.");

				while (!flujoEntrada.ready()) {
					/*
					 * Con esto se controla el timeout y se reduce el consumo de
					 * recursos considerablemente asi se pueden atender 4
					 * peticiones por segundo por cada cliente.
					 */
					Thread.sleep(250);
					timeOut = timeOut - 250.0 / 1000.0;
					if (timeOut <= 0.0) {
						condicionSalida = false;
						break;
					}
				}

				if (condicionSalida) {
					timeOut = (double) servidorHTTP.getTimeOut();
				} else {
					break;
				}
				peticion = new PeticionHTTP();
				peticion.analizando(flujoEntrada);
				logger.debug("Preparando respuesta a la peticion.");

				nombreArchivo = peticion.getUrl();
				if (nombreArchivo.length() == 0 || nombreArchivo.charAt(nombreArchivo.length() - 1) == File.separatorChar) {
					nombreArchivo += servidorHTTP.getNombreArchivoDefault();
				}

				logger.debug("Nombre del Fichero: " + (servidorHTTP.getRutaArchivos() + nombreArchivo));

				solicitud = new File(servidorHTTP.getRutaArchivos() + nombreArchivo);
				archivoSolicitado = new FileInputStream(solicitud);

				/* Generando Codigo y Mensaje de la Respuesta. */
				if (!(peticion.getVersion().equals("HTTP/1.0") || (peticion.getVersion().equals("HTTP/1.1")))) {
					codigoRespuesta = 505;
					mensajeRespuesta = "http Version Not Supported";
				} else if (this.contenidoMIME(nombreArchivo).equals("Desconocido")) {
					codigoRespuesta = 415;
					mensajeRespuesta = "Unsupported Media Type";
				} else if (!(peticion.getMetodo().equals(PeticionHTTP.METODO_GET) || peticion.getMetodo().equals(PeticionHTTP.METODO_POST) || peticion.getMetodo().equals(PeticionHTTP.METODO_HEAD))) {
					codigoRespuesta = 501;
					mensajeRespuesta = "Not Implemented";
				} else if (peticion.getValorParametroCabecera("Host") == null) {
					codigoRespuesta = 400;
					mensajeRespuesta = "Bad Request";
				} else if (solicitud.exists()) {
					codigoRespuesta = 200;
					mensajeRespuesta = "OK";
				} else {
					codigoRespuesta = 404;
					mensajeRespuesta = "Not Found";
				}

				logger.debug("Codigo de Respuesta: " + codigoRespuesta);
				logger.debug("Escribiendo Salida...");

				flujoSalida.print(peticion.getVersion() + " " + codigoRespuesta + " " + mensajeRespuesta + "\r\n");
				flujoSalida.print("Server: Servidor HTTP del Framework 1.0 de la Comision.\r\n");
				flujoSalida.print("Date: " + Tiempo.toString(new Date(), "EEE, dd MMM yyyy HH:mm:ss z") + "\r\n");

				/*
				 * Si la peticion ha sido correcta (200), se envia el resto de
				 * la peticion.
				 */
				if (codigoRespuesta == 200) {
					if (peticion.getMetodo().equals(PeticionHTTP.METODO_GET)) {
						flujoSalida.print("Content-Type: " + this.contenidoMIME(nombreArchivo) + "\r\n");
						flujoSalida.print("Content-Length: " + solicitud.length() + "\r\n");
						flujoSalida.print("Last-Modified: " + new Date(solicitud.lastModified()) + "\r\n");
						flujoSalida.print("\r\n");
						/* Contenido del fichero */
						byte[] bytes = new byte[(int) solicitud.length()];
						int n;
						while ((n = archivoSolicitado.read(bytes)) != -1) {
							flujoSalida.write(bytes, 0, n);
						}
						archivoSolicitado.close();
					} else if (peticion.getMetodo().equals(PeticionHTTP.METODO_POST)) {
						flujoSalida.print("Content-Type: " + this.contenidoMIME(nombreArchivo) + "\r\n");
						flujoSalida.print("Content-Length: " + solicitud.length() + "\r\n");
						flujoSalida.print("Last-Modified: " + new Date(solicitud.lastModified()) + "\r\n");
						flujoSalida.print("\r\n");
						/* Contenido del fichero */
						byte[] bytes = new byte[(int) solicitud.length()];
						int n;
						while ((n = archivoSolicitado.read(bytes)) != -1) {
							flujoSalida.write(bytes, 0, n);
						}
						archivoSolicitado.close();
					} else if (peticion.getMetodo().equals(PeticionHTTP.METODO_HEAD)) {
						flujoSalida.print("Content-Type: " + this.contenidoMIME(nombreArchivo) + "\r\n");
						flujoSalida.print("Last-Modified: " + new Date(solicitud.lastModified()) + "\r\n");
						flujoSalida.print("\r\n");
					}
				} else {
					flujoSalida.print("Connection: close\r\n");
					flujoSalida.print("\r\n");
					condicionSalida = false;
					break;
				}

				/*
				 * Si el servidor es HTTP/1.1 y recibe la cabecera
				 * Connection:close O si el servidor es HTTP/1.0 Entonces se
				 * cerrara la conexion.
				 */
				if (((peticion.getVersion().equals("HTTP/1.1")) && ((peticion.getValorParametroCabecera("Connection").equals("close")))) || (peticion.getVersion().equals("HTTP/1.0"))) {
					condicionSalida = false;
				}
			} while (condicionSalida);

			logger.debug("Cerrando Flujos de Conexion");
			flujoSalida.close();
			flujoEntrada.close();

			logger.debug("Cerrando Conexion Servidor->Cliente");
			cliente.close();

		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		} catch (InterruptedException e) {
			logger.error(e);
			e.printStackTrace();
		}
		logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

	}

	/***************************************************************************
	 * Metodo que identifica el tipo de archivo a partir de la extension de este
	 * y genera el codigo MIME para la respuesta.
	 * 
	 * @param nombreArchivo
	 *            Nombre del Archivo.
	 * @return Codigo MIME.
	 **************************************************************************/
	private String contenidoMIME(final String nombreArchivo) {
		if ((nombreArchivo.endsWith(".htm")) || (nombreArchivo.endsWith(".html"))) {
			return "Text/html";
		} else if (nombreArchivo.endsWith(".gif")) {
			return "Image/gif";
		} else if ((nombreArchivo.endsWith(".jpg")) || (nombreArchivo.endsWith(".jpeg")) || (nombreArchivo.endsWith(".jpe"))) {
			return "Image/jpeg";
		} else if ((nombreArchivo.endsWith(".mpeg")) || (nombreArchivo.endsWith(".mpg")) || (nombreArchivo.endsWith(".mpe"))) {
			return "Video/mpeg";
		} else if (nombreArchivo.endsWith(".txt")) {
			return "Text/plain";
		} else if (nombreArchivo.endsWith(".pdf")) {
			return "Application/pdf";
		} else if (nombreArchivo.endsWith(".rtf")) {
			return "Application/rtf";
		} else if (nombreArchivo.endsWith(".doc")) {
			return "Application/doc";
		} else {
			return "Desconocido";
		}
	}

	public void iniciar() {
		if (hilo == null) {
			hilo = new Thread(this);
		}
		hilo.start();
	}

	public void detener() {
		hilo = null;
	}

}
