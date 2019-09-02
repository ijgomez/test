package org.example.test.views.components.dialog;

import javax.swing.JPanel;

public abstract class WarningDialogPanel extends JPanel {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = -3891108292086522840L;

	private String message;
	
	public WarningDialogPanel(String message) {
		this.message = message;
		// TODO Auto-generated constructor stub
	}
	
	public abstract void handlerConfirmMessageAction();
}
