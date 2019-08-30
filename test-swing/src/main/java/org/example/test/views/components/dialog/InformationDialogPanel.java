package org.example.test.views.components.dialog;

import javax.swing.JPanel;

public abstract class InformationDialogPanel extends JPanel {

	private static final long serialVersionUID = -6614126316557941802L;

	private String message;
	
	public InformationDialogPanel(String message) {
		this.message = message;
		// TODO Auto-generated constructor stub
	}
	
	public abstract void handlerConfirmMessageAction();
}
