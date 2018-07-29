package org.example.test;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.UIManager;

import org.example.test.views.ApplicationFrame;
import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObservableImpl;

public class Application {
	
	private Logger logger = Logger.getLogger(Application.class.getName());
	
	private ApplicationObservable observable;
	
	private ApplicationFrame applicationFrame;
	
	public Application() {
		this.observable = new ApplicationObservableImpl();	
	}
	
	public void run() {

		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
		}

		applicationFrame = new ApplicationFrame();
		applicationFrame.registerIn(observable);

		applicationFrame.setVisible(true);

	}

}
