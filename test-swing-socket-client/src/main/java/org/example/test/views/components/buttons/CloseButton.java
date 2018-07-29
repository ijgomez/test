package org.example.test.views.components.buttons;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;

import org.example.test.events.ApplicationEvent;
import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObserver;

public class CloseButton extends JButton implements ActionListener, ApplicationObserver {

	private static final long serialVersionUID = -5908861919712630484L;
	
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
	
	public CloseButton() {
		this.initComponents();
	}

	private void initComponents() {
		super.setText(resourceBundle.getString("button.close.text"));
		super.setToolTipText(resourceBundle.getString("button.close.tooltip"));
		super.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		super.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO
	}
	
	private ApplicationObservable observable;

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
	public void listener(ApplicationEvent evento) { }

}
