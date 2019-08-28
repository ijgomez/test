package org.example.test.views.components.toolbar;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JToolBar;

import org.example.test.views.ApplicationViewConfiguration;
import org.example.test.views.components.ApplicationModel;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.buttons.AppButton;
import org.example.test.views.components.buttons.ChangeViewButton;
import org.example.test.views.components.events.ApplicationEvent;
import org.example.test.views.components.events.ChangeViewEvent;
import org.example.test.views.components.helpers.LocaleHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	private void initializateGUI() {
		this.getViewConfiguration().ifPresent((vc) -> {
			if (vc != null && vc.getContainerViews() != null) {
				vc.getContainerViews().forEach((c) -> {
					if (c.getClassElement().equals(JButton.class)) {
						AppButton button;
						button = new ChangeViewButton<>(c.getTitleTextKey(), c.getToolTipTextKey(), c.getClassContainer());
						button.setSelected(c.isSelected());
						super.add(button);		
					} else if (c.getClassElement().equals(Separator.class)) {
						super.add(new Separator());
					}
							
				});
			}
		});
		this.handlerInitializateGUI();
	}
	
	protected abstract void handlerInitializateGUI();

	protected abstract void handlerRegisterEventListeners();
	
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
	
	protected void registerEventListeners() {
		addEventListener(ChangeViewEvent.class, (e) -> selectedButtonAction(((ChangeViewEvent) e)));
		// TODO Auto-generated method stub
		this.handlerRegisterEventListeners();
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
	
	private void selectedButtonAction(ChangeViewEvent e) {
		Stream.of(super.getComponents()).forEach((c) -> {
			if (c instanceof ChangeViewButton) {
				ChangeViewButton<?> changeViewButton = (ChangeViewButton<?>) c;
				if (changeViewButton.getClassView().equals(e.getClassEntity())) {
					changeViewButton.setEnabled(true);
					log.trace("enable {}", changeViewButton);
				}
			}
		});
	}
}
