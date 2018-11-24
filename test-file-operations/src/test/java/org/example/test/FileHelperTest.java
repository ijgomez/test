package org.example.test;

import static org.junit.Assert.*;

import java.util.function.Function;

import org.example.test.domain.Customer;
import org.example.test.domain.Measure;
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

	@Test
	public void testTransformXML2CSV() {

		FileHelper.transformXML2CSV("src/test/xml/data.xml", "src/test/xsl/data.xsl", "target/data.csv");

	}

	@Test
	public void testMarshal() throws Exception {
		Customer customer;

		customer = new Customer();
		customer.setAge(99);
		customer.setId(1);
		customer.setName("usuario");

		FileHelper.marshal(customer, "target/file.xml");

	}

	@Test
	public void testUnmarshal() throws Exception {
		FileHelper.unmarshal("src/test/resources/file.xml");
	}

	@Test
	public void testCompressBz2() throws Exception {

		System.out.println("Comprimiendo...");
		FileHelper.compressBz2("src/test/resources/input2.pdf");
		System.out.println("..OK.");
		assertTrue(true);

	}

	@Test
	public void testUncompressBz2() throws Exception {

		System.out.println("Descomprimiendo...");
		FileHelper.uncompressBz2("src/test/resources/input2.pdf.bz2");
		System.out.println("..OK.");
		assertTrue(true);

	}
	
	@Test
	public void testReadCSV() throws Exception {
		Function<String, Measure> mapper = (line) -> {
			String[] p;
			Measure measure;
			
			measure = new Measure();
			p = line.split(";");
			
			//TODO Parser line
			
			return measure;
		};
		FileHelper.readCSV("src/test/csv/data.csv", mapper);
	}

}
