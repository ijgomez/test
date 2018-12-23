package org.example.test.services;

import java.net.InetAddress;

import org.springframework.stereotype.Service;

@Service
public class LocationService {

	public String getAddress() throws Exception {
		return InetAddress.getLocalHost().getHostAddress();
	}
	
}
