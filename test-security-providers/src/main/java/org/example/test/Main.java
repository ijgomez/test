package org.example.test;

import java.security.Provider;
import java.security.Security;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Provider[] providers;

		System.out.println("'Providers' instalados:");
		providers = Security.getProviders();
		for (Provider provider : providers) {
			System.out.println("-------------------------------------------------------");
			System.out.println("Name: " + provider.getName());
			System.out.println("\tVersion: " + provider.getVersion());
			System.out.println("\tInfo:    " + provider.getInfo());
			System.out.println("\tClass:   " + provider.getClass());
//			System.out.println("\tServices:");
//			Set<Service> services = provider.getServices();
//			for (Service service : services) {
//				System.out.println("\t\tService: " + service.getAlgorithm());
//			}
		}
			
	}

}
