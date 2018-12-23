package org.example.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class CourseEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseEurekaServerApplication.class, args);
	}

}

