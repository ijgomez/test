package org.example.test;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HTTPHelper {

	/** Atributo que define el log donde escribe la clase. */
	private static Logger LOGGER = LoggerFactory.getLogger(HTTPHelper.class);

	/**
	 * Metodo que obtiene la respuesta de un servidor web a una peticion.
	 * 
	 * @param url
	 *            URL de la pagina web, peticion, o servicio que se quiere
	 *            obtener del servidor. Formato de la URL:
	 *            <code>http://[servidor][:puerto(opcional)]/[informacion o pagina web requerida]</code>
	 * @return informacion devuelta por el servidor. En caso de haber pedido una
	 *         pagina web. este devolvera la informacion en formato HTML.
	 */
	public static final String peticion1(final String url, final String usuario, String password) {
		URL _url = null;
		URLConnection conn = null;
		BufferedReader in = null;
		String inputLine;
		String salida = "";
		try {
			LOGGER.debug("Configurando conexion HTTP con la URL: " + url);
			_url = new URL(url);
			
			LOGGER.debug("Abriendo conexion contra la URL...");
			conn = _url.openConnection();
			
			if (usuario != null && password != null) {
				LOGGER.debug("Estableciendo proxy autentificacion...");
				String authString = usuario + ":" + password;
	            String auth = "Basic " + Base64.getEncoder().encode(authString.getBytes());
				conn.setRequestProperty("Proxy-Authorization", auth);
			}
			
			LOGGER.debug("Estableciendo flujos de datos...");
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			LOGGER.debug("Recibiendo Informacion...");
			while ((inputLine = in.readLine()) != null) {
				LOGGER.debug("TRAMA: " + inputLine);
				salida += inputLine;
			}
			
			LOGGER.debug("Cerrando Flujo de informacion...");
			in.close();
			
			LOGGER.debug("...FIN DE LA COMUNICACION");
			return salida;
			
		} catch (MalformedURLException e) {
			LOGGER.error("URL mal formada");
			LOGGER.error("Mensaje MalformedURLException: ", e);
			return null;
		} catch (IOException e) {
			LOGGER.error("No se puede conectar con la URL especificada.");
			LOGGER.error("Mensaje IOException: ", e);
			return null;
		}
	}
	
	public static void peticion2(final String _url) {
		try {

			OutputStream to_file = System.out;

			System.out.println("--- Cliente HTTP ---");
			System.out.println("estableciendo URL... (" + _url + ")");
			URL url = new URL(_url);
			String protocol = url.getProtocol();
			if (!protocol.equals("http"))
				throw new IllegalArgumentException("URL must use 'http:' protocol");
			String host = url.getHost();
			int port = url.getPort();
			if (port == -1)
				port = 80; 
			String filename = url.getFile();

			System.out.println("estableciendo Socket... (" + host + ", " + port + ")");
			Socket socket = new Socket(host, port);
			
			System.out.println("estableciendo flujos de datos...");
			InputStream from_server = socket.getInputStream();
			PrintWriter to_server = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

			System.out.println("enviando el comando ... (GET " + filename + ")");
			to_server.println("GET " + filename);
			to_server.flush();

			System.out.println("leyendo respuesta...");
			byte[] buffer = new byte[4096];
			int bytes_read;
			while ((bytes_read = from_server.read(buffer)) != -1) {
				to_file.write(buffer, 0, bytes_read);
			}
			System.out.println();
			
			System.out.println("...Finalizando Conexion");
			socket.close();
			to_server.close();
			to_file.close();

		} catch (Exception e) {
			System.out.println();
			System.err.println(e);
		} 

	}

	public static void peticion3(String[] args) {
		try { // Check the arguments
			if ((args.length != 1) && (args.length != 2))
				throw new IllegalArgumentException("Wrong number of arguments");
			// Get an output stream to write the URL contents to
			OutputStream to_file;
			if (args.length == 2)
				to_file = new FileOutputStream(args[1]);
			else
				to_file = System.out;
			// Now use the URL class to parse the user-specified URL into its various parts: protocol, host, port, filename. Check the  protocol
			URL url = new URL(args[0]);
			String protocol = url.getProtocol();
			if (!protocol.equals("http"))
				throw new IllegalArgumentException("URL must use 'http:' protocol");
			String host = url.getHost();
			int port = url.getPort();
			if (port == -1)
				port = 80; 
			// if no port, use the default HTTP port
			String filename = url.getFile(); 
			// Open a network socket connection to the specified host and port
			Socket socket = new Socket(host, port); 
			// Get input and output streams for the socket
			InputStream from_server = socket.getInputStream();
			PrintWriter to_server = new PrintWriter(
					new OutputStreamWriter(socket.getOutputStream()));
			// Send the HTTP GET command to the Web server, specifying the file.
			// This uses an old and very simple version of the HTTP protocol
			to_server.println("GET " + filename);
			to_server.flush(); // Send it right now!
			// Now read the server's response, and write it to the file
			byte[] buffer = new byte[4096];
			int bytes_read;
			while ((bytes_read = from_server.read(buffer)) != -1)
				to_file.write(buffer, 0, bytes_read);
			// When the server closes the connection, we close our stuff
			socket.close();
			to_file.close();
		} catch (Exception e) {
			System.err.println(e);
			System.err.println("Usage: java  HttpClient <URL> [<filename>]");
		}
	}


}
