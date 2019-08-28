package org.example.test.views.components.buttons;

import java.awt.Cursor;
import java.util.ResourceBundle;

import javax.swing.JButton;

import org.example.test.views.components.ApplicationModel;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.helpers.LocaleHelper;

public abstract class AppButton extends JButton implements ApplicationModelListener {

	private static final long serialVersionUID = 4106812624638240974L;
	
	private ResourceBundle messagesResources = LocaleHelper.getMessagesResources();
	
	protected ApplicationModel model;
	
	private String titleTextKey;
	
	private String toolTipTextKey;

	public AppButton(String titleTextKey, String toolTipTextKey) {
		this.titleTextKey = titleTextKey;
		this.toolTipTextKey = toolTipTextKey;
		this.initializateGUI();
	}
	
	private void initializateGUI() {
		super.setText(messagesResources.getString(titleTextKey));
		if (toolTipTextKey != null) {
			super.setToolTipText(messagesResources.getString(toolTipTextKey));
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
