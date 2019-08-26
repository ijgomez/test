package org.example.test.views.components.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.example.test.views.ApplicationInitializationDialog;
import org.example.test.views.ApplicationStatus;
import org.example.test.views.ApplicationViewConfiguration;
import org.example.test.views.components.ApplicationConstants;
import org.example.test.views.components.ApplicationModel;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.events.ApplicationEvent;
import org.example.test.views.components.events.ChangeViewEvent;
import org.example.test.views.components.events.CloseApplicationEvent;
import org.example.test.views.components.helpers.LocaleHelper;
import org.example.test.views.components.menubar.AppMenuBar;
import org.example.test.views.components.toolbar.AppToolBar;
import org.example.test.views.factories.ContainerViewFactory;
import org.example.test.views.factories.ModalDialogFactory;

public abstract class AppFrame extends JFrame implements ApplicationModelListener, ApplicationConstants {

	private static final long serialVersionUID = 5279263641839891108L;

	protected ResourceBundle messagesResources = LocaleHelper.getMessagesResources();
	
	protected ApplicationModel model;
	
	private ApplicationViewConfiguration viewConfiguration;
	
	private Map<Class<?>, Consumer<ApplicationEvent>> handlers = new HashMap<Class<?>, Consumer<ApplicationEvent>>();
	
	private ApplicationInitializationDialog initializationDialog;
	
	private AppMenuBar menuBar;
	
	private AppToolBar toolBar;
	
	private JComponent container;
	
	private ApplicationStatus status;
	
	/**
	 * New Instance
	 */
	public AppFrame(ApplicationViewConfiguration viewConfiguration) {
		this.viewConfiguration = viewConfiguration;
		this.initializateGUI();
		this.registerEvents();
	}

	private void initializateGUI() {
		JScrollPane scrollPane;
		
		this.menuBar = buildMenuBar();
		
		this.toolBar = buildToolBar();
		
		this.container = (JComponent) ContainerViewFactory.getInstance().getContainerView(null);
		
		this.status = buildStatusBar();
		
		scrollPane = new JScrollPane();
		scrollPane.getViewport().add(this.container);
		
		super.setTitle(messagesResources.getString("application.title"));
		if (this.menuBar != null) {
			super.setJMenuBar(this.menuBar);
		}
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
				initializationDialog = buildInitializationAction();
				initializationDialog.setVisible(true);
				initializationDialog.toFront();
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				confirmExitAction();
			}
		});
		this.handlerInitializateGUI();
	}
	
	protected abstract void handlerInitializateGUI();
	
	protected abstract ApplicationInitializationDialog buildInitializationAction();

	protected abstract ApplicationStatus buildStatusBar();

	protected abstract AppToolBar buildToolBar();

	protected abstract AppMenuBar buildMenuBar();

	@Override
	public void setModel(ApplicationModel model) {
		assert (model != null) : "parameter 'model' cannot be null!";
		
		if (model != null) {
			this.model = model;
			this.model.register(this);
		} else {
			this.model.unregister(this);
			this.model = model;
		}
		this.menuBar.setModel(model);
		this.toolBar.setModel(model);
		((ApplicationModelListener) this.container).setModel(model);
		this.status.setModel(model);
	}
	
	@Override
	public void listener(ApplicationEvent event) { 
		if (this.handlers.containsKey(event.getClass())) {
			this.handlers.get(event.getClass()).accept(event);
		}
	}
	
	protected void register(Class<?> key, Consumer<ApplicationEvent> value) {
		this.handlers.put(key, value);
	}
	
	private void registerEvents() {
		register(CloseApplicationEvent.class, (e) -> confirmExitAction());
		register(ChangeViewEvent.class, (e) -> changeView(((ChangeViewEvent) e).getClassEntity()));
		this.handlerRegisterEvents();
	}
	
	protected abstract void handlerRegisterEvents();

	private void confirmExitAction() {
		if (ModalDialogFactory.showConfirmExitDialog(this)) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
		} else {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}
	
	private void changeView(Class<?> classEntity) {
		// TODO Auto-generated method stub
		
	}
	
	public ApplicationViewConfiguration getViewConfiguration() {
		return viewConfiguration;
	}
}
