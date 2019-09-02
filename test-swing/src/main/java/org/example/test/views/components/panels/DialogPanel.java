package org.example.test.views.components.panels;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.example.test.views.factories.ResourcesFactory;
import org.example.test.views.resources.TextResources;

public class DialogPanel extends JPanel {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = -6738536289459473508L;
	
	private TextResources textResources = ResourcesFactory.getFactory().text();
	
	private JLabel iconStatus;
	
	private JLabel nameStatus;
	
	private JLabel textStatus;
	
	private Integer width = 400;

	public DialogPanel(String text, Integer width) {
		this.width = width;
		this.initializateGUI(text);
	}
	
	public DialogPanel(String text) {
		this(text, 400);
	}

	/**
	 * Method that contains the definition of the visual elements of the component.
	 */
	private void initializateGUI(String name) {
		
		this.iconStatus = new JLabel();
		this.iconStatus.setBounds(new Rectangle(0,0,92,78));
		this.iconStatus.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.nameStatus = new JLabel(name);	
		this.nameStatus.setBounds(new Rectangle(93, 15, (this.width-120), 20));
		this.nameStatus.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.textStatus = new JLabel();
		this.textStatus.setBounds(new Rectangle(93, 32, (this.width-120), 20));
		this.textStatus.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		
//		this.iconStatus = new JLabel();
//		this.nameStatus = new JLabel(name);
//		this.textStatus = new JLabel();
		
//		super.setLayout(null);
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
