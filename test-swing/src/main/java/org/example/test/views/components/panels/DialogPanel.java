package org.example.test.views.components.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.example.test.views.factories.ResourcesFactory;
import org.example.test.views.resources.TextResources;

public class DialogPanel extends JPanel {

	private static final long serialVersionUID = -6738536289459473508L;
	
	private TextResources textResources = ResourcesFactory.getFactory().text();
	
	private JLabel iconStatus;
	
	private JLabel nameStatus;
	
	private JLabel textStatus;

	public DialogPanel(String text, Integer width) {
		// TODO Auto-generated constructor stub
		this.initializateGUI(text);
	}
	
	private void initializateGUI(String name) {
		
		this.iconStatus = new JLabel();
		this.nameStatus = new JLabel(name);
		this.textStatus = new JLabel();
		
		super.add(iconStatus);
		super.add(nameStatus);
		super.add(textStatus);
		
		// TODO Auto-generated constructor stub
	}

	public void setInProgressView() {
		
		this.textStatus.setText(textResources.getString("dialog.progress.status.progress.text"));
		// TODO Auto-generated method stub
		
	}
}
