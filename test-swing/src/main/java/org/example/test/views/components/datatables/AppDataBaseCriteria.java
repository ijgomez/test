package org.example.test.views.components.datatables;

import lombok.Data;

@Data
public class AppDataBaseCriteria {

	private Integer pageNumber;
	
	private Integer pageSize;
	
	private String sortField;
	
	private String sortOrder;
	
}
