package org.example.test.views.menu.buttons;

import java.awt.Cursor;

import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.events.ApplicationEvent;
import org.example.test.views.components.events.CloseApplicationEvent;
import org.example.test.views.components.menubar.buttons.AppMenuItem;

public class CloseMenuItem extends AppMenuItem implements ApplicationModelListener {

	private static final long serialVersionUID = 5756386904187105201L;
	
	protected void initializateGUI() {
		super.setText(messagesResources.getString("menu.button.close.text"));
		super.setToolTipText(messagesResources.getString("menu.button.close.tool.tip"));
		super.setCursor(new Cursor(Cursor.HAND_CURSOR));
		super.addActionListener((e) -> model.notify(new CloseApplicationEvent()));
	}
	
	@Override
	public void listener(ApplicationEvent event) { }

	@Override
	public void updateView() { }
}
