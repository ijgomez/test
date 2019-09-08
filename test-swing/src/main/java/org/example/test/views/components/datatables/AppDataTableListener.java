package org.example.test.views.components.datatables;

import java.awt.event.ActionEvent;

public interface AppDataTableListener {

	void firstPageAction(ActionEvent e);
	
	void previousPageAction(ActionEvent e);
	
	void nextPageAction(ActionEvent e);
	
	void lastPageAction(ActionEvent e);
	
}
