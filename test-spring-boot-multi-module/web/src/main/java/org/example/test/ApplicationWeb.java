package org.example.test;

import org.example.test.services.IndexService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "org.example.test")
@RestController
public class ApplicationWeb {

	private final IndexService indexService;

    public ApplicationWeb(IndexService indexService) {
        this.indexService = indexService;
    }

    @GetMapping("/")
    public String home() {
        return indexService.getRepository();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationWeb.class, args);
	}

}
