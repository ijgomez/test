package org.example.test.views.mock;

import javax.swing.JLabel;

import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.panels.AppPanel;

public class MockContainerPanel extends AppPanel implements ApplicationModelListener {

	private static final long serialVersionUID = 6744713898858449289L;

	@Override
	protected void initializateGUI() {
		super.add(new JLabel("Mock Container"));
	}
	
	@Override
	protected void registerEvents() {
		// TODO Auto-generated method stub
		
	}

}
