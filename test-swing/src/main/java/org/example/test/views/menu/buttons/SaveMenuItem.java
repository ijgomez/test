package org.example.test.views.menu.buttons;

import java.awt.Cursor;

import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.events.ApplicationEvent;
import org.example.test.views.components.events.SaveFileEvent;
import org.example.test.views.components.menubar.buttons.AppMenuItem;

public class SaveMenuItem extends AppMenuItem implements ApplicationModelListener {

	private static final long serialVersionUID = -3411334708945643057L;

	protected void initializateGUI() {
		super.setText(textResources.getString("menu.button.save.text"));
		super.setToolTipText(textResources.getString("menu.button.save.tool.tip"));
		super.setCursor(new Cursor(Cursor.HAND_CURSOR));
		super.addActionListener((e) -> model.notify(new SaveFileEvent()));
	}
	
	@Override
	public void listener(ApplicationEvent event) { }

	@Override
	public void updateView() { }
}
