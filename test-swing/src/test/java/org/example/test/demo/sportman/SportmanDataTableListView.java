package org.example.test.demo.sportman;

import java.awt.BorderLayout;

import org.example.test.demo.sportman.data.Sportman;
import org.example.test.views.components.ApplicationModel;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.events.ApplicationEvent;
import org.example.test.views.components.events.OpenEntityEvent;
import org.example.test.views.components.panels.AppPanel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SportmanDataTableListView  extends AppPanel implements ApplicationModelListener {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = 5574414910291766370L;

	private SportmanDataTableToolBar toolBar;
	
	private SportmanDataTable datatable;

	@Override
	protected void initializateGUI() {
		
		this.toolBar = new SportmanDataTableToolBar();
		
		this.datatable = new SportmanDataTable();
		
		super.setLayout(new BorderLayout());
		super.add(this.toolBar, BorderLayout.NORTH);
		super.add(this.datatable, BorderLayout.CENTER);
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setModel(ApplicationModel model) {
		super.setModel(model);
		this.toolBar.setModel(model);
		this.datatable.setModel(model);
	}
	
	@Override
	protected void registerEventListeners() {
		super.addEventListener(OpenEntityEvent.class, (e) -> openEntityDialog(e));
		// TODO Auto-generated method stub
		
	}

	private void openEntityDialog(ApplicationEvent e) {
		log.trace("New Registry: {}", e);
		// TODO Auto-generated method stub
		this.datatable.addRegister(new Sportman(0L, "", "", "", 0, false));
	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}

}
