package org.example.test.web.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.web.context.support.ServletRequestHandledEvent;

public class TestRequestEventsListener implements ApplicationListener<ServletRequestHandledEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestRequestEventsListener.class);

	@Override
	public void onApplicationEvent(ServletRequestHandledEvent event) {
		
		LOGGER.info("Peticion: {}", event);
		
	}
	
	

}
