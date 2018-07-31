package org.example.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class LuceneServiceTest {

	@Test
	public void testIndexar() {
		
		LuceneService luceneService;
		
		luceneService = new LuceneService();
		luceneService.indexar(".", "target/index");
		
		assertTrue(true);
	}
	
	@Test
	public void testSearch() {
		LuceneService luceneService;
		
		luceneService = new LuceneService();
		luceneService.search("target/index", "+lucene");
		
		assertTrue(true);
	}

}
