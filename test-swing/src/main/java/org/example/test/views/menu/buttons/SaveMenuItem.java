package org.example.test.views.menu.buttons;

import java.awt.Cursor;

import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.events.ApplicationEvent;
import org.example.test.views.components.events.SaveFileEvent;
import org.example.test.views.components.menubar.buttons.AppMenuItem;

public class SaveMenuItem extends AppMenuItem implements ApplicationModelListener {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = -3411334708945643057L;

	/**
	 * Method that contains the definition of the visual elements of the component.
	 */
	protected void initializateGUI() {
		this.textResources.getString("menu.button.save.text").ifPresent((t) -> super.setText(t));
		this.textResources.getString("menu.button.save.tool.tip").ifPresent((t) -> super.setToolTipText(t));

		super.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		super.addActionListener((e) -> model.notify(new SaveFileEvent()));
	}
	
	@Override
	public void listener(ApplicationEvent event) { }

	@Override
	public void updateView() { }
}
