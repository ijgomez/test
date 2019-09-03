package org.example.test.views.components.dialog;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.example.test.views.components.panels.DialogPanel;

public class ProgressDialogPanel extends JPanel {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = -1795521026661566935L;
	
	private DialogPanel dialogPanel;

	/**
	 * New Instance.
	 * 
	 * @param text  Text of dialog.
	 * @param width Width of dialog.
	 */
	public ProgressDialogPanel(String text, Integer width) {
		this.initializateGUI(text, width);
	}

	/**
	 * Method that contains the definition of the visual elements of the component.
	 */
	private void initializateGUI(String text, Integer width) {
		// TODO Auto-generated constructor stub

		dialogPanel = new DialogPanel(text, width);
		dialogPanel.setInProgressView();
		
		super.setLayout(new BorderLayout());
		super.add(dialogPanel, BorderLayout.CENTER);
	}
}
