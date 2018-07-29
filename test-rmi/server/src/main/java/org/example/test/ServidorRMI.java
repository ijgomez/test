package org.example.test;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorRMI {

	public static void main(String[] args) {

		Registry r;
		try {
			System.out.println("Server......");
			r = LocateRegistry.getRegistry();
			r.bind("ejemploRMI", new PeticionesWSRemoteImpl());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
