package org.example.test.views.components.datatables;

import lombok.Data;

@Data
public class AppDataTableCriteria {

	private Integer pageNumber;
	
	private Integer pageSize;
	
	private String sortField;
	
	private String sortOrder;
	
}
