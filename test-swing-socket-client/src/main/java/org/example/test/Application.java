package org.example.test;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.UIManager;

import org.apache.commons.lang.SystemUtils;
import org.example.test.views.ApplicationFrame;
import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObservableImpl;

public class Application {
	
	private Logger logger = Logger.getLogger(Application.class.getName());
	
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
		
	private ApplicationObservable observable;
	
	private ApplicationFrame applicationFrame;
	
	public Application() {
		this.observable = new ApplicationObservableImpl();	
	}
	
	public void run() {

		try {
			if (SystemUtils.IS_OS_MAC_OSX) {
				// take the menu bar off the jframe
				System.setProperty("apple.laf.useScreenMenuBar", "true");

				// set the name of the application menu item
				System.setProperty("com.apple.mrj.application.apple.menu.about.name", resourceBundle.getString("application.title"));

				System.setProperty("com.apple.macos.smallTabs", "true");
				
				// set the look and feel
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} else {
				for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					if ("Nimbus".equals(info.getName())) {
						UIManager.setLookAndFeel(info.getClassName());
						break;
					}
				}
			}
		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
		}

		applicationFrame = new ApplicationFrame();
		applicationFrame.registerIn(observable);
		applicationFrame.setLocationRelativeTo(null);
		applicationFrame.setVisible(true);
		

	}

}
