package org.example.test.views.components.datatables;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
		
		String[] columnNames = createColumnNames();

		Object[][] data = createDummyData();
		
		this.tableModel = new DefaultTableModel();
//		this.tableModel.setColumnIdentifiers(columnNames);
//		this.tableModel.setColumnIdentifiers(new String[] {"COLUMN_1", "COLUMN_2", "COLUMN_3"});
		this.tableModel.setDataVector(data, columnNames);
		
		this.table = new JTable(this.tableModel);
//		this.table.setModel(tableModel);
//		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
		
		this.controlPanel = new JPanel();
		this.controlPanel.add(new JLabel("control"));
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		
		super.setLayout(new BorderLayout());
		super.add(scrollPane, BorderLayout.CENTER);
		super.add(controlPanel, BorderLayout.SOUTH);
		
		// TODO Auto-generated method stub

	}
	
	private String[] createColumnNames() {
		return new String[]{
				"First Name",
				"Last Name",
				"Sport",
				"# of Years",
            	"Vegetarian"
			};
	}
	
	private Object[][] createDummyData() {
		return new Object[][] {
			{"Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false)},
			{"John", "Doe", "Rowing", new Integer(3), new Boolean(true)},
			{"Sue", "Black", "Knitting", new Integer(2), new Boolean(false)},
			{"Jane", "White", "Speed reading", new Integer(20), new Boolean(true)},
			{"Joe", "Brown", "Pool", new Integer(10), new Boolean(false)},
			{"Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false)},
			{"John", "Doe", "Rowing", new Integer(3), new Boolean(true)},
			{"Sue", "Black", "Knitting", new Integer(2), new Boolean(false)},
			{"Jane", "White", "Speed reading", new Integer(20), new Boolean(true)},
			{"Joe", "Brown", "Pool", new Integer(10), new Boolean(false)},
			{"Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false)},
			{"John", "Doe", "Rowing", new Integer(3), new Boolean(true)},
			{"Sue", "Black", "Knitting", new Integer(2), new Boolean(false)},
			{"Jane", "White", "Speed reading", new Integer(20), new Boolean(true)},
			{"Joe", "Brown", "Pool", new Integer(10), new Boolean(false)},
			{"Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false)},
			{"John", "Doe", "Rowing", new Integer(3), new Boolean(true)},
			{"Sue", "Black", "Knitting", new Integer(2), new Boolean(false)},
			{"Jane", "White", "Speed reading", new Integer(20), new Boolean(true)},
			{"Joe", "Brown", "Pool", new Integer(10), new Boolean(false)},
			{"Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false)},
			{"John", "Doe", "Rowing", new Integer(3), new Boolean(true)},
			{"Sue", "Black", "Knitting", new Integer(2), new Boolean(false)},
			{"Jane", "White", "Speed reading", new Integer(20), new Boolean(true)},
			{"Joe", "Brown", "Pool", new Integer(10), new Boolean(false)},
			{"Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false)},
			{"John", "Doe", "Rowing", new Integer(3), new Boolean(true)},
			{"Sue", "Black", "Knitting", new Integer(2), new Boolean(false)},
			{"Jane", "White", "Speed reading", new Integer(20), new Boolean(true)},
			{"Joe", "Brown", "Pool", new Integer(10), new Boolean(false)},
			{"Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false)},
			{"John", "Doe", "Rowing", new Integer(3), new Boolean(true)},
			{"Sue", "Black", "Knitting", new Integer(2), new Boolean(false)},
			{"Jane", "White", "Speed reading", new Integer(20), new Boolean(true)},
			{"Joe", "Brown", "Pool", new Integer(10), new Boolean(false)}
		};
	}

	@Override
	protected void registerEventListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub
//		this.tableModel.addRow(new Object[]{});
	}

}
