package org.example.test.views.components;

import org.example.test.events.ApplicationEvent;

public interface ApplicationObserver {
	
	public void registerIn(ApplicationObservable observable);
	
	public void listener(ApplicationEvent evento);
	//public void update();
	
}
