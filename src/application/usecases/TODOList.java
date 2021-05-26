package application.usecases;

import java.util.ArrayList;

import application.entities.Task;
import application.entities.TaskStatus;

public class TODOList {
	private ArrayList<Task> currentTasks;
	private ArrayList<Task> completedTasks;

	/**
	 * Constructor for the TODO List repository
	 */
	public TODOList() {
		this.currentTasks = new ArrayList<>();
		this.completedTasks = new ArrayList<>();
	}

	/**
	 * Marks the given task as completed and adds it to the completed tasks list
	 * 
	 * @param Task The task that was completed
	 */
	public void completeTask(Task task) {
		task.setTaskStatus(TaskStatus.COMPLETED);
		this.currentTasks.remove(task);
		this.completedTasks.add(task);
	}

	/**
	 * Adds a task to the current task list.
	 * 
	 * @param Task The task to be added
	 */
	public void addTask(Task task) {
		this.currentTasks.add(task);
	}

	/**
	 * Removes a task from the current task list
	 * 
	 * @param Task The task to be removed
	 */
	public void removeTask(Task task) {
		this.currentTasks.remove(task);
	}

	/**
	 * Return a list of all the current tasks in this repository
	 * 
	 * @return a list of all the uncompleted tasks
	 */
	public ArrayList<Task> getCurrentTasks() {
		return this.currentTasks;
	}

	/**
	 * Return a list of all the completed tasks in this repository
	 * 
	 * @return a list of all the completed tasks
	 */
	public ArrayList<Task> getCompletedTasks() {
		return this.completedTasks;
	}

	/**
	 * Return the most recent task that was completed
	 * 
	 * @return the most recent completed task
	 */
	public Task getRecentCompletedTask() {
		ArrayList<Task> completedTasks = this.getCompletedTasks();
		return completedTasks.get(completedTasks.size() - 1);
	}
}
