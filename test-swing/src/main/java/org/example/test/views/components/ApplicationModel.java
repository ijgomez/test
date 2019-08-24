package org.example.test.views.components;

import org.example.test.views.components.events.ApplicationEvent;

public interface ApplicationModel {
	
	public void register(ApplicationModelListener listener);
	
	public void unregister(ApplicationModelListener listener);
	
	public void notify(ApplicationEvent event);
}
