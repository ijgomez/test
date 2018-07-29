package org.example.test.views.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.example.test.controllers.DataService;
import org.example.test.controllers.DataServiceException;
import org.example.test.domain.Configuration;
import org.example.test.events.ApplicationEvent;
import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObserver;
import org.example.test.views.components.buttons.CloseButton;

public class OptionsDialog extends JDialog implements ApplicationObserver {

	private static final long serialVersionUID = -5138834679878178873L;
	
	private static final Dimension DIMENSION = new Dimension(400, 200);

    private ApplicationObservable observable;
	
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
	
	private DataService<Configuration> dataService = DataService.CONFIGURATION;
	
	public OptionsDialog(JFrame owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		JPanel infoPanel, buttonPanel;
		JButton saveButton, closeButton;
		
		infoPanel = new JPanel();
		
		saveButton = new JButton();
		saveButton.setText("Guardar");
		
		closeButton = new CloseButton(){

			private static final long serialVersionUID = -8066366699613660046L;

			@Override
			public void actionPerformed(ActionEvent e) {
				closeDialogAction();
			}
			
		};
		
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(saveButton);
		buttonPanel.add(closeButton);
		
		super.setTitle(resourceBundle.getString("dialog.options.title"));
		super.setModal(true);
		super.setSize(DIMENSION);
		super.setLayout(new BorderLayout());
		super.add(infoPanel, BorderLayout.CENTER);
		super.add(buttonPanel, BorderLayout.SOUTH);
		super.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent evt) {
				try {
					loadData();
				} catch (DataServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Override
			public void windowClosing(WindowEvent evt) {
				closeDialogAction();
			}	
		});
	}

	protected void loadData() throws DataServiceException {
		Configuration configuration;
		
		configuration = this.dataService.load(null);
		// TODO Auto-generated catch block
	}

	protected void closeDialogAction() {
		super.setVisible(false);
		super.dispose();
	}
	
	@Override
	public void registerIn(ApplicationObservable observable) {
		if (observable != null) {
			this.observable = observable;
			this.observable.add(this);
		} else {
			this.observable.remove(this);
			this.observable = observable;
		}
	}
	
	@Override
	public void listener(ApplicationEvent event) {
		//TODO
	}
	
	
}
