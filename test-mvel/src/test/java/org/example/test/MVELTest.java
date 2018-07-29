package org.example.test;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mvel2.MVEL;

public class MVELTest {

	@Test
	public void test1() {
		
		Person personInst = new Person();
    	personInst.setName("Mr. Foo");

    	Object result = MVEL.eval("name == 'Mr. Foo'", personInst);
    	
    	assertTrue((Boolean) result);
	}
	
	@Test
	public void test2() {
		Map<String, Object> vars = new HashMap<String, Object>();
    	vars.put("x", new Integer(5));
    	vars.put("y", new Integer(10));

    	Integer result2 = (Integer) MVEL.eval("x * y", vars);

    	assertEquals(Integer.valueOf(50), result2);
    	
	}
	
	@Test
	public void test3() {
		// The compiled expression is serializable and can be cached for re-use.
    	
    	Serializable compileExpression = MVEL.compileExpression("x * y");
    	
    	Map<String, Object> vars = new HashMap<String, Object>();
    	vars.put("x", new Integer(5));
    	vars.put("y", new Integer(10));

    	// Executes the compiled expression
    	Integer result = (Integer) MVEL.executeExpression(compileExpression, vars); 
    	
    	assertEquals(Integer.valueOf(50), result);
    	
	}

}
