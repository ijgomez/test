package org.example.test.views.components.dialog.init;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ApplicationInitializationDialogPanel extends JPanel {


	private static final long serialVersionUID = -7761631327237930071L;

	public ApplicationInitializationDialogPanel() {
		this.initializateGUI();
	}

	private void initializateGUI() {
		// TODO Auto-generated method stub
		super.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
	}
}
