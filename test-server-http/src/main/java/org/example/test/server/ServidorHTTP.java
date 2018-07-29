package org.example.test.server;

import java.io.IOException;
import java.net.Socket;

import org.apache.log4j.Logger;
import org.example.test.server.core.Conexion;
import org.example.test.server.core.ServidorBaseFactory;
import org.example.test.server.utils.Propiedades;

/*******************************************************************************
 * CLASE QUE IMPLEMENTA UN SERVIDOR HTTP.
 * 
 * @author lacomision.org
 * @version 1.0
 * 
 ******************************************************************************/
public class ServidorHTTP extends ServidorBaseFactory implements Runnable {
	
	/** Atributo que define el log donde escribe la clase. */
	private Logger logger = Logger.getLogger(this.getClass());

	/** Atributo que define la instancia donde esta ejecutandose el servidor. */
	private volatile Thread hilo = null;

	/**
	 * CONSTRUCTOR DE LA CLASE.
	 *
	 */
	public ServidorHTTP() {
		super();
	}

	/** {@inheritDoc} */
	protected final void procesarPeticion(final Socket socketCliente) {
		logger.debug("Procesando Peticion...");
		(new Conexion(super.cliente, this)).iniciar();
	}

	/** {@inheritDoc} */
	public final void run() {
		try {
			super.iniciar();
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		}
		
	}

	/** {@inheritDoc} */
	public final void iniciar() throws IOException {
		if (hilo == null) {
			hilo = new Thread(this);
			hilo.start();
		}
	}

	/** {@inheritDoc} */
	public final void detener() throws IOException {
		try {
			hilo = null;
			super.detener();
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	/***************************************************************************
	 * Metodo que obtiene del archivo de propiedades del Framework los
	 * parametros de configuracion del servidor.
	 * 
	 **************************************************************************/
	public final void configurar() {
		logger.debug("Configurando los parametros del servidor...");
		super.setPuerto(Propiedades.getValorEntero32("servidor.http.puerto"));
		super.setTimeOut(Propiedades.getValorEntero32("servidor.http.timeOut"));
		super.setNombreArchivoDefault(Propiedades.getValor("servidor.http.archivo.defecto"));
		super.setRutaArchivos(Propiedades.getValor("servidor.http.archivos"));
	}

	

}
