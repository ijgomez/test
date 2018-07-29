package org.example.test;

import java.io.IOException;
import java.net.InetAddress;

import org.apache.log4j.Logger;
import org.example.test.exceptions.InitServerException;

public class Main {

	private static final Logger logger = Logger.getLogger(Main.class);
	
//	private static final String ARG_PATH_SERVER_PROPERTIES = "-ServerProperties";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

		try {
			logger.info("------------------------------------------------------");
			logger.info("");
			logger.info("");
			logger.info("Arranque Servidor de RMI");
			logger.info("");
			logger.info("");
			logger.info("------------------------------------------------------");
			logger.info("");
			logger.info("Configurando el servidor...");
			config(args);

			logger.info("Creando objeto remoto ...");
			logger.info("HostName: " + InetAddress.getLocalHost().getHostName());
			logger.info("IP: " + InetAddress.getLocalHost().getHostAddress());
			logger.info(" ");
		} catch (Exception e) {
			logger.fatal("Error no controlado: ", e);
		}
	}

	private static void config(String[] args) throws InitServerException, IOException {
//		String serviceName;
		
		logger.info("Leyendo el fichero de configuracion...");		
		String serverProperties;
//		if (args == null || args.length == 0) {
//			throw new InitServerException("Faltan Argumentos para arrancar la aplicacion: " + ARG_PATH_SERVER_PROPERTIES);
//		}
//		serverProperties = args[0];
//		if (serverProperties.indexOf(ARG_PATH_SERVER_PROPERTIES) == -1) {
//			throw new InitServerException("Argumento no reconocido: " + args[0]);
//		}
//		serverProperties = args[0];
//		serverProperties = serverProperties.substring(ARG_PATH_SERVER_PROPERTIES.length() + 1, serverProperties.length());

		serverProperties = "src/main/resources/servidor.properties";
		
		logger.info("Localizacion del fichero de configuracion: " + serverProperties);
		RMIServerProperties.load(serverProperties);

		System.getProperties().setProperty("java.security.policy", "src/main/policy/security.policy");
        System.setSecurityManager(new SecurityManager());
		
		ServiceLocator.init();
		
		logger.debug("Contexto Cargado.");

	}

}
