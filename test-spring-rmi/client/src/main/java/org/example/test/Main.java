package org.example.test;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.getProperties().setProperty("java.security.policy", "src/main/policy/security.policy");
	        System.setSecurityManager(new SecurityManager());
	        
			ClienteRMI cliente = ClienteRMI.getService();

			cliente.init("localhost", 1099, "interfaceRMI");
			cliente.peticion1("peticion1");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
