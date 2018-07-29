package org.example.test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

public class ClienteRMI {

	public static void main(String[] args) {
		
		System.setSecurityManager(new RMISecurityManager());
		
		PeticionesWSRemote peticiones;
		try {
			peticiones = (PeticionesWSRemote) Naming.lookup("rmi://localhost/ejemploRMI");
			System.out.println(peticiones.peticionFechaHora(""));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
