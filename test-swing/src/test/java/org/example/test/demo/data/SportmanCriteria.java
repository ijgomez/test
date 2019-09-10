package org.example.test.demo.data;

import org.example.test.views.components.datatables.AppDataTableCriteria;

import lombok.Data;

@Data
public class SportmanCriteria extends AppDataTableCriteria {

	private String name;
	
	private String surname;
	
	private String sport;
	
	private Integer year;
	
	private Boolean vegetarian;
	
}
