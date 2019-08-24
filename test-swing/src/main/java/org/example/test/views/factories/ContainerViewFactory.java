package org.example.test.views.factories;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.example.test.domain.Mock;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.exceptions.ApplicationViewException;
import org.example.test.views.mock.MockContainerPanel;

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
		
		this.views.put(Mock.class, MockContainerPanel.class);
		// TODO Auto-generated catch block
	}
	
	public ApplicationModelListener getContainerView(Class<?> classEntity) throws ApplicationViewException {
		try {
			log.trace("Creating container view for the entity {}...", classEntity);
			
			if (classEntity != null) {
				return (ApplicationModelListener) this.views.get(classEntity).newInstance();
			}
		} catch (InstantiationException | IllegalAccessException e) {
			throw new ApplicationViewException("Failed to create container view.", e);
		}
		
		// TODO Auto-generated catch block
		return null;
	}
	
}
