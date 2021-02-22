package org.example.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author 
 *
 */
public class Main {

	public static void main(String[] args) {
		
		EmailService service;
		if (args == null || args.length == 0) {
			service = new EmailJavaServiceImpl("smtp.mailtrap.io", 25, "87ba3d9555fae8", "91cb4379af43ed");
			service.sendMail();
		} else {
			try (ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml")) {
				service = applicationContext.getBean(EmailSpringServiceImpl.class);
				service.sendMail();
			}
		}
	}
}
