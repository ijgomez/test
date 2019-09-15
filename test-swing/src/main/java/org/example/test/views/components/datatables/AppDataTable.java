package org.example.test.views.components.datatables;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

import org.example.test.views.components.ApplicationConstants;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.datatables.listeners.AppDataTableHeaderMouseListener;
import org.example.test.views.components.datatables.pagging.PaginationPanel;
import org.example.test.views.components.datatables.renderer.AppDataTableHeaderRenderer;
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
//		TableRowSorter<AppDataTableModel<E>> tableRowSorter;
		
		this.filterView = createFilterView();
		
		this.tableModel = new AppDataTableModel<E>() {

			/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
			private static final long serialVersionUID = -8257919131789123073L;

			@Override
			protected Object getValueAt(E e, int columnIndex) {
				return handlerGetValueAt(e, columnIndex);
			}
			
			@Override
			protected void handlerShortAction() { updateView(); }
		};
		this.tableModel.setColumnNames(createColumnNames());
		
//		tableRowSorter = new TableRowSorter<>(this.tableModel);
//		tableRowSorter.addRowSorterListener((e) -> sorterChanged(e));
		
		this.table = new JTable();
		this.table.setModel(this.tableModel);
//      this.table.setAutoCreateRowSorter(true);
//		this.table.setRowSorter(tableRowSorter);
        this.table.setFillsViewportHeight(true);
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.table.getSelectionModel().addListSelectionListener((e) -> valueSelected(e));
        this.table.getTableHeader().setDefaultRenderer(new AppDataTableHeaderRenderer(this.table.getTableHeader().getDefaultRenderer()));
        this.table.getTableHeader().addMouseListener(new AppDataTableHeaderMouseListener(this.table));
        
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
	
//	private void sorterChanged(RowSorterEvent e) {
//		log.trace("event: {}", e);
//		// TODO Auto-generated method stub
//		
//	}

	private void valueSelected(ListSelectionEvent event) {
		if (event.getValueIsAdjusting()) {
            return;
        }
		this.handlerValueSelected(tableModel.getValueAt(table.getSelectedRow()));
	}

	protected abstract void handlerValueSelected(E valueSelected);

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

		criteria = this.buildCriteria(this.paginationPanel.getActualPage(), MAX_REGISTRY_BY_PAGE, this.tableModel.getSortColumn(), this.tableModel.getSortOrder());
		
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

	protected abstract C buildCriteria(int pageNumber, int pageSize, String sortColumn, String sortOrder);

}
