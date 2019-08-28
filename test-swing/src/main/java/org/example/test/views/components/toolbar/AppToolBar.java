package org.example.test.views.components.toolbar;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.swing.JToolBar;

import org.example.test.views.ApplicationViewConfiguration;
import org.example.test.views.components.ApplicationModel;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.events.ApplicationEvent;
import org.example.test.views.components.helpers.LocaleHelper;

public abstract class AppToolBar extends JToolBar implements ApplicationModelListener {

	private static final long serialVersionUID = 1460066304433101806L;

	protected ResourceBundle messagesResources = LocaleHelper.getMessagesResources();
	
	protected ApplicationModel model;
	
	private ApplicationViewConfiguration viewConfiguration;
	
	private Map<Class<?>, Consumer<ApplicationEvent>> handlers = new HashMap<Class<?>, Consumer<ApplicationEvent>>();
	
	public AppToolBar(ApplicationViewConfiguration viewConfiguration) {
		this.viewConfiguration = viewConfiguration;
		this.initializateGUI();
		this.registerEventListeners();
	}
	
	protected abstract void initializateGUI();

	protected abstract void registerEventListeners();
	
	@Override
	public void setModel(ApplicationModel model) {
		if (model != null) {
			this.model = model;
			this.model.register(this);
			this.updateView();
		} else {
			this.model.unregister(this);
			this.model = model;
		}
		Stream.of(super.getComponents()).forEach((c) -> {
			if (c instanceof ApplicationModelListener) {
				((ApplicationModelListener) c).setModel(model);
			}
		});
	}
	
	@Override
	public abstract void updateView();
	
	@Override
	public void listener(ApplicationEvent event) { 
		if (this.handlers.containsKey(event.getClass())) {
			this.handlers.get(event.getClass()).accept(event);
		}
	}
	
	protected void addEventListener(Class<?> key, Consumer<ApplicationEvent> value) {
		this.handlers.put(key, value);
	}
	
	public Optional<ApplicationViewConfiguration> getViewConfiguration() {
		return Optional.ofNullable(viewConfiguration);
	}
}
