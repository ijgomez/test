package org.example.test.ds.web.listerner;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

public class TestHttpSessionListener implements HttpSessionListener {

	private static final Logger LOGGER = Logger.getLogger(TestHttpSessionListener.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		if (LOGGER.isInfoEnabled()) { LOGGER.info(String.format("Creando Session... [id=%s]", event.getSession().getId())); }

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		if (LOGGER.isInfoEnabled()) { LOGGER.info(String.format("Destruyendo Session... [id=%s]", event.getSession().getId())); }
	}

}
