package org.example.test.views.components;

import org.example.test.views.components.events.ApplicationEvent;

public interface ApplicationModelListener {
	
	public void setModel(ApplicationModel model);
	
	public void listener(ApplicationEvent event);
	
	public void updateView();
	
}
