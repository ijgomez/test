package org.example.test.views.resources;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

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
	
	public ImageIcon getImageIcon(String key) {
		try {
			return ImageHelper.loadImageIcon(resources.get(key));
		} catch (Exception e) {
			log.error("Invalid key {} for icon: {}", key, e);
			return null;
		}
	}
	
	public Image getImage(String key) {
		try {
			return ImageHelper.loadImage(resources.get(key));
		
		} catch (Exception e) {
			log.error("Invalid key {} for images: {}", key, e);
			return null;
		}
	}
	
}
