package org.example.test.views.components.events;

import lombok.ToString;

@ToString
public class ChangeViewEvent extends ApplicationEvent {

	private Class<?> classEntity;
	
	public ChangeViewEvent(Class<?> entity) {
		this.classEntity = entity;
	}
	
	public Class<?> getClassEntity() {
		return classEntity;
	}

}
