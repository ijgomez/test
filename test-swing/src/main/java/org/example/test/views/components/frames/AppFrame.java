package org.example.test.views.components.frames;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javax.swing.JFrame;

import org.example.test.views.components.ApplicationModel;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.events.ApplicationEvent;
import org.example.test.views.components.events.ChangeViewEvent;
import org.example.test.views.components.events.CloseApplicationEvent;
import org.example.test.views.components.helpers.LocaleHelper;
import org.example.test.views.factories.ModalDialogFactory;

public abstract class AppFrame extends JFrame implements ApplicationModelListener {

	private static final long serialVersionUID = 5279263641839891108L;

	protected ResourceBundle messagesResources = LocaleHelper.getMessagesResources();
	
	protected ApplicationModel model;
	
	private Map<Class<?>, Consumer<ApplicationEvent>> handlers = new HashMap<Class<?>, Consumer<ApplicationEvent>>();
	
	protected abstract void initializateGUI();
	
	protected abstract void handlerRegisterEvents();
	
	public AppFrame() {
		this.initializateGUI();
		this.registerEvents();
	}
	
	protected void registerEvents() {
		register(CloseApplicationEvent.class, (e) -> confirmExitAction());
		register(ChangeViewEvent.class, (e) -> changeView(((ChangeViewEvent) e).getClassEntity()));
		this.handlerRegisterEvents();
	}
	
	protected abstract void changeView(Class<?> classEntity);

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
	
	protected void confirmExitAction() {
		if (ModalDialogFactory.showConfirmExitDialog(this)) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
		} else {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}
	
}
