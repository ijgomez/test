package org.example.test.views.components.dialog.init;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.apache.commons.lang3.SystemUtils;
import org.example.test.views.components.ApplicationModel;
import org.example.test.views.components.ApplicationModelImpl;
import org.example.test.views.components.ApplicationModelListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ApplicationInitializationDialog extends JDialog {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = -3495759555845880204L;
	
	private String[] arguments;
	
	private ApplicationModel model;
	
	private ApplicationInitializationDialogPanel initializationDialogPanel;

	public ApplicationInitializationDialog(JFrame owner, String... args) {
		super(owner);
		this.arguments = args;
		this.initializeGUI();
	}

	private void initializeGUI() {
		this.initializationDialogPanel = this.getInitialDialogPanel();

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

	private ApplicationInitializationDialogPanel getInitialDialogPanel() {
		return new ApplicationInitializationDialogPanel();
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
		this.initializationDialogPanel.setStatusCompleteTask("model");
		
		// Initialization Application Listeners.
		((ApplicationModelListener) getParent()).setModel(model);
		this.initializationDialogPanel.setStatusCompleteTask("listeners");
		
		// TODO Initialization Logical Business.
		handlerExecuteInitializationApplication();
		this.initializationDialogPanel.setStatusCompleteTask("business");
		
		// Configuration Shutdown Hook.
		Runtime.getRuntime().addShutdownHook(new Thread(() -> executeShutdownApplication()));
		this.initializationDialogPanel.setStatusCompleteTask("hook");
		
		log.info("...application loaded.");

		getOwner().repaint();
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	protected abstract void handlerExecuteInitializationApplication();

	private void executeShutdownApplication() {
		log.trace("Executing Shutdown Hook...");
		this.handlerExecuteShutdownApplication();
	}
	
	protected abstract void handlerExecuteShutdownApplication();

	
}
