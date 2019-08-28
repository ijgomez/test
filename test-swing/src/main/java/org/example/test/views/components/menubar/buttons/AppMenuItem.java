package org.example.test.views.components.menubar.buttons;

import java.util.ResourceBundle;

import javax.swing.JMenuItem;

import org.example.test.views.components.ApplicationModel;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.helpers.LocaleHelper;

public abstract class AppMenuItem extends JMenuItem implements ApplicationModelListener {

	private static final long serialVersionUID = 7730448020444982488L;
	
	protected ResourceBundle messagesResources = LocaleHelper.getMessagesResources();
	
	protected ApplicationModel model;

	public AppMenuItem() {
		this.initializateGUI();
	}

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
