package org.example.test.views.components;

import org.example.test.views.components.events.ApplicationEvent;

/**
 * Interface that defines the operations that all java classes subject to
 * receiving application events must comply with.
 * 
 * @author ijgomez
 *
 */
public interface ApplicationModelListener {

	/**
	 * Application Model.
	 * 
	 * @param model Application Model.
	 */
	public void setModel(ApplicationModel model);
	
	/**
	 * Method that will receive the events generated within the application.
	 * 
	 * @param event New Application Event.
	 */
	public void listener(ApplicationEvent event);
	
	/**
	 * Method that is invoked at the start of the component inside the application,
	 * and that contains the business logic that allows data to be loaded, or
	 * updated, inside the component.
	 */
	public void updateView();
	
}
