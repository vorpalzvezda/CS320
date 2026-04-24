package taskservice;

import java.util.Map;
import java.util.HashMap;

import task.Task;

/**
 * Manages Task objects in memory
 * 
 * TaskService: adding, updating, retrieving, and deleting tasks by unique taskID
 * Field validation is enforced by the Task Class definition
 */
public class TaskService {
	
	// Store task objects
	private final Map<String, Task> taskList = new HashMap<>();
	
	/**
	 * Creates and stores new task objects
	 * @param id
	 * @param name
	 * @param info
	 * @throws IllegalArgumentException if the ID already exists or input is invalid
	 */
	public void addTask(String id, String name, String info) {
		
		if (taskList.containsKey(id)) {
			throw new IllegalArgumentException("Task ID Already Exists");
		}
		
		Task task = new Task(id, name, info);
		taskList.put(task.getID(), task);
	}
	
	/**
	 * Helper method for repeated lookup operations 
	 * @param taskID
	 * @return valid task object
	 * @throws IllegalArgumentException if the ID already exists or input is invalid
	 */
	public Task findTask(String taskID) {
		Task task = taskList.get(taskID);
		
		if (task == null) {
			throw new IllegalArgumentException("No Task Found");
		}
		
		return task;
	}
	
	/**
	 * Deletes a task object by ID
	 * @param taskID
	 */
	public void deleteTask(String taskID) {
		findTask(taskID);
		taskList.remove(taskID);
	}
	
	/**
	 * Updates a task name by ID
	 * @param taskID
	 * @param name
	 */
	public void updateTaskName(String taskID, String name) {
		findTask(taskID).setTaskName(name);
	}
	
	/**
	 * Updates a task description by ID
	 * @param taskID
	 * @param info
	 */
	public void updateTaskDescription(String taskID, String info) {
		findTask(taskID).setDescription(info);
	}
}
