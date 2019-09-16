package org.example.test;

import java.io.File;

import org.example.test.views.ApplicationStatus;
import org.example.test.views.ApplicationViewConfiguration;
import org.example.test.views.annotations.ApplicationViewScan;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.dialog.init.ApplicationInitializationDialog;
import org.example.test.views.components.events.OpenFileEvent;
import org.example.test.views.components.events.OpenSettingsDialogEvent;
import org.example.test.views.components.events.SaveFileEvent;
import org.example.test.views.components.frames.AppFrame;
import org.example.test.views.components.menubar.AppMenuBar;
import org.example.test.views.components.toolbar.AppMainToolBar;
import org.example.test.views.factories.DialogFactory;
import org.example.test.views.helpers.SleepHelper;
import org.example.test.views.menu.ApplicationMenuBar;

import lombok.extern.slf4j.Slf4j;

@ApplicationViewScan(
		packages = {"org.example.test.demo"},
		textResources = {"demoMessages"}
	)
@Slf4j
public class DemoApplicationFrame extends AppFrame implements ApplicationModelListener {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = 4661793292792888017L;

	private String[] arguments;

	public DemoApplicationFrame(ApplicationViewConfiguration viewConfiguration, String... args) {
		super(viewConfiguration);
		this.arguments = args;
	}

	protected void handlerInitializateGUI() { }
	
	protected AppMenuBar buildMenuBar() {
		return new ApplicationMenuBar();
	}
	
	protected AppMainToolBar buildToolBar() {
		return new DemoApplicationToolBar(getViewConfiguration());
	}
	
	protected ApplicationStatus buildStatusBar() {
		return new ApplicationStatus();
	}
	
	protected ApplicationInitializationDialog buildInitializationAction() {
		return new ApplicationInitializationDialog(this, arguments){

			/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
			private static final long serialVersionUID = -8783535924054686171L;

			@Override
			protected void handlerExecuteInitializationApplication() {
				// TODO Auto-generated method stub
				SleepHelper.sleep(10000);
			}

			@Override
			protected void handlerExecuteShutdownApplication() {
				// TODO Auto-generated method stub
				
			}
			
		};
	}

	@Override
	protected void handlerRegisterEventListeners() {
		super.register(OpenFileEvent.class, (e) -> openFileDialog());
		super.register(SaveFileEvent.class, (e) -> saveFileDialog());
		super.register(OpenSettingsDialogEvent.class, (e) -> openSettingsDialog());
	}
	
	

	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}

	private void openFileDialog() {
		File f;
		
		f = DialogFactory.showOpenFileDialog(getParent());
		if (f != null) {
			log.trace("File: {}", f.getAbsolutePath());
		}
		// TODO Auto-generated method stub
		
	}
	
	private void saveFileDialog() {
		File f;
		
		f = DialogFactory.showSaveFileDialog(getParent());
		if (f != null) {
			log.trace("File: {}", f.getAbsolutePath());
		}
		// TODO Auto-generated method stub

	}
	
	private void openSettingsDialog() {
		DialogFactory.showSettingsDialog(this);
		// TODO Auto-generated method stub
	}

}
