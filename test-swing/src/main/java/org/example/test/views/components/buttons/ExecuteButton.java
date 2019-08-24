package org.example.test.views.components.buttons;

import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.events.ApplicationEvent;
import org.example.test.views.events.ExecuteEvent;

public class ExecuteButton extends AppButton implements ApplicationModelListener {

	private static final long serialVersionUID = 9124367834491909447L;

	public ExecuteButton() {
		super("button.execute.text", "button.execute.tooltip");
	}
	
	@Override
	protected void handlerInitializateGUI() {
		super.addActionListener((e) -> model.notify(new ExecuteEvent()));
	}
	
	@Override
	public void listener(ApplicationEvent event) { }

}
