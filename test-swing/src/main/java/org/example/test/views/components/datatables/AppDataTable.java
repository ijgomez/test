package org.example.test.views.components.datatables;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.panels.AppPanel;

public abstract class AppDataTable extends AppPanel implements ApplicationModelListener {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = 6061707010961045115L;
	
	private JTable table;
	
	private DefaultTableModel tableModel;
	
	private JPanel controlPanel;

	@Override
	protected void initializateGUI() {
		
		this.tableModel = new DefaultTableModel();
		
		this.table = new JTable();
		this.table.setModel(tableModel);
		
		
		this.controlPanel = new JPanel();
		
		super.setLayout(new BorderLayout());
		super.add(table, BorderLayout.CENTER);
		super.add(controlPanel, BorderLayout.SOUTH);
		
		// TODO Auto-generated method stub

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
