package org.example.test.views.components.datatables.buttons;

import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.buttons.AppButton;
import org.example.test.views.components.events.ApplicationEvent;
import org.example.test.views.components.events.ExportDataEvent;

public class ExportButton extends AppButton implements ApplicationModelListener {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = -2960155241027241105L;
	
	public ExportButton() {
		super("button.export.text", "button.export.tool.tip");
	}
	
	@Override
	protected void handlerInitializateGUI() {
		super.addActionListener((e) -> model.notify(new ExportDataEvent()));
	}
	
	@Override
	public void listener(ApplicationEvent event) { }

	@Override
	public void updateView() { }

	

}
