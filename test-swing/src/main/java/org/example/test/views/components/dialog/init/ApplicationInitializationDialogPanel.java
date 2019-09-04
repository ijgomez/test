package org.example.test.views.components.dialog.init;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.example.test.views.components.panels.DialogPanel;
import org.example.test.views.factories.ResourcesFactory;
import org.example.test.views.helpers.SleepHelper;
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
		JPanel taskPanel, buttonPanel;
		
		titleLabel = new JLabel(textResources.getString("application.title"), SwingConstants.CENTER);
		titleLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		taskPanel = new JPanel();
		
		this.dialogPanels = new ArrayList<>();
		this.dialogPanels.add(new DialogPanel("Model"));
		this.dialogPanels.add(new DialogPanel("Listeners"));
		this.dialogPanels.add(new DialogPanel("Business"));
		this.dialogPanels.add(new DialogPanel("Hook"));
		
		// TODO Auto-generated method stub
		for (DialogPanel dialogPanel : dialogPanels) {
			dialogPanel.setLoadingView();
			
			taskPanel.add(dialogPanel);
		}
		// TODO Auto-generated method stub
		
		this.cancelButton = new JButton("Cancel");
		
		this.exitButton = new JButton("Exit");
		
		buttonPanel = new JPanel();
		buttonPanel.add(this.cancelButton);
		buttonPanel.add(this.exitButton);
		
		super.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		super.setLayout(new BorderLayout());
		super.add(titleLabel, BorderLayout.NORTH);
		super.add(taskPanel, BorderLayout.CENTER);
		super.add(buttonPanel, BorderLayout.SOUTH);
	}

	public void setStatusCompleteTask(String key) {
		// TODO Auto-generated method stub
		SleepHelper.sleep(1000L);
	}
}
