package org.example.test.views;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.example.test.ApplicationContext;

public class ApplicationFrame extends JFrame {

	private static final long serialVersionUID = 4661793292792888017L;

	private String[] arguments;

	private ApplicationInitializationDialog initializationDialog;

	public ApplicationFrame(String... args) {
		this.arguments = args;
		this.initializateGUI();
	}

	private void initializateGUI() {
		// TODO Auto-generated method stub
		super.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				initializationAction();
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				confirmExitAction();
			}
		});
	}
	
	private void initializationAction() {
		initializationDialog = new ApplicationInitializationDialog(this, arguments);
		initializationDialog.setVisible(true);
		initializationDialog.toFront();
	}

	private void confirmExitAction() {
		if (ModalDialogFactory.showConfirmExitDialog(this)) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ApplicationContext.close();
			dispose();
		} else {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}

}
