package org.example.test.core.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.example.test.core.domain.Task;
import org.example.test.core.domain.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TaskManagerService {

	@Autowired
	private TaskRepository taskRepository;
	
	public void addTask(Task task) {
		this.taskRepository.save(task);
	}

	public void archiveTask(Long taskId) {
		Optional<Task> o = this.taskRepository.findById(taskId);
		if (o.isPresent()) {
			Task task = o.get();
			task.setTask_archived(true);
			
			this.taskRepository.save(task);
		}
	}

	public void updateTask(Task task) throws ParseException {
		this.taskRepository.save(task);
	}

	public void changeTaskStatus(long taskId, String status) throws ParseException {
		Optional<Task> o = this.taskRepository.findById(taskId);
		if (o.isPresent()) {
			Task task = o.get();
			task.setTaskStatus(status);
			
			this.taskRepository.save(task);
		}

	}

	public List<Task> getAllTasks() {
		return StreamSupport.stream(this.taskRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public Task getTaskById(Long taskId) {
		return this.taskRepository.findById(taskId).get();
	}

}
