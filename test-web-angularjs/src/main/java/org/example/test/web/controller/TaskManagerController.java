package org.example.test.web.controller;

import java.text.ParseException;
import java.util.List;

import org.example.test.core.domain.Task;
import org.example.test.core.service.TaskManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskManagerController {
	
	@Autowired
	private TaskManagerService taskmanagerservice;
	
	 @RequestMapping(value="/tasks",method = RequestMethod.GET,headers="Accept=application/json")
	 public List<Task> getAllTasks() {	 
	  List<Task> tasks=taskmanagerservice.getAllTasks();
	  return tasks;
	
	 }
	 
	 
	 @RequestMapping(value="/tasks/archive/{taskIds}",method = RequestMethod.POST,headers="Accept=application/json")
	 public List<Task> archiveAllTasks(@PathVariable long[] taskIds) {	
		 for(int i=0;i<taskIds.length;i++){
			 taskmanagerservice.archiveTask(taskIds[i]);	
			 
		 }
	  List<Task> tasks=taskmanagerservice.getAllTasks();
	  return tasks;
	
	 }
	 
	 @RequestMapping(value="/tasks/{taskId}/{taskStatus}",method = RequestMethod.POST,headers="Accept=application/json")
	 public List<Task> changeTaskStatus(@PathVariable int taskId,@PathVariable String taskStatus) throws ParseException {	
		 taskmanagerservice.changeTaskStatus(taskId,taskStatus);		 
		 return taskmanagerservice.getAllTasks();
		 
	 }
	 
	 @RequestMapping(value="/tasks/insert/{taskName}/{taskDesc}/{taskPriority}/{taskStatus}",method = RequestMethod.POST,headers="Accept=application/json")
	 public List<Task> addTask(@PathVariable String taskName,@PathVariable String taskDesc,@PathVariable String taskPriority,@PathVariable String taskStatus) throws ParseException {	
		Task task = new Task();
		task.setName(taskName);
		task.setDescription(taskDesc);
		task.setPriority(taskPriority);
		task.setStatus(taskStatus);
		
		taskmanagerservice.addTask(task);
		return taskmanagerservice.getAllTasks();
		 
	 }	 	 	 	 
}