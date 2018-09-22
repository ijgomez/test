package org.example.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResourcesFactoryTest {

	@Test
	public void testFromQuery1() throws Exception {
		
		String fromQueryName = ResourcesFactory.fromQueryName(ResourcesFactoryTestURL.TEST.getContext(), "query_test1");
		
		assertEquals(fromQueryName, "SELECT SYSDATE FROM DUAL");
	}
	
	@Test
	public void testFromQuery2() throws Exception {
		
		String fromQueryName = ResourcesFactory.fromQueryName(ResourcesFactoryTestURL.TEST.getContext(), "query_test2", "env");
		
		assertEquals(fromQueryName, "SELECT TIMESTAMP FROM DUAL");
	}

}
