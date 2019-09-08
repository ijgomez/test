package org.example.test.views.components.buttons;

import java.awt.Cursor;

import javax.swing.JButton;

import org.example.test.views.factories.ResourcesFactory;
import org.example.test.views.resources.ImagesResources;
import org.example.test.views.resources.TextResources;

public abstract class BasicButton extends JButton {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = 4106812624638240974L;
	
	private TextResources textResources = ResourcesFactory.getFactory().text();
	
	private ImagesResources imagesResources = ResourcesFactory.getFactory().images();
	
	private String iconKey;
	
	private String titleTextKey;
	
	private String toolTipTextKey;

	public BasicButton(String iconKey, String titleTextKey, String toolTipTextKey) {
		this(titleTextKey, toolTipTextKey);
		this.iconKey = iconKey;
		this.initializateGUI();
	}
	
	public BasicButton(String titleTextKey, String toolTipTextKey) {
		this.titleTextKey = titleTextKey;
		this.toolTipTextKey = toolTipTextKey;
		this.initializateGUI();
	}
	
	public BasicButton(String iconKey) {
		this.iconKey = iconKey;
		this.initializateGUI();
	}

	/**
	 * Method that contains the definition of the visual elements of the component.
	 */
	private void initializateGUI() {
		this.imagesResources.getImageIcon(iconKey).ifPresent((i) -> super.setIcon(i));
		this.textResources.getString(titleTextKey).ifPresent((t) -> super.setText(t));
		this.textResources.getString(toolTipTextKey).ifPresent((t) -> super.setToolTipText(t));

		super.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		this.handlerInitializateGUI();
	}

	protected abstract void handlerInitializateGUI();
	
}
