package org.example.test.views;

import java.awt.Frame;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {

	public static void run(Class<ApplicationFrame> className, String... args) {
		
		SwingUtilities.invokeLater(() -> {
			
			try {
//						 // set the brushed metal look and feel, if desired
//						  System.setProperty("apple.awt.brushMetalLook", "true");
//						  
//						  // use the mac system menu bar
//						  System.setProperty("apple.laf.useScreenMenuBar", "true");
//						  
//						  // set the "About" menu item name
//						  System.setProperty("com.apple.mrj.application.apple.menu.about.name", "WikiStar");
//						  
//						  // use smoother fonts
//						  System.setProperty("apple.awt.textantialiasing", "true");
//
//						  // ref: http://developer.apple.com/releasenotes/Java/Java142RNTiger/1_NewFeatures/chapter_2_section_3.html
//						  System.setProperty("apple.awt.graphics.EnableQ2DX","true");
//						  
//						  // use the system look and feel
//						  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

	            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {
	                    UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (Exception ex) {
	            log.error("Setting look and feel failed:", ex);
	        }
			
			try {
				ApplicationViewConfiguration viewConfiguration = new ApplicationViewConfiguration();
				viewConfiguration.load();
				
				Constructor<ApplicationFrame> constructor = className.getConstructor(ApplicationViewConfiguration.class, String[].class);
				
				ApplicationFrame frame = constructor.newInstance(viewConfiguration, (Object)args);
				frame.setVisible(true);
				frame.setExtendedState(Frame.MAXIMIZED_BOTH);
				frame.toFront();
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				log.error("fail to init app...", e);
			}
			
		});

      
	}
}
