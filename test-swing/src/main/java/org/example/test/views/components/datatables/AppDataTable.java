package org.example.test.views.components.datatables;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.datatables.pagging.PaginationPanel;
import org.example.test.views.components.events.ReloadDataEvent;
import org.example.test.views.components.panels.AppPanel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AppDataTable extends AppPanel implements ApplicationModelListener {

	
	
	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = 6061707010961045115L;
	
	private JTable table;
	
	private DefaultTableModel tableModel;
	
	private PaginationPanel paginationPanel;

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
		
        JScrollPane scrollPane = new JScrollPane(table);
        
        this.paginationPanel = new PaginationPanel();
		
		
		super.setLayout(new BorderLayout());
		super.add(scrollPane, BorderLayout.CENTER);
		super.add(this.paginationPanel, BorderLayout.SOUTH);
		
		// TODO Auto-generated method stub

		this.handlerInitializateGUI();
	}
	
	protected abstract void handlerInitializateGUI();

	protected abstract String[] createColumnNames();
	
	protected Object[][] createDummyData() {
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
		super.addEventListener(ReloadDataEvent.class, (e) -> updateView());
		// TODO Auto-generated method stub

	}
	
	@Override
	public void updateView() {
		AppDataBaseCriteria criteria;
		int total;
		
		this.cleanData();
		
		log.trace("Update datatable...");
		
		criteria = this.buildCriteria();
		
		total = this.countByCriteria(criteria);
		
		log.trace("Count of Registers: {}", total);
		
		List<?> data = this.findByCriteria(criteria);
		
		log.trace("Number of Registers: {}", data.size());
	
		// TODO Auto-generated method stub
//		this.tableModel.addRow(new Object[]{});
	}

	private void cleanData() {
		int rowCount = this.tableModel.getRowCount();
		
		log.trace("Model row count: {}", rowCount);
		// TODO Auto-generated method stub
		
	}

	protected abstract List<?> findByCriteria(AppDataBaseCriteria criteria);

	protected abstract Integer countByCriteria(AppDataBaseCriteria criteria);

	protected abstract AppDataBaseCriteria buildCriteria();

}
