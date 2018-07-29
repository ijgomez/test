package org.example.test.events;


public class ApplicationEvent {

	private EventType eventType;
	
	public EventType getEventType() {
		return eventType;
	}
	
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
}
