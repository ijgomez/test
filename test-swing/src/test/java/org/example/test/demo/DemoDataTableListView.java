package org.example.test.demo;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JToolBar;

import org.example.test.views.components.ApplicationModel;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.panels.AppPanel;

public class DemoDataTableListView  extends AppPanel implements ApplicationModelListener {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = 5574414910291766370L;

	private DemoDataTable datatable;
	
	@Override
	protected void initializateGUI() {
		JToolBar toolbar;
		
		toolbar = new JToolBar();
		toolbar.add(new JButton("Reflesh"));
		toolbar.add(new JToolBar.Separator());
		toolbar.add(new JButton("Search"));
		toolbar.add(new JButton("Add"));
		
		this.datatable = new DemoDataTable();
		
		super.setLayout(new BorderLayout());
		super.add(toolbar, BorderLayout.NORTH);
		super.add(this.datatable, BorderLayout.CENTER);
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setModel(ApplicationModel model) {
		super.setModel(model);
		this.datatable.setModel(model);
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
