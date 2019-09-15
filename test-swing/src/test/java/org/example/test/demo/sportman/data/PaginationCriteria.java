package org.example.test.demo.sportman.data;

import org.example.test.views.components.datatables.AppDataTableCriteria;

import lombok.Data;

@Data
public class PaginationCriteria implements AppDataTableCriteria {

	private Integer pageNumber;
	
	private Integer pageSize;
	
	private String sortField;
	
	private String sortOrder;
	
}
