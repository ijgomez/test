package org.example.test;

import org.junit.Test;

public class FileHelperTest {

	@Test
	public void testCopy() {
		
		FileHelper.copy("src/test/resources/input.txt", "target/output.txt");

	}

	@Test
	public void testCut() {
		
		FileHelper.cut("src/test/resources/output2.bz2", 2, "target");
		
	}
	
	@Test
	public void testBz2() {
		
		FileHelper.bzip2("src/test/resources/input2.pdf", "target/output2.bz2");
		
	}

}
