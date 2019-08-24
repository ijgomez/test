package org.example.test.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.example.test.domain.Mock;
import org.example.test.views.components.ApplicationConstants;
import org.example.test.views.components.ApplicationModel;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.frames.AppFrame;
import org.example.test.views.events.ChangeViewEvent;
import org.example.test.views.events.CloseApplicationEvent;
import org.example.test.views.factories.ContainerViewFactory;
import org.example.test.views.factories.ModalDialogFactory;
import org.example.test.views.menu.ApplicationMenuBar;
import org.example.test.views.toolbar.ApplicationToolBar;

public class ApplicationFrame extends AppFrame implements ApplicationModelListener, ApplicationConstants {

	private static final long serialVersionUID = 4661793292792888017L;

	private String[] arguments;

	private ApplicationInitializationDialog initializationDialog;
	
	private ApplicationMenuBar menuBar;
	
	private ApplicationToolBar toolBar;
	
	private JComponent container;
	
	private ApplicationStatus status;
	
	public ApplicationFrame(String... args) {
		super();
		this.arguments = args;
	}

	protected void initializateGUI() {
		JScrollPane scrollPane;
		
		this.menuBar = new ApplicationMenuBar();
		
		this.toolBar = new ApplicationToolBar();
		
		this.container = (JComponent) ContainerViewFactory.getInstance().getContainerView(Mock.class);
		
		this.status = new ApplicationStatus();
		
		scrollPane = new JScrollPane();
		scrollPane.getViewport().add(this.container);
		
		super.setTitle(messagesResources.getString("application.title"));
		super.setJMenuBar(this.menuBar);
		super.setPreferredSize(MINIMUM_SIZE);
		super.setMinimumSize(MINIMUM_SIZE);
		super.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));

		super.getRootPane().setPreferredSize(MINIMUM_SIZE);
		super.getRootPane().setMinimumSize(MINIMUM_SIZE);		
		super.getRootPane().setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		
		super.setLayout(new BorderLayout());
		
		super.add(toolBar, BorderLayout.NORTH);
		super.add(scrollPane, BorderLayout.CENTER);
		super.add(status, BorderLayout.SOUTH);
		
		// TODO Auto-generated method stub
		
		super.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				initializationAction();
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				confirmExitAction();
			}
		});
	}
	
	private void initializationAction() {
		initializationDialog = new ApplicationInitializationDialog(this, arguments);
		initializationDialog.setVisible(true);
		initializationDialog.toFront();
	}

	private void confirmExitAction() {
		if (ModalDialogFactory.showConfirmExitDialog(this)) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
		} else {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}
	
	@Override
	protected void registerEvents() {
		super.register(CloseApplicationEvent.class, (e) -> confirmExitAction());
		super.register(ChangeViewEvent.class, (e) -> changeView(((ChangeViewEvent) e).getClassEntity()));
	}
	
	@Override
	public void setModel(ApplicationModel model) {
		super.setModel(model);
		this.menuBar.setModel(model);
		this.toolBar.setModel(model);
		((ApplicationModelListener) this.container).setModel(model);
		this.status.setModel(model);
	}

	private void changeView(Class<?> classEntity) {
		// TODO Auto-generated method stub
		
	}

}
