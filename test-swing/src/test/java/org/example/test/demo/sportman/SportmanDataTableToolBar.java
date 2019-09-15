package org.example.test.demo.sportman;

import javax.swing.JToolBar;

import org.example.test.demo.sportman.data.Sportman;
import org.example.test.views.components.ApplicationModel;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.datatables.buttons.AddButton;
import org.example.test.views.components.datatables.buttons.DeleteButton;
import org.example.test.views.components.datatables.buttons.EditButton;
import org.example.test.views.components.datatables.buttons.ExportButton;
import org.example.test.views.components.datatables.buttons.ReloadDataButton;
import org.example.test.views.components.events.ApplicationEvent;

public class SportmanDataTableToolBar extends JToolBar implements ApplicationModelListener {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = -6120636081870785266L;
	
	protected ApplicationModel model;

	private AddButton<Sportman> addButton;
	
	private EditButton<Sportman> editButton;
	
	private DeleteButton<Sportman> deleteButton;
	
	private ReloadDataButton reloadDataButton;
	
	private ExportButton exportButton;
	
	/**
	 * New Instance
	 */
	public SportmanDataTableToolBar() {
		this.initializateGUI();
	}
	
	/**
	 * Method that contains the definition of the visual elements of the component.
	 */
	private void initializateGUI() {
		this.addButton = new AddButton<>(Sportman.class);
		
		this.editButton = new EditButton<>(Sportman.class);
		this.editButton.setEnabled(false);
		
		this.deleteButton = new DeleteButton<>(Sportman.class);
		this.deleteButton.setEnabled(false);
		
		this.reloadDataButton = new ReloadDataButton();
		
		this.exportButton = new ExportButton();
		
		super.add(this.addButton);
		super.add(this.editButton);
		super.add(this.deleteButton);
		super.add(new JToolBar.Separator());
		super.add(this.reloadDataButton);
		super.add(this.exportButton);
	}

	@Override
	public void setModel(ApplicationModel model) {
		if (model != null) {
			this.model = model;
			this.model.register(this);
			this.updateView();
		} else {
			this.model.unregister(this);
			this.model = model;
		}
		this.addButton.setModel(model);
		this.editButton.setModel(model);
		this.deleteButton.setModel(model);
		this.reloadDataButton.setModel(model);
		this.exportButton.setModel(model);
	}
	
	@Override
	public void updateView() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listener(ApplicationEvent event) {
		// TODO Auto-generated method stub
		
	}

	

}
