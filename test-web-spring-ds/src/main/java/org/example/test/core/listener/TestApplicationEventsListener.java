package org.example.test.core.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class TestApplicationEventsListener implements ApplicationListener<ApplicationEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestApplicationEventsListener.class);

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		
		LOGGER.trace("event -> {}", event);
		
	}

}
