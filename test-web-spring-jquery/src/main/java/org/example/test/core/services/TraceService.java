package org.example.test.core.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TraceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TraceService.class);

	public void info(String mensaje) {
		LOGGER.info(mensaje);
	}

	public void warn(String mensaje) {
		LOGGER.warn(mensaje);

	}

}
