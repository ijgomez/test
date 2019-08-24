package org.example.test;

import java.awt.Frame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.example.test.views.ApplicationFrame;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

	public static void main(String[] args) {
		try {
			try {
//				 // set the brushed metal look and feel, if desired
//				  System.setProperty("apple.awt.brushMetalLook", "true");
//				  
//				  // use the mac system menu bar
//				  System.setProperty("apple.laf.useScreenMenuBar", "true");
//				  
//				  // set the "About" menu item name
//				  System.setProperty("com.apple.mrj.application.apple.menu.about.name", "WikiStar");
//				  
//				  // use smoother fonts
//				  System.setProperty("apple.awt.textantialiasing", "true");
//
//				  // ref: http://developer.apple.com/releasenotes/Java/Java142RNTiger/1_NewFeatures/chapter_2_section_3.html
//				  System.setProperty("apple.awt.graphics.EnableQ2DX","true");
//				  
//				  // use the system look and feel
//				  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

	            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {
	                    UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (Exception ex) {
	            log.error("Setting look and feel failed:", ex);
	        }


			SwingUtilities.invokeLater(new Runnable() {
				
				public void run() {
					ApplicationFrame frame = new ApplicationFrame(args);
					frame.setVisible(true);
					frame.setExtendedState(Frame.MAXIMIZED_BOTH);
					frame.toFront();
				}
			});

        } catch(Exception e) {
        	log.error("fail to start...", e);
        }

	}

}
