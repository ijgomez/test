package org.example.test.web.controller;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.example.test.core.domain.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/reports")
public class ReportsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportsController.class);
	
	private List<Report> testEmployees;
	
	@PostConstruct
	public void init() {
		LOGGER.info("Generando Datos de Prueba.");
		this.testEmployees = this.buildDataTest();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String showView() {
		
		LOGGER.info("Visualizando Informes Definidos.");
		
		return "reports-view";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<Report> list() {

		LOGGER.info("Cargando Informes.");
		
		return this.testEmployees;

	}
	
	
	
	private List<Report> buildDataTest() {
		List<Report> data;

		data = new ArrayList<Report>();
		
		for (long i = 0; i < 300; i++) {
			data.add(new Report(i, 
					RandomStringUtils.random(10, false, true), 
					RandomStringUtils.random(20, false, true), 
					RandomStringUtils.random(10, false, true), 
					RandomStringUtils.random(10, false, true), 
					RandomUtils.nextBoolean(), Calendar.getInstance().getTime()));
		}
				
		return data;
	}
}
