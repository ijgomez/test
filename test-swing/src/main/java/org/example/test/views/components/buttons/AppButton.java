package org.example.test.views.components.buttons;

import java.awt.Cursor;

import javax.swing.JButton;

import org.example.test.views.components.ApplicationModel;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.factories.ResourcesFactory;
import org.example.test.views.resources.TextResources;

public abstract class AppButton extends JButton implements ApplicationModelListener {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = 4106812624638240974L;
	
	private TextResources textResources = ResourcesFactory.getFactory().text();
	
	protected ApplicationModel model;
	
	private String titleTextKey;
	
	private String toolTipTextKey;

	public AppButton(String titleTextKey, String toolTipTextKey) {
		this.titleTextKey = titleTextKey;
		this.toolTipTextKey = toolTipTextKey;
		this.initializateGUI();
	}

	/**
	 * Method that contains the definition of the visual elements of the component.
	 */
	private void initializateGUI() {
		super.setText(textResources.getString(titleTextKey));
		if (toolTipTextKey != null) {
			super.setToolTipText(textResources.getString(toolTipTextKey));
		}
		super.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.handlerInitializateGUI();
	}

	protected abstract void handlerInitializateGUI();
	
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
