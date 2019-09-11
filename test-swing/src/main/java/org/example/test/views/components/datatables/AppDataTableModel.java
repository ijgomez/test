package org.example.test.views.components.datatables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public abstract class AppDataTableModel<E> extends AbstractTableModel {

	private static final long serialVersionUID = 5661446774375595890L;

	private String[] columnNames;
	
	private List<E> data;

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
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		E e = this.data.get(rowIndex);
		return getValueAt(e, columnIndex);
	}
	
	protected abstract Object getValueAt(E e, int columnIndex);

	public void addData(E e) {
		data.add(e);
		fireTableRowsInserted(data.size() - 1, data.size() - 1);
	}

}
