package org.example.test.views.factories;

import java.util.ResourceBundle;
import java.util.stream.Stream;

import org.example.test.views.components.helpers.EnumHelper;
import org.example.test.views.resources.ImagesResources;
import org.example.test.views.resources.TextResources;

public class ResourcesFactory {

	private static final String DEFAULT_TEXT_RESOURCES = "messages";

	private static final ResourcesFactory FACTORY = new ResourcesFactory();
	
	private TextResources textResources;
	
	private ImagesResources imagesResources;
	
	public static ResourcesFactory getFactory() {
		return FACTORY;
	}
	
	public void load(String[] textFileNames, String[] imagesFileNames) {
		this.loadTextResources(textFileNames);
		// TODO Auto-generated constructor stub
	}
	
	private void loadTextResources(String[] baseNames) {
		textResources = new TextResources();

		Stream.of(baseNames).forEach((baseName) -> {
			ResourceBundle bundle = ResourceBundle.getBundle(baseName);
			EnumHelper.enumerationAsStream(bundle.getKeys()).forEach((key) -> {
				textResources.register(key, bundle.getString(key));
			});
		});
		
		ResourceBundle defaultBundle = ResourceBundle.getBundle(DEFAULT_TEXT_RESOURCES);
		EnumHelper.enumerationAsStream(defaultBundle.getKeys()).forEach((key) -> {
			textResources.register(key, defaultBundle.getString(key));
		});
	}
	
	public TextResources text() {
		return this.textResources;
	}
	
	public ImagesResources images() {
		return this.imagesResources;
	}
}
