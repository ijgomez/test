package org.example.test.views.components.buttons;

import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.events.ApplicationEvent;
import org.example.test.views.components.events.SelectFileEvent;

public class SelectButton extends AppButton implements ApplicationModelListener {

	private static final long serialVersionUID = 9124367834491909447L;

	public SelectButton() {
		super("button.select.text", "button.select.tooltip");
	}
	
	@Override
	protected void handlerInitializateGUI() {
		super.addActionListener((e) -> model.notify(new SelectFileEvent()));
	}
	
	@Override
	public void listener(ApplicationEvent event) { }

	@Override
	public void updateView() { }
}
