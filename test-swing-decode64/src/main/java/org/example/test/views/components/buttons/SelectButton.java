package org.example.test.views.components.buttons;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;

import org.example.test.events.ApplicationEvent;
import org.example.test.events.SelectFileEvent;
import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObserver;

public class SelectButton extends JButton implements ActionListener, ApplicationObserver {

	private static final long serialVersionUID = 9124367834491909447L;

	private ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
	
	public SelectButton() {
		this.initComponents();
	}

	private void initComponents() {
		super.setText(resourceBundle.getString("button.select.text"));
		super.setToolTipText(resourceBundle.getString("button.select.tooltip"));
		super.setCursor(new Cursor(Cursor.HAND_CURSOR));
		super.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.observable.notify(new SelectFileEvent());
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
