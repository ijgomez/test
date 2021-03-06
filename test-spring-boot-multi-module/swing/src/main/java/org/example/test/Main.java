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
