package org.example.test.views.factories;

import org.example.test.views.resources.ImagesResources;
import org.example.test.views.resources.TextResources;

public class ResourcesFactory {

	private static final ResourcesFactory FACTORY = new ResourcesFactory();
	
	public static ResourcesFactory getFactory() {
		return FACTORY;
	}
	
	public void load(String... fileNames) {
		// TODO Auto-generated constructor stub
	}
	
	
	public TextResources text() {
		// TODO Auto-generated constructor stub
		return null;
	}
	
	public ImagesResources images() {
		// TODO Auto-generated constructor stub
		return null;
	}
}
