package org.example.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InterlocutorService {
	
	private @Value("${app.title}") String title;
	
	public void init() {
		System.out.println("=> Init Service " + System.getProperty("my.key"));
	}

	public void medidasPeriodicas() {
		System.out.println("=> Medidas Periodicas " + System.getProperty("my.key"));
	}
	
	public void medidasPedientes() {
		System.out.println("=> Medidas Pendientes: " + title + " - ");
	}

}
