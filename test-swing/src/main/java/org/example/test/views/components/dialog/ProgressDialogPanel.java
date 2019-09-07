package org.example.test.views.components.dialog;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.example.test.views.components.panels.DialogPanel;

public class ProgressDialogPanel extends JPanel {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = -1795521026661566935L;
	
	private DialogPanel dialogPanel;
	
	private String title;


	/**
	 * New Instance.
	 * 
	 * @param title  Title of dialog.
	 */
	public ProgressDialogPanel(String title) {
		this.title = title;
		this.initializateGUI();
	}

	/**
	 * Method that contains the definition of the visual elements of the component.
	 */
	private void initializateGUI() {
		dialogPanel = new DialogPanel(this.title);
		dialogPanel.setInProgressStatusView();

		super.setLayout(new BorderLayout());
		super.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		super.add(dialogPanel, BorderLayout.CENTER);
	}
}
