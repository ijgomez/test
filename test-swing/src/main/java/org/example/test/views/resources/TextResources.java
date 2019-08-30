package org.example.test.views.resources;

import java.util.HashMap;
import java.util.Map;

public class TextResources {

	private Map<String, String> resources;
	
	public TextResources() {
		resources = new HashMap<>();
	}
	
	public void register(String key, String value) {
		if (!this.resources.containsKey(key)) {
			this.resources.put(key, value);
		}
	}
	
	public String getText(String key) {
		return this.resources.get(key);
	}

	public String getText(String key, String...parameters) {
		// TODO Auto-generated constructor stub
		return null;
	}
}
