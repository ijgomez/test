package org.example.test.views.menu.buttons;

import java.awt.Cursor;

import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.events.ApplicationEvent;
import org.example.test.views.components.events.OpenSettingsDialogEvent;
import org.example.test.views.components.menubar.buttons.AppMenuItem;

public class SettingsMenuItem extends AppMenuItem implements ApplicationModelListener {

	private static final long serialVersionUID = 1266354841616399872L;

	protected void initializateGUI() {
		super.setText(messagesResources.getString("menu.button.settings.text"));
		super.setToolTipText(messagesResources.getString("menu.button.settings.tool.tip"));
		super.setCursor(new Cursor(Cursor.HAND_CURSOR));
		super.addActionListener((e) -> model.notify(new OpenSettingsDialogEvent()));
	}
	
	@Override
	public void listener(ApplicationEvent event) { }

}
