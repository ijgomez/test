package org.example.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	private ApplicationContext applicationContext;
	
	public void run(String... arg) {
		
		this.applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml", "applicationContext-quartz.xml");
		
		System.out.println("Running...");
		
		System.out.println("ID: " + this.applicationContext.getId());
		System.out.println("Application Name: " + this.applicationContext.getApplicationName());
		System.out.println("StartupDate: " + this.applicationContext.getStartupDate());
		System.out.println("Environment: " + this.applicationContext.getEnvironment());
		System.out.println("");
	}
	
	/**
	 * @param args
	 */
	public static void main(String... args) {
		
		(new Main()).run(args);

	}

}
