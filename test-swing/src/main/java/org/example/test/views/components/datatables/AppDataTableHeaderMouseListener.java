package org.example.test.views.components.datatables;

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
		
		columnIndex = table.getColumnModel().getColumnIndexAtX(e.getX());
		// TODO Auto-generated method stub
		if (e.getClickCount() == 2) {
			log.trace("Column Selected: id={}, name={}", columnIndex, table.getModel().getColumnName(columnIndex));
//			((AppDataTableModel)table.getModel()).setOrderBy(columnIndex);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
