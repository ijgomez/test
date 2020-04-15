package org.example.test;

public class Main {

	public static void main(String[] args) {
		
		try {
			EmbeddedLdapServer embeddedLdapServer;
			
			// TODO Auto-generated method stub
			embeddedLdapServer = new EmbeddedLdapServer();
			embeddedLdapServer.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
