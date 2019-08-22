package org.example.test;

import org.example.test.services.IndexService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "org.example.test")
public class ApplicationContext {
	
	private static ConfigurableApplicationContext applicationContext;

	public static void initialize(String... args) {
		applicationContext = SpringApplication.run(ApplicationContext.class, args);
	}
	
	public static void close() {
		applicationContext.close();
	}
	
	public static IndexService getIndexService() {
		return applicationContext.getBean(IndexService.class);
	}
	
}
