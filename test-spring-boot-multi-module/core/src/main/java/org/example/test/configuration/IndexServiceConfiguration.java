package org.example.test.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties("index")
@Data
public class IndexServiceConfiguration {

	private String repository;
	
}
