package org.example.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "org.example.test")
public class Application {

	private final MyService myService;

    public Application(MyService myService) {
        this.myService = myService;
    }

    public String home() {
        return myService.message();
    }
	
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
		
		MyService service = applicationContext.getBean(MyService.class);
		
		System.out.println(service.message());
	}

}
