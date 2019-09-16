package org.example.test.views.settings;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class SettingsContainerView extends JPanel {

	private static final long serialVersionUID = -7732669008696167866L;

	/**
	 * New Instance.
	 */
	public SettingsContainerView() {
		this.initializateGUI();
	}

	private void initializateGUI() {
		JPanel buttonPanel;
		JButton saveButton, cancelButton;
		
		// TODO Auto-generated method stub
		
		saveButton = new JButton("Save");
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener((e) -> closeDialogAction(e));
		
		buttonPanel = new JPanel();
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);
		
		super.setLayout(new BorderLayout());
		super.add(buttonPanel, BorderLayout.SOUTH);
	}

	protected abstract void closeDialogAction(ActionEvent e);
}
