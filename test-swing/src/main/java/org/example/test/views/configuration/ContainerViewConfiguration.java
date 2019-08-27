package org.example.test.views.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContainerViewConfiguration {

	private String titleTextKey;
	
	private String toolTipTextKey;
	
	private Class<?> classEntity;
	
	private Class<?> classContainer;

}
