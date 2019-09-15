package org.example.test.views.components.datatables.renderer;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

import org.example.test.views.components.datatables.listeners.AppDataTableSorterListener;

public class AppDataTableHeaderRenderer implements TableCellRenderer {
	
	private TableCellRenderer defaultRenderer;

	/**
	 * New Instance.
	 * 
	 * @param defaultRenderer Default Renderer.
	 */
	public AppDataTableHeaderRenderer(TableCellRenderer defaultRenderer) {
		this.defaultRenderer = defaultRenderer;
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		String sortColumn, sortOrder;
		Icon sortIcon = null;
		
		Component component = this.defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (component instanceof JLabel) {
			sortColumn = ((AppDataTableSorterListener) table.getModel()).getSortColumn();
			if (sortColumn != null) {
				sortOrder = ((AppDataTableSorterListener) table.getModel()).getSortOrder();
			
				if (sortOrder != null && (value.equals(sortColumn))) {
					if (sortOrder.equals("ASC")) {
						sortIcon = UIManager.getIcon("Table.ascendingSortIcon");
					} else {
						sortIcon = UIManager.getIcon("Table.descendingSortIcon");
					}
  
	            } else {
	            	 sortIcon = UIManager.getIcon("Table.naturalSortIcon");
	            }
	            ((JLabel)component).setIcon(sortIcon);
			}
		}
		return component;
	}

}
