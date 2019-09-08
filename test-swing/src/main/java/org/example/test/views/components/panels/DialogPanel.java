package org.example.test.views.components.panels;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.example.test.views.factories.ResourcesFactory;

/**
 * Class that defines the common panel to show the different messages to the
 * user.
 * 
 * @author ijgomez
 *
 */
public class DialogPanel extends JPanel {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = -6738536289459473508L;
	
	private ResourcesFactory resourcesFactory = ResourcesFactory.getFactory();

	private JLabel iconStatusLabel;
	
	private JLabel nameStatusLabel;
	
	private JLabel textStatusLabel;
	
	private String title;

	public DialogPanel(String title) {
		this.title = title;
		this.initializateGUI();
	}

	/**
	 * Method that contains the definition of the visual elements of the component.
	 */
	private void initializateGUI() {
		JPanel messagePanel;
		
		this.iconStatusLabel = new JLabel();
		
		this.nameStatusLabel = new JLabel(this.title);
		this.nameStatusLabel.setFont(this.nameStatusLabel.getFont().deriveFont((float) (this.nameStatusLabel.getFont().getSize() * 1.5)));
		
		this.textStatusLabel = new JLabel();
		
		messagePanel = new JPanel();
		messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
		messagePanel.add(Box.createVerticalGlue());
		messagePanel.add(nameStatusLabel);
		messagePanel.add(textStatusLabel);
		messagePanel.add(Box.createVerticalGlue());

		super.setLayout(new BorderLayout(10, 10));
		super.add(this.iconStatusLabel, BorderLayout.WEST);
		super.add(messagePanel, BorderLayout.CENTER);
	}

	public void setWaitingStatusView() {	
		this.resourcesFactory.images().getImageIcon("dialog.status.waiting.icon").ifPresent((i) -> this.iconStatusLabel.setIcon(i));	
		this.resourcesFactory.text().getString("dialog.status.waiting.text").ifPresent((t) -> this.textStatusLabel.setText(t));
		
		this.revalidate();
		this.repaint();
	}
	
	public void setInLoadingStatusView() {
		this.resourcesFactory.images().getImageIcon("dialog.status.loading.icon").ifPresent((i) -> this.iconStatusLabel.setIcon(i));
		this.resourcesFactory.text().getString("dialog.status.loading.text").ifPresent((t) -> this.textStatusLabel.setText(t));
		
		this.revalidate();
		this.repaint();
	}

	public void setInProgressStatusView() {
		this.resourcesFactory.images().getImageIcon("dialog.status.inprogress.icon").ifPresent((i) -> this.iconStatusLabel.setIcon(i));
		this.resourcesFactory.text().getString("dialog.status.inprogress.text").ifPresent((t) -> this.textStatusLabel.setText(t));

		this.revalidate();
		this.repaint();
	}
	
	public void setErrorView(Throwable th) {
		this.resourcesFactory.images().getImageIcon("dialog.status.error.icon").ifPresent((i) -> this.iconStatusLabel.setIcon(i));
		this.textStatusLabel.setText(th.getMessage());
			
		this.revalidate();
		this.repaint();
	}
	
	public void setCompleteStatusView() {
		this.resourcesFactory.images().getImageIcon("dialog.status.completed.icon").ifPresent((i) -> this.iconStatusLabel.setIcon(i));
		this.resourcesFactory.text().getString("dialog.status.completed.text").ifPresent((t) -> this.textStatusLabel.setText(t));

		this.revalidate();
		this.repaint();
	}

}
