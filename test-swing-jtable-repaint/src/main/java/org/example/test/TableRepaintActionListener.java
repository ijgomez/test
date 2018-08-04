package org.example.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableRepaintActionListener implements ActionListener {
	
	private JTable table;
	
	public TableRepaintActionListener(JTable table) {
		this.table = table;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("timer---------------------");
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		model.setValueAt(new java.util.Date().getTime(), 0, 0);
		//model.fireTableCellUpdated(0, 0);
		//model.fireTableCellUpdated(9, 4);
		model.fireTableDataChanged();
	}

}
