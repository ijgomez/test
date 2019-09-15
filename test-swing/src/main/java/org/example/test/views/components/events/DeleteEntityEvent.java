package org.example.test.views.components.events;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class DeleteEntityEvent extends ApplicationEvent {

	private Class<?> classEntity;
	
	public DeleteEntityEvent(Class<?> entity) {
		this.classEntity = entity;
	}
}
