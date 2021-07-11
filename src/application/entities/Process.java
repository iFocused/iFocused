package application.entities;

import java.io.File;

public class Process {

	private File process;
	private int processId;
	private int timesBlocked;

	public Process() {}
	
	public Process(File process, int processId) {
		this.process = process;
		this.processId = processId;
		this.timesBlocked = 0;
	}

	public String getProcessName() {
		return process.getName();
	}

	public int getProcessId() {
		return processId;
	}

	public int getTimesBlocked() {
		return timesBlocked;
	}

	public void setProcess(File newProcess) {
		this.process = newProcess;
	}

	public void setProcessId(int newProcessId) {
		this.processId = newProcessId;
	}

	public void setTimesBlocked(int newTimesBlocked) {
		this.timesBlocked = newTimesBlocked;
	}

}
