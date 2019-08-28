package org.example.test.views.components.buttons;

import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.events.ApplicationEvent;
import org.example.test.views.components.events.ChangeViewEvent;

public class ChangeViewButton<E> extends AppButton implements ApplicationModelListener {

	private static final long serialVersionUID = 7965182179055476500L;
	
	private Class<E> classView;

	public ChangeViewButton(String titleTextKey, String toolTipTextKey, Class<E> classView) {
		super(titleTextKey, toolTipTextKey);
		this.classView = classView;
	}
	
	public Class<E> getClassView() {
		return classView;
	}
	
	@Override
	protected void handlerInitializateGUI() {
		super.addActionListener((e) -> model.notify(new ChangeViewEvent(classView)));
	}
	
	@Override
	public void listener(ApplicationEvent event) { }
	
	@Override
	public void updateView() { }

}
