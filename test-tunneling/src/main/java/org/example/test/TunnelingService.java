package org.example.test;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class TunnelingService {

    public void start() throws Exception{
    	JSch jsch;
		Session session;
		String host, user, password, tunnelRemoteHost;
		int port, tunnelRemotePort, tunnelLocalPort;
		
    	// intermediate server
    	//------------------------------------------------------------------------------------------------------        
		host = "192.168.200.100";
		user = "userServer";
		password = "pwdServer";
		port = 22;
         
        // target server or remote computer
    	//------------------------------------------------------------------------------------------------------        
		tunnelRemoteHost = "192.168.300.200";
		tunnelRemotePort = 5001;
		tunnelLocalPort = 5001;
        
		jsch = new JSch();
		
		session = jsch.getSession(user, host, port);
		session.setPassword(password);
		session.setUserInfo(new LocalUserInfo());
		session.connect();
		session.setPortForwardingL(tunnelLocalPort, tunnelRemoteHost, tunnelRemotePort);
		
		System.out.println("...Connected");
    }
    
	class LocalUserInfo implements UserInfo {
		
		String passwd;

		public String getPassword() { return passwd; }

		public boolean promptYesNo(String str) { return true; }

		public String getPassphrase() { return null; }

		public boolean promptPassphrase(String message) { return true; }

		public boolean promptPassword(String message) { return true; }

		public void showMessage(String message) { }
	}
	
}
