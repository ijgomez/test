package org.example.test.views.components.datatables.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppDataTableHeaderMouseListener implements MouseListener {
	
	private JTable table;
	
	/**
	 * New Instance.
	 * 
	 * @param table
	 */
	public AppDataTableHeaderMouseListener(JTable table) {
		this.table = table;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int columnIndex;

		columnIndex = table.convertColumnIndexToModel(table.columnAtPoint(e.getPoint()));
		if (e.getClickCount() == 2) { //Double click
			log.trace("Column Selected: id={}, name={}", columnIndex, table.getColumnName(columnIndex));
			((AppDataTableSorterListener) this.table.getModel()).shortAction(columnIndex);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

}
