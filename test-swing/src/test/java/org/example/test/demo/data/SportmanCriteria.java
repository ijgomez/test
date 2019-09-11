package org.example.test.demo.data;

import org.example.test.views.components.datatables.AppDataTableCriteria;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SportmanCriteria extends PaginationCriteria implements AppDataTableCriteria {

	private String name;
	
	private String surname;
	
	private String sport;
	
	private Integer year;
	
	private Boolean vegetarian;
	
}
