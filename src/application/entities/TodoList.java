package application.entities;

import java.util.ArrayList;

public class TodoList {
	private ArrayList<Task> currentTasks;
	private ArrayList<Task> completedTasks;

	/**
	 * Constructor for the TODO List entity
	 */
	public TodoList() {
		this.currentTasks = new ArrayList<>();
		this.completedTasks = new ArrayList<>();
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

	/**
	 * Set the current active tasks of this todo list
	 * 
	 * @param currentTasks that this todo list will use
	 */
	public void setCurrentTasks(ArrayList<Task> currentTasks) {
		this.currentTasks = currentTasks;
	}

	/**
	 * Set the completed tasks of this todo list
	 * 
	 * @param completedTasks that were marked as completed in this todo list will
	 *                       use
	 */
	public void setCompletedTasks(ArrayList<Task> completedTasks) {
		this.completedTasks = completedTasks;
	}
}
