package org.example.test.views.components.datatables.listeners;

import java.awt.event.ActionEvent;

public interface AppDataTablePaginationListener {

	void firstPageAction(ActionEvent e);
	
	void previousPageAction(ActionEvent e);
	
	void nextPageAction(ActionEvent e);
	
	void lastPageAction(ActionEvent e);
	
}
