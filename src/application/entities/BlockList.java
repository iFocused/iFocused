package application.entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class BlockList {
	private int blockListId;
	private String updatedDate;
	private ArrayList<Website> blockedWebsites;
	private ArrayList<Process> blockedProcesses;
	private boolean isEnabled;

	public BlockList(int blockListId) {
		this(blockListId, false);
	}

	public BlockList(int blockListId, boolean isEnabled) {
		this(blockListId, isEnabled, new ArrayList<>(Arrays.asList()), new ArrayList<>(Arrays.asList()));
	}

	public BlockList(int blockListId, ArrayList<Website> blockedWebsites,
			ArrayList<Process> blockedProcesses) {
		this(blockListId, false, blockedWebsites, blockedProcesses);
	}

	public BlockList(int blockListId, boolean isEnabled, ArrayList<Website> blockedWebsites, 
			ArrayList<Process> blockedProcesses) {
		this.isEnabled = isEnabled;
		this.blockListId = blockListId;
		this.blockedWebsites = blockedWebsites;
		this.blockedProcesses = blockedProcesses;

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		this.updatedDate = formatter.format(new Date());
	}

	public void addWebsite(Website w) {
		this.blockedWebsites.add(w);
	}

	public void removeWebsite(Website w) {
		this.blockedWebsites.remove(w);
	}

	public void addProcess(Process p) {
		this.blockedProcesses.add(p);
	}

	public void removeProcess(Process p) {
		this.blockedProcesses.remove(p);
	}

	/* Getters */

	public boolean getIsEnabled() {
		return this.isEnabled;
	}

	public int getBlockListId() {
		return blockListId;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public ArrayList<Website> getBlockedWebsites() {
		return blockedWebsites;
	}

	public ArrayList<Process> getBlockedProcesses() {
		return blockedProcesses;
	}

	/* Setters */

	public void setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public void setBlockListId(int newId) {
		this.blockListId = newId;
	}

	public void setUpdatedDate(String newDate) {
		this.updatedDate = newDate;
	}

	public void setBlockedWebsites(ArrayList<Website> websites) {
		this.blockedWebsites = websites;
	}

	public void setBlockedProcesses(ArrayList<Process> processes) {
		this.blockedProcesses = processes;
	}

}
