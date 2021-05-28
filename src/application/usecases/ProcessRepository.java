package application.usecases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import application.entities.Process;

public class ProcessRepository implements Serializable {
	private Map<Integer, Process> processes;
	private int currId;

	/**
	 * Constructor for the Process repository
	 */
	public ProcessRepository() {
		this.currId = 0;
		this.processes = new HashMap<>();
	}

	/**
	 * Adds a new Process.
	 * 
	 * @param Process The Process to be added
	 */
	public int createProcess(String processName) {
		this.processes.put(this.currId, new Process(processName, this.currId));
		int tmpId = this.currId;
		this.currId++;
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
		this.currId = prevKey;
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
	 * @return a list of Process up to a certain time in asccurrIding order
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
		return this.currId;
	}
}
