package org.example.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class EncriptacionServiceTest {

	private EncriptacionService encriptacionService;
	
	@Test
	public void testEncriptacion() {
		
		this.encriptacionService = new EncriptacionService();
		
		String encriptacion = this.encriptacionService.encriptacion("Esto es una Prueba");
		
		assertEquals("L7zzlAYZ1rSijwgWWt7Mz7ldDGg=", encriptacion);
		
	}

}
