package org.example.test.views.components.dialog.init;

import java.awt.Color;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.example.test.views.components.panels.DialogPanel;
import org.example.test.views.factories.ResourcesFactory;
import org.example.test.views.resources.TextResources;

public class ApplicationInitializationDialogPanel extends JPanel {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = -7761631327237930071L;
	
	private TextResources textResources = ResourcesFactory.getFactory().text();
	
	private List<DialogPanel> dialogPanels;
	
	private JButton cancelButton;
	
	private JButton exitButton;

	public ApplicationInitializationDialogPanel() {
		this.initializateGUI();
	}

	/**
	 * Method that contains the definition of the visual elements of the component.
	 */
	private void initializateGUI() {
		JLabel titleLabel;
		
		titleLabel = new JLabel();
		titleLabel.setText(textResources.getString("application.title"));
		
		// TODO Auto-generated method stub
		super.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		super.add(titleLabel);
	}
}
