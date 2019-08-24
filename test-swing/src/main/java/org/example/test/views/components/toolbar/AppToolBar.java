package org.example.test.views.components.toolbar;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javax.swing.JToolBar;

import org.example.test.views.components.ApplicationModel;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.events.ApplicationEvent;
import org.example.test.views.helper.LocaleHelper;

public abstract class AppToolBar extends JToolBar implements ApplicationModelListener {

	private static final long serialVersionUID = 1460066304433101806L;

	protected ResourceBundle messagesResources = LocaleHelper.getMessagesResources();
	
	protected ApplicationModel model;
	
	private Map<Class<?>, Consumer<ApplicationEvent>> handlers = new HashMap<Class<?>, Consumer<ApplicationEvent>>();
	
	public AppToolBar() {
		this.initializateGUI();
		this.registerEvents();
	}
	
	protected abstract void initializateGUI();

	protected abstract void registerEvents();
	
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
}
