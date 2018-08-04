package org.example.test;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class TableRepaintTableCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

	private static final long serialVersionUID = 39540842631868664L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		System.out.println("R" + row + ",C" + column);

		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}
	
}
