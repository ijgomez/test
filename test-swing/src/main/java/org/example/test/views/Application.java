package org.example.test.views;

import java.awt.Frame;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.commons.lang3.SystemUtils;
import org.example.test.views.annotations.ApplicationViewScan;
import org.example.test.views.components.frames.AppFrame;
import org.example.test.views.factories.ResourcesFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * Class that executes the application, reading the different configuration
 * options (annotations, parameters, configuration files, ...).
 * 
 * @author jizquierdo
 *
 */
@Slf4j
public class Application {

	/**
	 * Application execution method.
	 * 
	 * @param className Main Class.
	 * @param args      Application execution method.
	 */
	public static void run(Class<?> className, String... args) {
		
		SwingUtilities.invokeLater(() -> {
			AppFrame frame;
			ApplicationViewConfiguration viewConfiguration;
			Constructor<?> constructor;
			String[] packages = null;
			String[] textResources = null;
			
			try {
				setLookAndFeelApplication();
			
				Annotation annotation = Stream.of(className.getAnnotations())
						.filter((a) -> a instanceof ApplicationViewScan)
						.findAny()
						.orElseThrow(() -> new RuntimeException("Class not valid. Class not annotated!"));//TODO Change to Exception
				
				packages = ((ApplicationViewScan) annotation).packages();
				textResources = ((ApplicationViewScan) annotation).textResources();
				
				viewConfiguration = new ApplicationViewConfiguration();
				viewConfiguration.load(packages);
				
				ResourcesFactory.getFactory().load(textResources, null);
				
				constructor = className.getConstructor(ApplicationViewConfiguration.class, String[].class);
				
				frame = (AppFrame) constructor.newInstance(viewConfiguration, (Object)args);
				frame.setVisible(true);
				frame.setExtendedState(Frame.MAXIMIZED_BOTH);
				frame.toFront();
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				log.error("fail to init app...", e);
			}
			
		});

      
	}

	/**
	 * Method that configures the visual styles of the application.
	 */
	private static void setLookAndFeelApplication() {
		try {
			if (SystemUtils.IS_OS_MAC_OSX) {
				// set the brushed metal look and feel, if desired
				System.setProperty("apple.awt.brushMetalLook", "true");
				  
				// use the mac system menu bar
				System.setProperty("apple.laf.useScreenMenuBar", "true");
				  
				// set the "About" menu item name
				System.setProperty("com.apple.mrj.application.apple.menu.about.name", "WikiStar");
				  
				// use smoother fonts
				System.setProperty("apple.awt.textantialiasing", "true");
				
				// ref: http://developer.apple.com/releasenotes/Java/Java142RNTiger/1_NewFeatures/chapter_2_section_3.html
				System.setProperty("apple.awt.graphics.EnableQ2DX","true");
				  
				// use the system look and feel
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());					
			}

		    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception ex) {
		    log.error("Setting look and feel failed:", ex);
		}
	}
}
