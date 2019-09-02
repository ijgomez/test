package org.example.test.views.components.menubar.buttons;

import javax.swing.JMenuItem;

import org.example.test.views.components.ApplicationModel;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.factories.ResourcesFactory;
import org.example.test.views.resources.TextResources;

public abstract class AppMenuItem extends JMenuItem implements ApplicationModelListener {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = 7730448020444982488L;
	
	protected TextResources textResources = ResourcesFactory.getFactory().text();
	
	protected ApplicationModel model;

	public AppMenuItem() {
		this.initializateGUI();
	}

	/**
	 * Method that contains the definition of the visual elements of the component.
	 */
	protected abstract void initializateGUI();
	
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
}
