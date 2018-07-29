package org.example.test.views.components;

import java.util.ArrayList;
import java.util.List;

import org.example.test.events.ApplicationEvent;

public class ApplicationObservableImpl implements ApplicationObservable {
	
	private List<ApplicationObserver> observers;
	
	public ApplicationObservableImpl() {
		observers = new ArrayList<ApplicationObserver>();
	}

	@Override
	public void add(ApplicationObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public void remove(ApplicationObserver observer) {
		this.observers.remove(observer);
	}
	
	@Override
	public void notify(ApplicationEvent evento) {
		for (ApplicationObserver applicationObserver : observers) {
			applicationObserver.listener(evento);
		}
	}

}
