package org.example.test.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestHttpSessionListener implements HttpSessionListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestHttpSessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		LOGGER.info("Creando Session... [id={}]", event.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		LOGGER.info("Destruyendo Session... [id={}]", event.getSession().getId());
	}

}
