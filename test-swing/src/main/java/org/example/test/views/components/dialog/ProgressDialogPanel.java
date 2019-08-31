package org.example.test.views.components.dialog;

import javax.swing.JPanel;

import org.example.test.views.components.panels.DialogPanel;

public class ProgressDialogPanel extends JPanel {

	private static final long serialVersionUID = -1795521026661566935L;
	
	private DialogPanel dialogPanel;

	public ProgressDialogPanel(String text, Integer width) {
		this.initializateGUI(text, width);
	}
	
	private void initializateGUI(String text, Integer width) {
		// TODO Auto-generated constructor stub

		dialogPanel = new DialogPanel(text, width);
		dialogPanel.setInProgressView();
		
		super.add(dialogPanel);
	}
}
