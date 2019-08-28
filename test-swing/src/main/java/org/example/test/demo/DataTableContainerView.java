package org.example.test.demo;

import javax.swing.JLabel;

import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.panels.AppPanel;

public class DataTableContainerView extends AppPanel implements ApplicationModelListener {

	private static final long serialVersionUID = 8543052142754893011L;

	@Override
	protected void initializateGUI() {
		// TODO Auto-generated method stub
		super.add(new JLabel("Datatable container"));
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
