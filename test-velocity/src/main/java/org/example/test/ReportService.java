package org.example.test;

import java.io.FileWriter;
import java.io.Writer;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

public class ReportService {

	private static final Logger LOGGER = Logger.getLogger(ReportService.class);
	
	private VelocityEngine velocityEngine;
	
	public void init() {
		try {
			LOGGER.info("Iniciando Servicio.");
		
			velocityEngine = new VelocityEngine();
			velocityEngine.init();
			
		} catch (Exception e) {
			LOGGER.error("Fallo al inicializar el servicio", e);
		}
	}
	
	public void execute(String nombre, String plantilla, String fileResult) {
		
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		LOGGER.info("Ejecutando el Informe: " + nombre);
		
		Template template;
		Writer writer;
		Context context;
		
		try {
			template = velocityEngine.getTemplate(plantilla);
			
			context = new VelocityContext();
			
			
			writer = new FileWriter(fileResult);
						
			template.merge(context, writer);
			
			LOGGER.info(template.requiresChecking());
			
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//TODO
		
		LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
	
}
