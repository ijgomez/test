package org.example.test;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class TableRepaint extends JFrame {


	private static final long serialVersionUID = -6144479420417331742L;
	
	private final static int ROWS = 20;
	
	private final static int COLUMNS = 10;

	public TableRepaint() {
		String[] headers = new String[COLUMNS];
		for (int c = 0; c < COLUMNS; c++) {
			headers[c] = "Header " + Integer.toString(c);
		}

		String[][] cells = new String[ROWS][COLUMNS];
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLUMNS; c++) {
				cells[r][c] = Integer.toString(r) + "," + Integer.toString(c);
			}
		}

		final JTable table = new JTable(new DefaultTableModel(cells, headers));

		table.setDefaultRenderer(Object.class, new TableRepaintTableCellRenderer());

		getContentPane().add(table);

		Timer redrawTimer = new Timer(3000, new TableRepaintActionListener(table));
		redrawTimer.start();
	}

}