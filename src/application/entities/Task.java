package application.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
	private String name;
	private TaskStatus taskStatus;
	private String startTime;
	private String endTime;

	public Task(String name) {
		// getting today's date and time (keep in mind the possibility where
		// the user will manually change their time on the machine)
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.startTime = formatter.format(new Date());

		this.endTime = "";
		this.taskStatus = TaskStatus.INITALIZED;
		this.name = name;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getName() {
		return this.name;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public String getEndTime(String newEndTime) {
		return this.endTime;
	}

	public void setName(String name) {
		this.name = name;
	}

}
