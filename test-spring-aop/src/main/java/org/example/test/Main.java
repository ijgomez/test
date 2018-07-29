package org.example.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext applicationContext;
		
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");		
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		CustomerService customerService = applicationContext.getBean(CustomerService.class);
		
		Customer c = customerService.getCustomer(1L);
		
		LOGGER.info("Customer: {}", c);
		
		
		customerService.execute(1L);
		
		LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		((ClassPathXmlApplicationContext) applicationContext).close();

	}

}
