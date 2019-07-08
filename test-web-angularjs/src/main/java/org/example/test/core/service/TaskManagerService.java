package org.example.test.core.service;

import java.util.List;

import org.example.test.core.domain.Task;
import org.example.test.core.domain.TaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

@Service
public class TaskManagerService {

	@Autowired
	private TaskDao taskDao;

	public void addTask(Task task) {
		this.taskDao.addTask(task);

	}

	public void archiveTask(int taskId) {
		this.taskDao.archiveTask(taskId);
	}

	public void updateTask(Task task) throws ParseException {
		this.taskDao.updateTask(task);

	}

	public void changeTaskStatus(int taskId, String status) throws ParseException {
		this.taskDao.changeTaskStatus(taskId, status);

	}

	public List<Task> getAllTasks() {
		return this.taskDao.getAllTasks();

	}

	public Task getTaskById(int taskId) {
		return this.taskDao.getTaskById(taskId);

	}

}
