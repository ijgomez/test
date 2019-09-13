package org.example.test.views.components.datatables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.example.test.views.components.ApplicationConstants;

public abstract class AppDataTableModel<E> extends AbstractTableModel implements ApplicationConstants {

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
		if (this.data == null || this.data.isEmpty() || (rowIndex + 1) > this.data.size()) {
			return null;
		}
		E e = this.data.get(rowIndex);
		return getValueAt(e, columnIndex);
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

	

}
