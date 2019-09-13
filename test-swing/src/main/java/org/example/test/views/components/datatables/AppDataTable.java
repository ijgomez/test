package org.example.test.views.components.datatables;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.example.test.views.components.ApplicationConstants;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.datatables.pagging.PaginationPanel;
import org.example.test.views.components.events.ReloadDataEvent;
import org.example.test.views.components.panels.AppPanel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AppDataTable<E, C> extends AppPanel implements ApplicationModelListener, ApplicationConstants {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = 6061707010961045115L;
	
	private JPanel filterView;
	
	private JTable table;
	
	private AppDataTableModel<E> tableModel;
	
	private PaginationPanel paginationPanel;

	@Override
	protected void initializateGUI() {
		JScrollPane scrollPane;
		
		this.filterView = createFilterView();
		
		this.tableModel = new AppDataTableModel<E>() {

			/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
			private static final long serialVersionUID = -8257919131789123073L;

			@Override
			protected Object getValueAt(E e, int columnIndex) {
				return handlerGetValueAt(e, columnIndex);
			}
		};
		this.tableModel.setColumnNames(createColumnNames());
		
		
		this.table = new JTable();
		this.table.setModel(this.tableModel);
        this.table.setFillsViewportHeight(true);
		
        scrollPane = new JScrollPane(this.table);
        
        this.paginationPanel = new PaginationPanel();
		
		super.setLayout(new BorderLayout());
		if (this.filterView != null) {
			super.add(this.filterView, BorderLayout.NORTH);
		}
		super.add(scrollPane, BorderLayout.CENTER);
		super.add(this.paginationPanel, BorderLayout.SOUTH);
		
		this.handlerInitializateGUI();
	}
	
	protected abstract JPanel createFilterView();

	protected abstract Object handlerGetValueAt(E e, int columnIndex);

	protected abstract void handlerInitializateGUI();

	protected abstract String[] createColumnNames();
	
	protected Object[][] createDummyData() {
		return new Object[][] {};
	}

	@Override
	protected void registerEventListeners() {
		super.addEventListener(ReloadDataEvent.class, (e) -> updateView());
	}
	
	@Override
	public void updateView() {
		C criteria;
		List<E> data;
		int total;
		
		this.cleanData();
		
		log.trace("Update datatable...");

		criteria = this.buildCriteria(this.paginationPanel.getActualPage(), MAX_REGISTRY_BY_PAGE);
		
		total = this.countByCriteria(criteria);
		log.trace("Count of Registers: {}", total);
		this.paginationPanel.setTotalRegistry(total);
		
		data = this.findByCriteria(criteria);
		log.trace("Number of Registers: {}", data.size());
	
		this.tableModel.addData(data);
		this.paginationPanel.updateView();
	}

	private void cleanData() {
		int rowCount = this.tableModel.getRowCount();
		
		log.trace("Remove {} rows...", rowCount);
		while (tableModel.getData().size() > 0) {
			tableModel.getData().remove(0);
		}
		tableModel.fireTableDataChanged();
	}

	protected abstract List<E> findByCriteria(C criteria);

	protected abstract Integer countByCriteria(C criteria);

	protected abstract C buildCriteria(int pageNumber, int pageSize);

}
