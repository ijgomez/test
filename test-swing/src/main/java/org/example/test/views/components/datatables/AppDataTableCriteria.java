package org.example.test.views.components.datatables;

public interface AppDataTableCriteria {

	public Integer getPageNumber();

	public void setPageNumber(Integer pageNumber);

	public Integer getPageSize();

	public void setPageSize(Integer pageSize);

	public String getSortField();

	public void setSortField(String sortField);

	public String getSortOrder();

	public void setSortOrder(String sortOrder);
	
}
