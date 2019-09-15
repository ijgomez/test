package org.example.test.views.components.datatables;

public interface AppDataTableCriteria {

	Integer getPageNumber();

	void setPageNumber(Integer pageNumber);

	Integer getPageSize();

	void setPageSize(Integer pageSize);

	String getSortField();

	void setSortField(String sortField);

	String getSortOrder();

	void setSortOrder(String sortOrder);
	
}
