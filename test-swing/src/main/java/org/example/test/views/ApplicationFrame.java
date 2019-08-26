package org.example.test.views;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.events.OpenFileEvent;
import org.example.test.views.components.events.SaveFileEvent;
import org.example.test.views.components.frames.AppFrame;
import org.example.test.views.components.menubar.AppMenuBar;
import org.example.test.views.components.toolbar.AppToolBar;
import org.example.test.views.menu.ApplicationMenuBar;
import org.example.test.views.toolbar.ApplicationToolBar;

public class ApplicationFrame extends AppFrame implements ApplicationModelListener {

	private static final long serialVersionUID = 4661793292792888017L;

	private String[] arguments;

	public ApplicationFrame(String... args) {
		super();
		this.arguments = args;
	}

	protected void handlerInitializateGUI() { }
	
	protected AppMenuBar buildMenuBar() {
		return new ApplicationMenuBar();
	}
	
	protected AppToolBar buildToolBar() {
		return new ApplicationToolBar();
	}
	
	protected ApplicationStatus buildStatusBar() {
		return new ApplicationStatus();
	}
	
	protected ApplicationInitializationDialog buildInitializationAction() {
		return new ApplicationInitializationDialog(this, arguments);
	}

	@Override
	protected void handlerRegisterEvents() {
		super.register(OpenFileEvent.class, (e) -> openFileDialog());
		super.register(SaveFileEvent.class, (e) -> saveFileDialog());
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
