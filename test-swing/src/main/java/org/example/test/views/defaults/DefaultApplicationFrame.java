package org.example.test.views.defaults;

import java.io.File;

import org.example.test.views.ApplicationStatus;
import org.example.test.views.ApplicationViewConfiguration;
import org.example.test.views.annotations.ApplicationViewScan;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.dialog.init.ApplicationInitializationDialog;
import org.example.test.views.components.events.OpenFileEvent;
import org.example.test.views.components.events.SaveFileEvent;
import org.example.test.views.components.frames.AppFrame;
import org.example.test.views.components.menubar.AppMenuBar;
import org.example.test.views.components.toolbar.AppToolBar;
import org.example.test.views.factories.ModalDialogFactory;
import org.example.test.views.menu.ApplicationMenuBar;

import lombok.extern.slf4j.Slf4j;

@ApplicationViewScan(packages = {"org.example.test.demo"})
@Slf4j
public class DefaultApplicationFrame extends AppFrame implements ApplicationModelListener {

	private static final long serialVersionUID = 4661793292792888017L;

	private String[] arguments;

	public DefaultApplicationFrame(ApplicationViewConfiguration viewConfiguration, String... args) {
		super(viewConfiguration);
		this.arguments = args;
	}

	protected void handlerInitializateGUI() { }
	
	protected AppMenuBar buildMenuBar() {
		return new ApplicationMenuBar();
	}
	
	protected AppToolBar buildToolBar() {
		return new DefaultApplicationToolBar(getViewConfiguration());
	}
	
	protected ApplicationStatus buildStatusBar() {
		return new ApplicationStatus();
	}
	
	protected ApplicationInitializationDialog buildInitializationAction() {
		return new ApplicationInitializationDialog(this, arguments){

			private static final long serialVersionUID = 3358680407057807752L;

			@Override
			protected void handlerExecuteInitializationApplication() { }

			@Override
			protected void handlerExecuteShutdownApplication() { }
			
		};
	}

	@Override
	protected void handlerRegisterEventListeners() {
		super.register(OpenFileEvent.class, (e) -> openFileDialog());
		super.register(SaveFileEvent.class, (e) -> saveFileDialog());
	}
	
	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}

	private void openFileDialog() {
		File f;
		
		f = ModalDialogFactory.showOpenFileDialog(getParent());
		if (f != null) {
			log.trace("File: {}", f.getAbsolutePath());
		}
		// TODO Auto-generated method stub
		
	}
	
	private void saveFileDialog() {
		File f;
		
		f = ModalDialogFactory.showSaveFileDialog(getParent());
		if (f != null) {
			log.trace("File: {}", f.getAbsolutePath());
		}
		// TODO Auto-generated method stub

	}

}
