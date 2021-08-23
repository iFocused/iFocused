package application.entities;

import java.time.LocalDate;

public class Task {
	private String name;
	private TaskStatus taskStatus;
	private String startTime;
	private String endTime;

	public Task() {
	}

	public Task(String name) {
		this.taskStatus = TaskStatus.INITALIZED;
		this.startTime = LocalDate.now().toString();
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
		if (taskStatus == TaskStatus.COMPLETED) {
			this.endTime = LocalDate.now().toString();
		}
		this.taskStatus = taskStatus;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public boolean isCompleted() {
		return this.taskStatus == TaskStatus.COMPLETED;
	}

}
