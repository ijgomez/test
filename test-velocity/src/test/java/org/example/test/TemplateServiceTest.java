package org.example.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TemplateServiceTest {

	private TemplateService templateService;

	@Before
	public void beforeTest() {
		templateService = new TemplateService();
		templateService.init();
	}

	@Test
	public void testExecute() {
		templateService.execute("src/test/resources/template.vm", "target/template.txt");
	}

	@After
	public void AfterTest() {

	}

}
