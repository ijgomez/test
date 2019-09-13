package org.example.test.demo;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JToolBar;

import org.example.test.demo.data.Sportman;
import org.example.test.views.components.ApplicationModel;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.datatables.buttons.AddButton;
import org.example.test.views.components.datatables.buttons.ReloadDataButton;
import org.example.test.views.components.events.ApplicationEvent;
import org.example.test.views.components.events.OpenEntityEvent;
import org.example.test.views.components.panels.AppPanel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SportmanTableListView  extends AppPanel implements ApplicationModelListener {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = 5574414910291766370L;

	private SportmanDataTable datatable;
	
	private ReloadDataButton reloadDataButton;
	
	private AddButton<Sportman> addButton;
	
	@Override
	protected void initializateGUI() {
		
		JToolBar toolbar;
		
		reloadDataButton = new ReloadDataButton();
		addButton = new AddButton<>(Sportman.class);
		
		toolbar = new JToolBar();
		
		toolbar.add(addButton);
		
		toolbar.add(new JToolBar.Separator());
		toolbar.add(reloadDataButton);
		toolbar.add(new JButton("Export"));
		
		this.datatable = new SportmanDataTable();
		
		super.setLayout(new BorderLayout());
		super.add(toolbar, BorderLayout.NORTH);
		super.add(this.datatable, BorderLayout.CENTER);
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setModel(ApplicationModel model) {
		super.setModel(model);
		this.reloadDataButton.setModel(model);
		this.addButton.setModel(model);
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
