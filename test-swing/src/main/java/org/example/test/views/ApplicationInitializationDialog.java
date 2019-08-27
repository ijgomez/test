package org.example.test.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.apache.commons.lang3.SystemUtils;
import org.example.test.views.components.ApplicationModel;
import org.example.test.views.components.ApplicationModelImpl;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.helpers.SleepHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationInitializationDialog extends JDialog {

	private static final long serialVersionUID = -3495759555845880204L;
	
	private String[] arguments;
	
	private ApplicationModel model;

	public ApplicationInitializationDialog(JFrame owner, String... args) {
		super(owner);
		this.arguments = args;
		this.initializeGUI();
	}

	private void initializeGUI() {
		super.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		super.setSize(442, 530);
		super.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		//super.setLocationRelativeTo(super.getParent());
		super.setLocationRelativeTo(null);
		super.setResizable(false);
		super.setUndecorated(true);
		super.setModal(true);
		super.setLayout(new BorderLayout());
		super.add(this.getInitialDialogPanel(), BorderLayout.CENTER);
		super.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				log.debug("Start initialization application process...");
				new Thread(() -> executeInitializationApplication(), "initThread").start();
			}
			
		});
	}

	private Component getInitialDialogPanel() {
		JPanel panel;
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		// TODO Auto-generated method stub
		return panel;
	}
	
	private void executeInitializationApplication() {
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
		
		// Initialization Application.
		log.info("Arguments: {}", Arrays.toString(arguments));
		
		// Initialization Application Model.
		model = new ApplicationModelImpl();
		SleepHelper.sleep(1000L);
		
		// Initialization Application Listeners.
		((ApplicationModelListener) getParent()).setModel(model);
		SleepHelper.sleep(1000L);
		
		// TOOD Initialization Logical Business.
		
		// Configuration Shutdown Hook.
		Runtime.getRuntime().addShutdownHook(new Thread(() -> executeShutdownHook()));
		SleepHelper.sleep(1000L);
		
		log.info("...application loaded.");

		getOwner().repaint();
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	private void executeShutdownHook() {
		log.trace("Executing Shutdown Hook...");
		// TODO Configuration Shutdown Hook.
	}
	
}
