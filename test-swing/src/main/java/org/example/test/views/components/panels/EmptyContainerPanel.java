package org.example.test.views.components.panels;

import javax.swing.JLabel;

import org.example.test.views.components.ApplicationModelListener;

public class EmptyContainerPanel extends AppContainerView implements ApplicationModelListener {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = 6744713898858449289L;

	@Override
	protected void initializateGUI() {
		super.add(new JLabel(textResources.getString("container.empty.text")));
	}
	
	@Override
	protected void registerEventListeners() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}

}
