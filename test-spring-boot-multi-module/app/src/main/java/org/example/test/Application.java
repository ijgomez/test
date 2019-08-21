package org.example.test;

import org.example.test.services.IndexService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "org.example.test")
public class Application {

    public Application() {

    }

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
		
		IndexService indexService = applicationContext.getBean(IndexService.class);
		
		System.out.println(indexService.getRepository());
	}

}
