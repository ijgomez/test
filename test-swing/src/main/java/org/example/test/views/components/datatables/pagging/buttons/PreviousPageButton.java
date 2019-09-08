package org.example.test.views.components.datatables.pagging.buttons;

import java.awt.event.ActionEvent;

import org.example.test.views.components.buttons.BasicButton;
import org.example.test.views.components.datatables.AppDataTableListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PreviousPageButton extends BasicButton {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = -3125617134162529196L;

	/**
	 * New Instance.
	 */
	public PreviousPageButton() {
		super("datatable.pagging.button.prev.text", "datatable.pagging.button.prev.tool.tip");
	}
	
	@Override
	protected void handlerInitializateGUI() {
		super.addActionListener((e) -> previousPageAction(e));
	}

	/**
	 * Send the event generated by pressing the button to the parent component.
	 * 
	 * @param e Event
	 */
	private void previousPageAction(ActionEvent e) {
		log.trace("Previous page: {}", e);
		if (super.getParent() instanceof AppDataTableListener) {
			((AppDataTableListener) super.getParent()).previousPageAction(e);
		}
	}

}
