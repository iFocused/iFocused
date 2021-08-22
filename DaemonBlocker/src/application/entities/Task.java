package application.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
	private String description;
	private TaskStatus taskStatus;
	private String startTime;
	private String endTime;

	public Task(String description) {
		// getting today's date and time (keep in mind the possibility where
		// the user will manually change their time on the machine)
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.startTime = formatter.format(new Date());

		this.endTime = "";
		this.taskStatus = TaskStatus.INITALIZED;
		this.description = description;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getDescription() {
		return this.description;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public String getEndTime(String newEndTime) {
		return this.endTime;
	}

	public void setDescription(String newDescription) {
		this.description = newDescription;
	}

}
