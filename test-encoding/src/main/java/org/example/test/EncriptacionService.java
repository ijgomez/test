package org.example.test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class EncriptacionService {

	public synchronized String encriptacion(String texto) {
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("SHA");
			md.update(texto.getBytes("UTF-8"));
			byte raw[] = md.digest(); 
		    String hash = (new Base64()).encodeToString(raw); 
		    
		    return hash; 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
