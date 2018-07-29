package org.example.test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String url = "rmi://"+args[0]+"/" + args[1];
		
		System.out.println("URL: " + url);
		
		try {
			Remote lookup = Naming.lookup(url);
			System.out.println(lookup);
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
