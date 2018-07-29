package org.example.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceLocator {

	private static ClassPathXmlApplicationContext ctx;

	public static void init() {
		ctx = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
	}

	public static PeticionService getPeticionService() {
		return (PeticionService) ctx.getBean(PeticionServiceImpl.class);
	}
}
