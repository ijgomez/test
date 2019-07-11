package org.example.test.core.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
	@SequenceGenerator(name = "task_seq", sequenceName = "task_seq", allocationSize = 1)
	private Long id;

	private String name;
	
	private String description;
	
	private String priority;
	
	private String status;
	
	private LocalDateTime startTime;
	
	private LocalDateTime endTime;
	
	private Boolean archived;
	
}
