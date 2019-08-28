package org.example.test.views.components.panels;

import javax.swing.JLabel;

import org.example.test.views.components.ApplicationModelListener;

public class EmptyContainerPanel extends AppContainerView implements ApplicationModelListener {

	private static final long serialVersionUID = 6744713898858449289L;

	@Override
	protected void initializateGUI() {
		super.add(new JLabel(messagesResources.getString("container.empty.text")));
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
