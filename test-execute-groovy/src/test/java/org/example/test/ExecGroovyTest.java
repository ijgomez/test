package org.example.test;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

public class ExecGroovyTest {
	@Test
	public void simpleScriptTest() {
	    GroovyShell shell = new GroovyShell();
	    String script = "return 'hello world'";

	    Object result = shell.evaluate(script);
	    assertEquals(result, "hello world");
	}
	
	@Test
    public void ScriptWithBindingTest() {
        Binding binding = new Binding();
        binding.setVariable("a", 1);
        binding.setVariable("b", 2);
        GroovyShell shell = new GroovyShell(binding);

        String script = "return a + b";

        Object result = shell.evaluate(script);
        assertEquals(result, 3);
    }
	

	@Test
    public void formulaCalcularTest() throws IOException {
		Binding binding = new Binding();
        binding.setVariable("a", 1);
        binding.setVariable("b", 2);
        
        GroovyShell shell = new GroovyShell(binding);
        URL url = Thread.currentThread().getContextClassLoader().getResource("formula.groovy");
        final File file = new File(url.getPath());

        Script script = shell.parse(file);
        
        Object result = script.run();
        
        assertEquals(result, 7);
       
	}
	
	@Test
    public void formulaCalcula2Test() throws IOException {
       
        GroovyShell shell = new GroovyShell();
        URL url = Thread.currentThread().getContextClassLoader().getResource("formulaAdd.groovy");
        final File file = new File(url.getPath());

        Script script = shell.parse(file);
        
        //Object result = script.run();
        Object result = script.invokeMethod("add", new Object[]{1, 2});
        
        assertEquals(result, 3);
       
	}
	
	@Test
    public void LoadFromFileTest() throws IOException {
        GroovyShell shell = new GroovyShell();

        URL url = Thread.currentThread().getContextClassLoader().getResource("simple.groovy");
        final File file = new File(url.getPath());

        Script script = shell.parse(file);
        Object result = script.run();
        assertEquals(result, "hello world");
    }
	
	@Test
    public void LoadFromFileAndExecMethodTest() throws IOException {
        GroovyShell shell = new GroovyShell();

        URL url = Thread.currentThread().getContextClassLoader().getResource("test.groovy");
        final File file = new File(url.getPath());

        Script script = shell.parse(file);
        Object result = script.invokeMethod("hello_world", null);
        assertEquals(result, "hello world");
    }

    @Test(expected = groovy.lang.MissingPropertyException.class)
    public void ExceptionTest() {
        GroovyShell shell = new GroovyShell();
        String script = "return a";

        shell.evaluate(script);
    }

    @Test
    public void ExecuteFromJsonTest() throws IOException {
        Binding binding = new Binding();
        binding.setVariable("input", 2);
        GroovyShell shell = new GroovyShell(binding);

        URL url = Thread.currentThread().getContextClassLoader().getResource("simpleConfig.json");
        final File file = new File(url.getPath());

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {};
        Map<String, String> map = mapper.readValue(file, typeRef);

        String script = map.get("script");

        Object result = shell.evaluate(script);
        assertEquals(result, 12);
    }
    
    @Test
    public void executeInterce() throws IOException, InstantiationException, IllegalAccessException {
    	int result = 0;
    	try (GroovyClassLoader classLoader = new GroovyClassLoader()) {
    		Class<?> groovy = classLoader.parseClass(new File("src/test/resources/MyFormulaImpl.groovy"));
    	      Formula myInterface = (Formula) groovy.newInstance();
    	      result = myInterface.execute(new Object[]{1, 2});
    	}
    	assertEquals(result, 3);
    }
}
