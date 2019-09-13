package org.example.test.views.resources;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
	
	public Optional<String> getString(String key) {
		return Optional.of(this.resources.get(key));
	}
	
	public String getStringOrDefault(String key) {
		return this.resources.getOrDefault(key, key);
	}

	public String getString(String key, Object...parameters) {
		return String.format(this.resources.get(key), parameters);
	}
}
