package org.example.test.views.components.datatables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.example.test.views.components.ApplicationConstants;
import org.example.test.views.components.datatables.listeners.AppDataTableSorterListener;

public abstract class AppDataTableModel<E> extends AbstractTableModel implements AppDataTableSorterListener, ApplicationConstants {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = 5661446774375595890L;

	private String[] columnNames;
	
	private List<E> data;
	
	private int registryPageMax = MAX_REGISTRY_BY_PAGE;

	/**
	 * New Instance.
	 */
	public AppDataTableModel() {
		this.data = new ArrayList<>();
	}
	
	public void setData(List<E> data) {
		this.data = data;
	}
	
	public List<E> getData() {
		return data;
	}
	
	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}
	
	@Override
	public String getColumnName(int column) {
		if (column > this.columnNames.length) {
			return super.getColumnName(column);
		}
		return this.columnNames[column];
	}
	
	@Override
	public int getRowCount() {
		return (this.registryPageMax == 0)? this.data.size() : this.registryPageMax;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		E e = this.getValueAt(rowIndex);
		return (e != null) ? getValueAt(e, columnIndex) : null;
	}
	
	/**
	 * Returns the object for the row at rowIndex.
	 * 
	 * @param rowIndex the row whose value is to be queried
	 * @return the object at the specified row.
	 */
	public E getValueAt(int rowIndex) {
		if (this.data == null || this.data.isEmpty() || (rowIndex + 1) > this.data.size()) {
			return null;
		}
		return this.data.get(rowIndex);
	}
	
	protected abstract Object getValueAt(E e, int columnIndex);

	public void addData(E e) {
		data.add(e);
		fireTableRowsInserted(data.size() - 1, data.size() - 1);
	}
	
	public void addData(List<E> e) {
		data.clear();
		data.addAll(e);
		fireTableDataChanged();		
	}
	
	public int getRegistryPageMax() {
		return registryPageMax;
	}
	
	public void setRegistryPageMax(int registryPageMax) {
		this.registryPageMax = registryPageMax;
	}
	
	private Integer sortColumnIndex;
	
	private String sortOrder;
	
	@Override
	public String getSortColumn() {
		return (sortColumnIndex != null) ? this.getColumnName(sortColumnIndex) : null;
	}

	@Override
	public String getSortOrder() {
		return (sortColumnIndex != null) ? this.sortOrder : null;
	}
	
	@Override
	public void shortAction(int columnIndex) {
		if (this.sortColumnIndex == null || this.sortColumnIndex != columnIndex) {
			this.sortColumnIndex = columnIndex;
			this.sortOrder = "ASC";
		} else {
			this.sortOrder = (this.sortOrder.equals("DESC")) ? "ASC": "DESC";
		}
		handlerShortAction();
	}

	protected abstract void handlerShortAction();
	
	
}
