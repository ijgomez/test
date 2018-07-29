package org.example.test;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public final class FTPHelper {

	public static void storeFile(String host, int port, String user, String password, String localPathname, String remoteFilename) throws IOException {
		FTPClient client = new FTPClient();
	    FileInputStream fis = null;
	    
	    client.connect(host, port);
		client.login(user, password);

	    fis = new FileInputStream(localPathname);
	    client.storeFile(remoteFilename, fis);
	    client.logout();
	    fis.close();
	    System.out.println("FIN");
	}

}
