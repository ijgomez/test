package org.example.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReportServiceTest {

	private ReportService reportService;

	@Before
	public void beforeTest() {
		reportService = new ReportService();
		reportService.init();
	}

	@Test
	public void testExecute() {
		reportService.execute("Medidas Diez Minutales", "src/test/resources/informe.vm", "target/informe.jrxml");
	}

	@After
	public void AfterTest() {

	}

}
