package org.example.test.views.resources;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.swing.ImageIcon;

import org.example.test.views.helpers.ImageHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImagesResources {

	private Map<String, String> resources;
	
	public ImagesResources() {
		resources = new HashMap<>();
	}
	
	public void register(String key, String value) {
		if (!this.resources.containsKey(key)) {
			this.resources.put(key, value);
		}
	}
	
	public Optional<ImageIcon> getImageIcon(String key) {
		try {
			String value = resources.get(key);
			if (!value.isEmpty()) {
				return Optional.of(ImageHelper.loadImageIcon(value));
			}
		} catch (Exception e) {
			log.error("Invalid key {} for icon: {}", key, e);
		}
		return Optional.empty();
	}
	
	public Optional<Image> getImage(String key) {
		try {
			String value = resources.get(key);
			if (!value.isEmpty()) {
				return Optional.of(ImageHelper.loadImage(value));
			}
		} catch (Exception e) {
			log.error("Invalid key {} for images: {}", key, e);
		}
		return Optional.empty();
	}
	
}
