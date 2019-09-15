package org.example.test.views.components.toolbar;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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
import org.example.test.views.factories.ResourcesFactory;
import org.example.test.views.resources.TextResources;

/**
 * Class that models the main menu of the application.
 * 
 * @author ijgomez
 *
 */
public abstract class AppMainToolBar extends JToolBar implements ApplicationModelListener {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = 1460066304433101806L;

	protected TextResources textResources = ResourcesFactory.getFactory().text();
	
	protected ApplicationModel model;
	
	private ApplicationViewConfiguration viewConfiguration;
	
	private Map<Class<?>, Consumer<ApplicationEvent>> handlers = new HashMap<Class<?>, Consumer<ApplicationEvent>>();
	
	
	private JButton selectedButton;
	
	public AppMainToolBar(ApplicationViewConfiguration viewConfiguration) {
		this.viewConfiguration = viewConfiguration;
		this.initializateGUI();
		this.registerEventListeners();
	}

	/**
	 * Method that contains the definition of the visual elements of the component.
	 */
	private void initializateGUI() {
		super.setFloatable(false);
		this.getViewConfiguration().ifPresent((vc) -> {
			if (vc != null && vc.getContainerViews() != null) {
				vc.getContainerViews().forEach((c) -> {
					if (c.getClassElement().equals(JButton.class)) {
						AppButton button;
						button = new ChangeViewButton<>(c.getTitleTextKey(), c.getToolTipTextKey(), c.getClassContainer());
						button.setSelected(c.isSelected());
						if (c.isSelected()) {
							selectedButton = button;
						}
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
		this.handlerRegisterEventListeners();
	}
	
	@Override
	public void updateView() {
		selectedButton.requestFocusInWindow();
		this.handlerUpdateView();
	};
	
	protected abstract void handlerUpdateView();
	
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
