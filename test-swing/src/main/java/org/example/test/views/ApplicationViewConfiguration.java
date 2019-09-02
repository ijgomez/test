package org.example.test.views;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.JButton;

import org.example.test.views.annotations.AnnotationsHelper;
import org.example.test.views.annotations.ApplicationContainerViewConfig;
import org.example.test.views.components.exceptions.ApplicationViewException;
import org.example.test.views.configuration.ContainerViewConfiguration;
import org.example.test.views.factories.ContainerViewFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * Class that models the information that the application needs to configure the
 * different windows and user interface options.
 * 
 * @author jizquierdo
 *
 */
@Slf4j
public class ApplicationViewConfiguration {

	/** List of configurations of the different main views of the application. */
	private List<ContainerViewConfiguration> containerViewConfigurations;
	
	/**
	 * New Instance.
	 */
	public ApplicationViewConfiguration() {
		this.containerViewConfigurations = new ArrayList<>();
	}
	
	/**
	 * Method that scans the different class packages, in search of the annotations
	 * with the configuration of the views.
	 * 
	 * @param packages Packages.
	 */
	public void load(String... packages) {
		log.debug("Loading view configuration from annotated class...");
		log.debug("Scan packages: " + Arrays.toString(packages));
		try {
		
			for (String packageName : packages) {
				
				AnnotationsHelper.getClasses(packageName).forEach((c) -> {
					
					Annotation annotation = Stream.of(c.getAnnotations())
							.filter((a) -> a instanceof ApplicationContainerViewConfig)
							.findFirst().orElse(null);
					
					if (annotation != null) {
						ApplicationContainerViewConfig ac = (ApplicationContainerViewConfig)annotation;
						this.containerViewConfigurations.add(new ContainerViewConfiguration(JButton.class, ac.titleTextKey(), ac.toolTipTextKey(), c, ac.selected(), ac.order()));
						if (ac.selected()) {
							ContainerViewFactory.getInstance().setDefaultContainerView(c);
						}
					}
					
				});
			}
			
			//Sort by order
			containerViewConfigurations.sort((ContainerViewConfiguration c1, ContainerViewConfiguration c2) -> c1.getOrder() - c2.getOrder());
			
			// TODO Auto-generated method stub
			
		} catch (Exception e) {
			throw new ApplicationViewException("Failed to configure user interface:", e);
		}
	}
	
	
	public List<ContainerViewConfiguration> getContainerViews() {
		return containerViewConfigurations;
	}
	
}
