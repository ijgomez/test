package org.example.test.views.components.panels;

import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.example.test.views.factories.ResourcesFactory;
import org.example.test.views.resources.ImagesResources;
import org.example.test.views.resources.TextResources;

public class DialogPanel extends JPanel {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = -6738536289459473508L;
	
	private TextResources textResources = ResourcesFactory.getFactory().text();
	
	private ImagesResources imagesResources = ResourcesFactory.getFactory().images();
	
	private JLabel iconStatusLabel;
	
	private JLabel nameStatusLabel;
	
	private JLabel textStatusLabel;
	
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
		
		this.iconStatusLabel = new JLabel();
		this.iconStatusLabel.setBounds(new Rectangle(0, 0, 92, 78));

		this.nameStatusLabel = new JLabel(name);
		this.nameStatusLabel.setBounds(new Rectangle(93, 15, (this.width - 120), 20));

		this.textStatusLabel = new JLabel();
		this.textStatusLabel.setBounds(new Rectangle(93, 32, (this.width - 120), 20));
		
		super.setLayout(null);
		super.add(this.iconStatusLabel);
		super.add(this.nameStatusLabel);
		super.add(this.textStatusLabel);
		
		// TODO Auto-generated constructor stub
	}
	
	public void setLoadingView() {

//		this.iconStatusLabel.setIcon(ResourcesFactory.getImageIcon(ImagePathFactory.Components.Dialog.Process.LOADING));		
		this.textStatusLabel.setText("En espera");
		
	}

	public void setInProgressView() {
		this.iconStatusLabel.setIcon(imagesResources.getImageIcon("dialog.progress.status.process.icon"));		
		this.textStatusLabel.setText(textResources.getString("dialog.progress.status.process.text"));
	}
	
	public void setErrorView(Throwable th) {

//		this.iconStatusLabel.setIcon(ResourcesFactory.getImageIcon(ImagePathFactory.Components.Dialog.Process.IN_PROCESS));
			this.textStatusLabel.setText(th.getMessage());
			

	}
	
	public void setCompleteView() {
		
//		this.iconStatusLabel.setIcon(ResourcesFactory.getImageIcon(ImagePathFactory.Components.Dialog.Process.COMPLETED));
		this.textStatusLabel.setText("Completado");
		

	}

}
