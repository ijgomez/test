package org.example.test.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "PERSON")
@Data
public class Person {

	@Id
	@GeneratedValue
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;

	@Column(nullable = false)
	private String surname;

	public Person() {
		super();
	}

	public Person(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}
}
