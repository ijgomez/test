package org.example.test.services;

import java.util.List;

import org.example.test.api.Operandos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LocalizadorServicio {

 	private String serviceURL;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	public LocalizadorServicio() {
		//this.serviceURL = serviceURL.startsWith("http") ? serviceURL : "http://" + serviceURL;
		
	}
	
	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}
	
	
	public String operacion(String operacion, Operandos operandos) {
		List<ServiceInstance> instances = discoveryClient.getInstances(serviceURL);
		
		String url = instances.get(0).getUri() + "/calculadora/" + operacion;
		
		System.out.println("url=" + url);
		
		return this.restTemplate.postForObject(url, operandos, String.class);
	}
	
}
