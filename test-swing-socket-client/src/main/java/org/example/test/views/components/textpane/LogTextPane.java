package org.example.test.views.components.textpane;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextPane;

import org.apache.log4j.Logger;
import org.example.test.events.ApplicationEvent;
import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObserver;

public class LogTextPane extends JTextPane implements ApplicationObserver {

	private static final long serialVersionUID = 1793548340836595084L;

	private ApplicationObservable observable;

	public LogTextPane() {
		initComponents();
	}
	
	private void initComponents() {
		super.setEditable(false);
		super.setFocusable(false);
		super.setFont((new Font("Courier New", 0, 12)));
		Logger.getRootLogger().addAppender(new LogTextPaneAppender(super.getStyledDocument()));
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		return (getSize().width < getParent().getSize().width);
	}
	
	@Override
	public void setSize(Dimension d) {
		if (d.width < getParent().getSize().width) {
            d.width = getParent().getSize().width;
        }
        super.setSize(d);
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
		// TODO Auto-generated method stub
		
	}
	
	public void clean() {
		//super.setText("");	
	}
}
