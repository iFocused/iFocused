package application.entities;

public class Process {

	private String processName;
	private int processId;
	private  int timesBlocked;

	public Process(String processName, int processId) {
		this.processName = processName;
		this.processId = processId;
		this.timesBlocked = 0;
	}

	public String getProcessName() {
		return processName;
	}

	public int getprocessId() {
		return processId;
	}

	public int getTimesBlocked() {
		return timesBlocked;
	}

	public void setProcessName(String newProcessName) {
		this.processName = newProcessName;
	}

	public void setprocessId(int newProcessId) {
		this.processId = newProcessId;
	}

	public void setTimesBlocked(int newTimesBlocked) {
		this.timesBlocked = newTimesBlocked;
	}

}
