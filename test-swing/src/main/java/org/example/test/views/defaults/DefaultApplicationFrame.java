package org.example.test.views.defaults;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import org.example.test.views.ApplicationInitializationDialog;
import org.example.test.views.ApplicationStatus;
import org.example.test.views.ApplicationViewConfiguration;
import org.example.test.views.annotations.ApplicationViewScan;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.events.OpenFileEvent;
import org.example.test.views.components.events.SaveFileEvent;
import org.example.test.views.components.frames.AppFrame;
import org.example.test.views.components.menubar.AppMenuBar;
import org.example.test.views.components.toolbar.AppToolBar;
import org.example.test.views.menu.ApplicationMenuBar;

@ApplicationViewScan(packages = {"org.example.test.demo"})
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
		return new ApplicationInitializationDialog(this, arguments);
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
		JFileChooser jfc;
		
		jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//		jfc.setDialogTitle("Choose a directory to save your file: ");
//		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		if (jfc.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION) {
			System.out.println(jfc.getSelectedFile().getAbsolutePath());
		}
		// TODO Auto-generated method stub
		
	}
	
	private void saveFileDialog() {
		JFileChooser jfc;
		
		jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//		jfc.setDialogTitle("Choose a directory to save your file: ");
//		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		if (jfc.showSaveDialog(getParent()) == JFileChooser.APPROVE_OPTION) {
			System.out.println(jfc.getSelectedFile().getAbsolutePath());
		}
		// TODO Auto-generated method stub

	}

}
