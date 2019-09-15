package org.example.test.views.components.datatables.listeners;

public interface AppDataTableSorterListener {

	void shortAction(int columnIndex);

	String getSortColumn();
	
	String getSortOrder();
}
