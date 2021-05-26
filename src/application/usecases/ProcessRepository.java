package application.usecases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import application.entities.Process;

public class ProcessRepository {
	private Map<Integer, Process> processes;
	private int end;

	/**
	 * Constructor for the Process repository
	 */
	public ProcessRepository() {
		this.end = 0;
		this.processes = new HashMap<>();
	}

	/**
	 * Adds a new Process.
	 * 
	 * @param Process The Process to be added
	 */
	public int createProcess(String processName) {
		this.processes.put(this.end, new Process(processName, this.end));
		int tmpId = this.end;
		this.end++;
		return tmpId;
	}

	/**
	 * Removes a Process.
	 * 
	 * @param Process The Process to be removed
	 */
	public void removeProcessByName(String processName) {
		boolean found = false;
		int prevKey = 0;

		// finding the key corresponding to Process
		for (int key : this.processes.keySet()) {
			Process tmpProcess = this.processes.get(key);
			if (!found) {
				if (tmpProcess.getProcessName().equals(processName)) {
					found = true;
					this.processes.remove(key);
					prevKey = key;
				}
			} else {
				// shifting all the rest of the ids down by 1 to maintain order
				this.processes.remove(key);
				this.processes.put(prevKey, tmpProcess);
				prevKey = key;
			}

		}
		this.end = prevKey;
	}

	/**
	 * Return a list of all the Processes in this repository
	 * 
	 * @return a list of all the Process
	 */
	public ArrayList<Process> getProcess() {
		ArrayList<Process> processes = new ArrayList<>();
		for (Process Process : this.processes.values()) {
			processes.add(Process);
		}
		return processes;
	}

	/**
	 * Return a Process corresponding to the given <id>.
	 * 
	 * @param id The identifier for the sought after Process
	 * @return a list of Process up to a certain time in ascending order
	 */
	public Process getNProcessById(int id) {
		return this.processes.get(id);
	}

	/**
	 * Returns the highest identifier number in the list of Processes
	 * 
	 * @return the highest identifier number in the list of Processes
	 */
	public int getHighestId() {
		return this.end;
	}
}
