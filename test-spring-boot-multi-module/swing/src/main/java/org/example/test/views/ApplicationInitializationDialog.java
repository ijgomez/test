package org.example.test.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.management.ManagementFactory;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.apache.commons.lang.SystemUtils;
import org.example.test.ApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationInitializationDialog extends JDialog {

	private static final long serialVersionUID = -3495759555845880204L;
	
	private String[] arguments;

	public ApplicationInitializationDialog(JFrame owner, String... args) {
		super(owner);
		this.arguments = args;
		this.initializeGUI();
	}

	private void initializeGUI() {
		super.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		super.setSize(442, 530);
		super.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		super.setLocationRelativeTo(super.getParent());
		super.setResizable(false);
		super.setUndecorated(true);
		super.setModal(true);
		super.setLayout(new BorderLayout());
		super.add(this.getInitialDialogPanel(), BorderLayout.CENTER);
		super.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				log.debug("Start initialization application process...");
				new Thread(executeInitializationApplication(), "initThread").start();
			}
			
		});
	}

	private Component getInitialDialogPanel() {
		JPanel panel;
		
		panel = new JPanel();
		// TODO Auto-generated method stub
		return panel;
	}
	
	protected Runnable executeInitializationApplication() {
		return () -> {
			
			// Show environment settings.
			log.info("Operative System: {} ({}) {}", SystemUtils.OS_NAME, SystemUtils.OS_ARCH, SystemUtils.OS_VERSION); 
			log.info("Java Version: {}", SystemUtils.JAVA_RUNTIME_VERSION); 
			log.info("Id. Application: {}", ManagementFactory.getRuntimeMXBean().getName()); 
			log.info("Locale: {}", Locale.getDefault());
			log.info("Time Zone: {} ({})", TimeZone.getDefault().getDisplayName(), TimeZone.getDefault().getID());
		
			log.info("User Name: {}", SystemUtils.USER_NAME); 
			log.info("User Time Zone: {}", SystemUtils.USER_TIMEZONE); 
			log.info("User Language: {}", SystemUtils.USER_LANGUAGE); 
			log.info("User Country: {}", SystemUtils.USER_COUNTRY); 
			log.info("User Dir: {}", SystemUtils.USER_DIR); 
			log.info("User Home: {}", SystemUtils.USER_HOME);
			
			// Initialization Spring Context.
			ApplicationContext.initialize(arguments);
			
			// TODO Initialization Application Model.
			log.info(ApplicationContext.getIndexService().getRepository());
			
			// TODO Initialization Application Listeners.
			
			log.info("...application loaded.");

			getOwner().repaint();
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		};
	}
	
}
