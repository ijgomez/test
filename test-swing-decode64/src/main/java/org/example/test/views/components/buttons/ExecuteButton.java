package org.example.test.views.components.buttons;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;

import org.example.test.events.ApplicationEvent;
import org.example.test.events.ExecuteEvent;
import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObserver;

public class ExecuteButton extends JButton implements ActionListener, ApplicationObserver {

	private static final long serialVersionUID = 9124367834491909447L;

	private ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
	
	public ExecuteButton() {
		this.initComponents();
	}

	private void initComponents() {
		super.setText(resourceBundle.getString("button.execute.text"));
		super.setToolTipText(resourceBundle.getString("button.execute.tooltip"));
		super.setCursor(new Cursor(Cursor.HAND_CURSOR));
		super.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.observable.notify(new ExecuteEvent());
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
