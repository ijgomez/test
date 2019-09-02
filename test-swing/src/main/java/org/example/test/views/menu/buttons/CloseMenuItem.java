package org.example.test.views.menu.buttons;

import java.awt.Cursor;

import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.events.ApplicationEvent;
import org.example.test.views.components.events.CloseApplicationEvent;
import org.example.test.views.components.menubar.buttons.AppMenuItem;

public class CloseMenuItem extends AppMenuItem implements ApplicationModelListener {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = 5756386904187105201L;

	/**
	 * Method that contains the definition of the visual elements of the component.
	 */
	protected void initializateGUI() {
		super.setText(textResources.getString("menu.button.close.text"));
		super.setToolTipText(textResources.getString("menu.button.close.tool.tip"));
		super.setCursor(new Cursor(Cursor.HAND_CURSOR));
		super.addActionListener((e) -> model.notify(new CloseApplicationEvent()));
	}
	
	@Override
	public void listener(ApplicationEvent event) { }

	@Override
	public void updateView() { }
}
