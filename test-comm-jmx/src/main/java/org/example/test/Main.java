package org.example.test;

import java.io.IOException;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * Clase que comprueba si hay conectividad por JMX con un proceso java.
 * 
 * @author ijgomez
 * 
 */
public class Main {

	/*
	 * /usr/java5_64/bin/java -jar jmxclient.jar 10.98.47.47 9999
	 * 
	 * Cannot connect to 192.168.1.1:9999 using
	 * service:jmx:rmi:///jndi/rmi://192.168.1.1:9999/jmxrmi
	 * 
	 * /usr/java5_64/bin/java -Djavax.net.debug=all -jar jmxclient.jar > ssl.log
	 * /usr/java5_64/bin/java -Djavax.net.debug=ssl:handshake
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			
//			checkConnectivity("service:jmx:rmi:///jndi/rmi://:18000/jmxrmi");

//			checkConnectivity("service:jmx:rmi:///jndi/rmi://192.168.1.1:9999/jmxrmi");

			checkConnectivity("service:jmx:rmi:///jndi/rmi://" + args[0] + ":" + args[1] + "/jmxrmi");
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void checkConnectivity(String serviceURL) throws IOException {

		System.out.println("\nCreate an RMI connector client and connect it to the RMI connector server");

		JMXServiceURL url;

		url = new JMXServiceURL(serviceURL);
		JMXConnector jmxc = JMXConnectorFactory.connect(url);
		System.out.println(jmxc);
		MBeanServerConnection mBeanServerConnection = jmxc.getMBeanServerConnection();

		System.out.println("DEFAULT DOMAIN: " + mBeanServerConnection.getDefaultDomain());
		System.out.println("DOMAINS: ---------------------------------------------");
		for (String string : mBeanServerConnection.getDomains()) {
			System.out.println(string);
		}
		System.out.println("-------------------------------------------------------");

	}

}
