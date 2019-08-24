package org.example.test.views.menu.buttons;

import java.awt.Cursor;

import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.menubar.buttons.AppMenuItem;
import org.example.test.views.events.ApplicationEvent;
import org.example.test.views.events.OpenFileEvent;

public class OpenMenuItem extends AppMenuItem implements ApplicationModelListener {

	private static final long serialVersionUID = -4350920360796110252L;

	protected void initializateGUI() {
		super.setText(messagesResources.getString("menu.button.open.text"));
		super.setToolTipText(messagesResources.getString("menu.button.open.tool.tip"));
		super.setCursor(new Cursor(Cursor.HAND_CURSOR));
		super.addActionListener((e) -> model.notify(new OpenFileEvent()));
	}
	
	@Override
	public void listener(ApplicationEvent event) { }

}
