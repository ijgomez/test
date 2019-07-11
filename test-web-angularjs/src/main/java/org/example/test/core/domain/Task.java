package org.example.test.core.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
	@SequenceGenerator(name = "task_seq", sequenceName = "task_seq", allocationSize = 1)
	private Long task_id;
	 private String task_name;
	 private String task_description; 
	 private String task_priority;
	 private String task_status;
	 private Boolean task_archived;
	 
	 public Long getTaskId() {
	  return task_id;
	 }
	 public void setTaskId(Long taskId) {
	  this.task_id = taskId;
	 }
	 public String getTaskName() {
	  return task_name;
	 }
	 public void setTaskName(String taskName) {
	  this.task_name = taskName;
	 }
	 public String getTaskDescription() {
	  return task_description;
	 }
	 public void setTaskDescription(String taskDescription) {
	  this.task_description = taskDescription;
	 }
	 
	 
	 public String getTaskPriority() {
	  return task_priority;
	 }
	 public void setTaskPriority(String taskPriority) {
	  this.task_priority = taskPriority;
	 }
	 
	 public String getTaskStatus() {
			return task_status;
		}
	 
	public void setTaskStatus(String taskStatus) {
			this.task_status = taskStatus;
		}
	
	public Boolean getTask_archived() {
		return task_archived;
	}
	
	public void setTask_archived(Boolean task_archived) {
		this.task_archived = task_archived;
	}
		
	 @Override
	 public String toString() {
	  return "Task [task_id=" + task_id + ", task_name=" + task_name
	    + ", task_description=" + task_description + ", task_priority="
	    + task_priority +",task_status="+task_status+ "]";
	 }
	  

}
