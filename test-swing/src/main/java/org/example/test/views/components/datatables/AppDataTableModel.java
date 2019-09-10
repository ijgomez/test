package org.example.test.views.components.datatables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AppDataTableModel<E> extends AbstractTableModel {

	private static final long serialVersionUID = 5661446774375595890L;

	private String[] columnNames;
	
	private List<E> data;
	
	public AppDataTableModel() {
		this.data = new ArrayList<>();
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		return null;
	}

}
