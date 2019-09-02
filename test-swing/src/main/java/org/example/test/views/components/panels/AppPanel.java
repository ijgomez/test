package org.example.test.views.components.panels;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import javax.swing.JPanel;

import org.example.test.views.components.ApplicationModel;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.events.ApplicationEvent;
import org.example.test.views.factories.ResourcesFactory;
import org.example.test.views.resources.TextResources;

public abstract class AppPanel extends JPanel implements ApplicationModelListener {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = 1856853307207687798L;

	protected TextResources textResources = ResourcesFactory.getFactory().text();
	
	protected ApplicationModel model;
	
	private Map<Class<?>, Consumer<ApplicationEvent>> handlers = new HashMap<Class<?>, Consumer<ApplicationEvent>>();
	
	public AppPanel() {
		this.initializateGUI();
		this.registerEventListeners();
	}

	/**
	 * Method that contains the definition of the visual elements of the component.
	 */
	protected abstract void initializateGUI();
	
	protected abstract void registerEventListeners();
	
	@Override
	public void setModel(ApplicationModel model) {
		if (model != null) {
			this.model = model;
			this.model.register(this);
			this.updateView();
		} else {
			this.model.unregister(this);
			this.model = model;
		}
	}
	
	@Override
	public abstract void updateView();
	
	@Override
	public void listener(ApplicationEvent event) { 
		if (this.handlers.containsKey(event.getClass())) {
			this.handlers.get(event.getClass()).accept(event);
		}
	}
	
	protected void addEventListener(Class<?> key, Consumer<ApplicationEvent> value) {
		this.handlers.put(key, value);
	}
}
