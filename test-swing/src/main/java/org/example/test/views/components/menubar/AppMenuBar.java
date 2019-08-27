package org.example.test.views.components.menubar;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javax.swing.JMenuBar;

import org.example.test.views.components.ApplicationModel;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.events.ApplicationEvent;
import org.example.test.views.components.helpers.LocaleHelper;

public abstract class AppMenuBar extends JMenuBar implements ApplicationModelListener {

	private static final long serialVersionUID = -5289274357066149266L;

	protected ResourceBundle messagesResources = LocaleHelper.getMessagesResources();
	
	protected ApplicationModel model;

	private Map<Class<?>, Consumer<ApplicationEvent>> handlers = new HashMap<Class<?>, Consumer<ApplicationEvent>>();

	public AppMenuBar() {
		this.initializateGUI();
		this.registerEvents();
	}
	
	protected abstract void initializateGUI();
	
	protected abstract void registerEvents();
	
	@Override
	public void setModel(ApplicationModel model) {
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
}
