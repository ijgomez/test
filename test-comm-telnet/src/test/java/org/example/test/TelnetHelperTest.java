package org.example.test;

import org.junit.Test;

public class TelnetHelperTest {

	@Test
	public void testTelnet() throws Exception {
		
		TelnetHelper.telnet("rainmaker.wunderground.com", 3000);

	}

}
