package org.example.test.views.components.menu.buttons;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JMenuItem;

import org.example.test.events.AboutEvent;
import org.example.test.events.ApplicationEvent;
import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObserver;

public class AboutMenuItem extends JMenuItem implements ApplicationObserver {

	private static final long serialVersionUID = 1266354841616399872L;

	private ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
	
	private ApplicationObservable observable;
	
	public AboutMenuItem() {
		this.initComponents();
	}
	
	private void initComponents() {
		super.setText(resourceBundle.getString("menu.button.about.text"));
		super.setCursor(new Cursor(Cursor.HAND_CURSOR));
		super.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				observable.notify(new AboutEvent());
			}
		});
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
	public void listener(ApplicationEvent evento) { }

}
