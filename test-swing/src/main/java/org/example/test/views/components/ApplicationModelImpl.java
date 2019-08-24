package org.example.test.views.components;

import java.util.ArrayList;
import java.util.List;

import org.example.test.views.events.ApplicationEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationModelImpl implements ApplicationModel {
	
	private List<ApplicationModelListener> listeners;
	
	public ApplicationModelImpl() {
		listeners = new ArrayList<ApplicationModelListener>();
	}

	@Override
	public void register(ApplicationModelListener listener) {
		log.trace("Register Component: {}", listener);
		this.listeners.add(listener);
	}

	@Override
	public void unregister(ApplicationModelListener listener) {
		log.trace("Unregister Component: {}", listener);
		this.listeners.remove(listener);
	}
	
	@Override
	public void notify(ApplicationEvent event) {
		log.trace("Notify event {} in {} listeners.", event, listeners.size());
		listeners.forEach((o) -> o.listener(event));
	}

}
