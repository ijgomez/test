package org.example.test.views.factories;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.exceptions.ApplicationViewException;
import org.example.test.views.components.panels.EmptyContainerPanel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContainerViewFactory {

	private static final ContainerViewFactory INSTANCE = new ContainerViewFactory();
	
	public static ContainerViewFactory getInstance() {
		return INSTANCE;
	}
	
	private Map<Class<?>, Class<?>> views;
	
	private ContainerViewFactory() {
		this.views = Collections.synchronizedMap(new HashMap<>());
		
		//this.views.put(Mock.class, EmptyContainerPanel.class);
		// TODO Auto-generated catch block
	}
	
	public ApplicationModelListener getContainerView(Class<?> classEntity) throws ApplicationViewException {
		try {
			
			log.trace("Creating container view for the entity {}...", classEntity);
			return (ApplicationModelListener) this.views.getOrDefault(classEntity, EmptyContainerPanel.class).newInstance();
			
		} catch (InstantiationException | IllegalAccessException e) {
			throw new ApplicationViewException("Failed to create container view.", e);
		}
		
	}
	
}
