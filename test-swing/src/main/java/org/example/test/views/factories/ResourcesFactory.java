package org.example.test.views.factories;

import java.awt.Image;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import javax.swing.ImageIcon;

import org.example.test.views.helpers.EnumHelper;
import org.example.test.views.resources.ImagesResources;
import org.example.test.views.resources.TextResources;

public class ResourcesFactory {

	private static final String DEFAULT_TEXT_RESOURCES = "messages";
	
	private static final String DEFAULT_IMAGES_RESOURCES = "images";

	private static final ResourcesFactory FACTORY = new ResourcesFactory();
	
	public static ResourcesFactory getFactory() {
		return FACTORY;
	}
	
	public static Optional<ImageIcon> getImageIcon(String key) {
		return getFactory().images().getImageIcon(key);
	}
	
	public static Optional<Image> getImage(String key) {
		return getFactory().images().getImage(key);
	}
	
	public static String getString(String key, Object... parameters) {
		return getFactory().text().getString(key, parameters);
	}
	
	private TextResources textResources;
	
	private ImagesResources imagesResources;
	
	public void load(String[] textFileNames, String[] imagesFileNames) {
		this.loadTextResources(textFileNames);
		this.loadImageResources(imagesFileNames);
		// TODO Auto-generated constructor stub
	}
	
	private void loadTextResources(String[] baseNames) {
		textResources = new TextResources();

		if (baseNames != null) {
			Stream.of(baseNames).forEach((baseName) -> {
				ResourceBundle bundle = ResourceBundle.getBundle(baseName);
				EnumHelper.enumerationAsStream(bundle.getKeys()).forEach((key) -> {
					textResources.register(key, bundle.getString(key));
				});
			});
		}
		
		ResourceBundle defaultBundle = ResourceBundle.getBundle(DEFAULT_TEXT_RESOURCES);
		EnumHelper.enumerationAsStream(defaultBundle.getKeys()).forEach((key) -> {
			textResources.register(key, defaultBundle.getString(key));
		});
	}
	
	private void loadImageResources(String[] baseNames) {
		imagesResources = new ImagesResources();

		if (baseNames != null) {
			Stream.of(baseNames).forEach((baseName) -> {
				ResourceBundle bundle = ResourceBundle.getBundle(baseName);
				EnumHelper.enumerationAsStream(bundle.getKeys()).forEach((key) -> {
					imagesResources.register(key, bundle.getString(key));
				});
			});
		}
		
		ResourceBundle defaultBundle = ResourceBundle.getBundle(DEFAULT_IMAGES_RESOURCES);
		EnumHelper.enumerationAsStream(defaultBundle.getKeys()).forEach((key) -> {
			imagesResources.register(key, defaultBundle.getString(key));
		});
	}
	
	public TextResources text() {
		return this.textResources;
	}
	
	public ImagesResources images() {
		return this.imagesResources;
	}

}
