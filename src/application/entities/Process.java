package application.entities;

import java.io.File;

public class Process {

	private File process;
	private int processId;
	private int timesBlocked;
	private String description;

	public Process(File process, String description, int processId) {
		this.process = process;
		this.processId = processId;
		this.description = description;
		this.timesBlocked = 0;
	}

	public String getProcessName() {
		return process.getName();
	}

	public int getprocessId() {
		return processId;
	}

	public int getTimesBlocked() {
		return timesBlocked;
	}

	public void setProcess(File newProcess) {
		this.process = newProcess;
	}

	public void setprocessId(int newProcessId) {
		this.processId = newProcessId;
	}

	public void setTimesBlocked(int newTimesBlocked) {
		this.timesBlocked = newTimesBlocked;
	}
	
	public String getDescription() {
		return this.description;
	}

}
