package org.example.test.views.events;

public class ChangeViewEvent extends ApplicationEvent {

	private Class<?> classEntity;
	
	public ChangeViewEvent(Class<?> entity) {
		this.classEntity = entity;
	}
	
	public Class<?> getClassEntity() {
		return classEntity;
	}

}
