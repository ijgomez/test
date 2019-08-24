package org.example.test.views.components;

import org.example.test.views.events.ApplicationEvent;

public interface ApplicationModelListener {
	
	public void setModel(ApplicationModel model);
	
	public void listener(ApplicationEvent event);
	
}
