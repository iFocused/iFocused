package application.entities;

import java.io.File;

public class Process {

	private String processName;
	private int processId;
	private int timesBlocked;

	public Process() {
		this.processName = "";
	}

	public Process(File process, int processId) {
		this.processName = process.getName();
		this.processId = processId;
		this.timesBlocked = 0;
	}

	public String getProcessName() {
		return processName;
	}

	public int getProcessId() {
		return processId;
	}

	public int getTimesBlocked() {
		return timesBlocked;
	}

	public void setProcess(String newProcessName) {
		this.processName = newProcessName;
	}

	public void setProcessId(int newProcessId) {
		this.processId = newProcessId;
	}

	public void setTimesBlocked(int newTimesBlocked) {
		this.timesBlocked = newTimesBlocked;
	}

	public void setProcessName(String newProcessName) {
		this.processName = newProcessName;
	}

}
