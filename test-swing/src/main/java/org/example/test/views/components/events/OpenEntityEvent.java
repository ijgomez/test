package org.example.test.views.components.events;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class OpenEntityEvent extends ApplicationEvent {

	private Class<?> classEntity;
	
	private String statusView;
	
	public OpenEntityEvent(Class<?> entity, String statusView) {
		this.classEntity = entity;
		this.statusView = statusView;
	}
	
	
	
	
}
