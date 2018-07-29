package org.example.test.views.components.panels;

import javax.swing.JPanel;

import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObserver;

public abstract class ApplicationPanel extends JPanel implements ApplicationObserver {

	private static final long serialVersionUID = -7913441528893627119L;
	
	private ApplicationObservable observable;

	public ApplicationPanel() {
		this.initComponents();
	}
	
	protected abstract void initComponents();
	
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

}
