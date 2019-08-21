package org.example.test.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Index {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "index_seq")
	@SequenceGenerator(name = "index_seq", sequenceName = "index_seq", allocationSize = 1)
	private Long id;
	
	private String fileName;
}
