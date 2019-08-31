package org.example.test.demo;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import org.example.test.views.annotations.ApplicationContainerViewConfig;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.panels.AppPanel;

@ApplicationContainerViewConfig(titleTextKey = "toolbar.button.container.datatable.text", toolTipTextKey = "toolbar.button.container.datatable.tool.tip", order = 2, selected = true)
public class DataTableContainerView extends AppPanel implements ApplicationModelListener {

	private static final long serialVersionUID = 8543052142754893011L;

	@Override
	protected void initializateGUI() {
		// TODO Auto-generated method stub
		super.setLayout(new BorderLayout());
		super.add(new JLabel("Datatable container"), BorderLayout.NORTH);
		super.add(new DemoDataTable(), BorderLayout.CENTER);
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
