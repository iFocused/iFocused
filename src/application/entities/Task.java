package application.entities;

public class Task {
	private String name;
	private TaskStatus taskStatus;

	public Task() {}
	
	public Task(String name) {
		this.taskStatus = TaskStatus.INITALIZED;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}
}
