package org.example.test.ws.server;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.example.test.ws.commons.Servicio;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

public class ServicioImpl extends ServletEndpointSupport implements Servicio {
	
	private static final Logger LOGGER = Logger.getLogger(Servicio.class);

	protected void onInit() throws ServiceException {
		LOGGER.info("------->");
		

	}
	
	public void destroy() {
		LOGGER.info("<-------");
		super.destroy();
	}
	
	public String version() {
		// TODO Auto-generated method stub
		return "Prueba";
	}

}
