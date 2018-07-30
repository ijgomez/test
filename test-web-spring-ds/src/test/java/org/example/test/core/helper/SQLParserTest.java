package org.example.test.core.helper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class SQLParserTest {

	@Test
	public void testParser() throws Exception {
		
		String sqlform = "INSERT; INSERT; INSERT; COMMIT";
		
		List<String> sqls = SQLParser.parser(sqlform);
		
		assertEquals(4, sqls.size());
		
	}

}
