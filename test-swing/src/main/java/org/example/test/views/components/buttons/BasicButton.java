package org.example.test.views.components.buttons;

import java.awt.Cursor;
import java.util.Optional;

import javax.swing.JButton;

import org.example.test.views.factories.ResourcesFactory;
import org.example.test.views.resources.TextResources;

public abstract class BasicButton extends JButton {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = 4106812624638240974L;
	
	private TextResources textResources = ResourcesFactory.getFactory().text();
	
	private String titleTextKey;
	
	private Optional<String> toolTipTextKey;

	public BasicButton(String titleTextKey, String toolTipTextKey) {
		this.titleTextKey = titleTextKey;
		this.toolTipTextKey = Optional.of(toolTipTextKey);
		this.initializateGUI();
	}

	/**
	 * Method that contains the definition of the visual elements of the component.
	 */
	private void initializateGUI() {
		super.setText(textResources.getString(titleTextKey));
		this.toolTipTextKey.ifPresent((key) -> super.setToolTipText(textResources.getString(key)));
		super.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.handlerInitializateGUI();
	}

	protected abstract void handlerInitializateGUI();
	
}
