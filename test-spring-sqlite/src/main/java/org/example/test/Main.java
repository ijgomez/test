package org.example.test;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main().run();
	}

	private static final Logger logger = Logger.getLogger(Main.class);
	
	private String[] paths = {"applicationContext.xml"};
	
	@SuppressWarnings("resource")
	public void run(){
	
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(this.paths);
		
		BaseDatosDao bd = (BaseDatosDao)applicationContext.getBean("baseDatosDao");
		
		List<Fichero> findAll = bd.findAll();
		
		logger.debug("RESULTADO: " + findAll.size());
		
		for (Fichero fichero : findAll) {
			logger.debug(fichero);
		}
		
		Fichero fichero;
		for (int i = 0; i < 10000; i++){
			logger.debug("Fichero: " + i);
			
			fichero = new Fichero();
			fichero.setFileName("Fichero" + i + ".ext");
			fichero.setPath("c:\\Fichero" + i + ".ext");
			fichero.setExtension("ext");
			fichero.setLength((long)i*5);
			fichero.setFxCreationFile(Calendar.getInstance().getTime());
			
			bd.insert(fichero);
			
			
		}
		
	
	}
}
