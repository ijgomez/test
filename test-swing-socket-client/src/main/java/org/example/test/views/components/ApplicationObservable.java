package org.example.test.views.components;

import org.example.test.events.ApplicationEvent;

public interface ApplicationObservable {
	
	public void add(ApplicationObserver observer);
	
	public void remove(ApplicationObserver observer);
	
	public void notify(ApplicationEvent event);
}
