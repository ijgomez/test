package org.example.test;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testing {@link SecurityHelper}.
 * 
 * @author ijgomez
 *
 */
public class SecurityHelperTest {

	/**
	 * Test of the reading method.
	 * 
	 * @throws Exception
	 *             Error in the execution of the test.
	 */
	@Test
	public final void testRead() throws Exception {

		SecurityHelper.read("src/test/resources/Common_name.cer");

		assertTrue(true);
	}

}
