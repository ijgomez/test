package org.example.test.views.resources;

import org.example.test.views.factories.ResourcesFactory;

public enum Resources {

	APPLICATION_TITLE("application.title");
	
	private String textKey;
	
	private Resources(String textKey) {
		this.textKey = textKey;
	}
	
	public String getText() {
		return ResourcesFactory.getFactory().text().getString(textKey);
	}
	

}
