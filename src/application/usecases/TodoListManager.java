package application.usecases;

import application.entities.Task;
import application.entities.TaskStatus;
import application.entities.TodoList;
import application.gateways.TODOListGateway;

public class TodoListManager {
	private final TODOListGateway todoListGateway;
	private TodoList todoList;
	
	public TodoListManager () {todoListGateway = null;}

	/**
	 * Constructor for the TODO List manager
	 * 
	 * @param todoListGateway
	 */
	public TodoListManager(TODOListGateway todoListGateway) {
		this.todoList = new TodoList();
		this.todoListGateway = todoListGateway;
		this.todoListGateway.populateTodoListContents(this);
	}

	/**
	 * Marks the given task as completed and adds it to the completed tasks list
	 * 
	 * @param Task The task that was completed
	 */
	public void completeTask(Task task) {
		task.setTaskStatus(TaskStatus.COMPLETED);
		this.todoList.getCurrentTasks().remove(task);
		this.todoList.getCompletedTasks().add(task);
	}

	/**
	 * Adds a task to the current task list.
	 * 
	 * @param Task The task to be added
	 */
	public void addTask(Task task) {
		this.todoList.getCurrentTasks().add(task);
	}

	/**
	 * Removes a task from the current task list
	 * 
	 * @param Task The task to be removed
	 */
	public void removeTask(Task task) {
		this.todoList.getCurrentTasks().remove(task);
	}

	public void saveTodoListContents() {
		this.todoListGateway.saveTodoListContents(this);
	}

	public TodoList getTodoList() {
		return todoList;
	}

	public void setTodoList(TodoList todoList) {
		this.todoList = todoList;
	}

}
