package application.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class BlockList {
	private String blocklistName;
	private String description;
	private int blockListId;
	private LocalDateTime updatedDate;
	private ArrayList<Website> blockedWebsites;
	private ArrayList<Process> blockedProcesses;
	private boolean isEnabled;

	public BlockList(String blocklistName, String description, int blockListId) {
		this(blocklistName, description, blockListId, false);
	}

	public BlockList(String blocklistName, String description, int blockListId, boolean isEnabled) {
		this(blocklistName, description, blockListId, isEnabled, new ArrayList<>(Arrays.asList()),
				new ArrayList<>(Arrays.asList()));
	}

	public BlockList(String blocklistName, String description, int blockListId, ArrayList<Website> blockedWebsites,
			ArrayList<Process> blockedProcesses) {
		this(blocklistName, description, blockListId, false, blockedWebsites, blockedProcesses);
	}

	public BlockList(String blocklistName, String description, int blockListId, boolean isEnabled,
			ArrayList<Website> blockedWebsites, ArrayList<Process> blockedProcesses) {
		this.blocklistName = blocklistName;
		this.description = description;
		this.isEnabled = isEnabled;
		this.blockListId = blockListId;
		this.blockedWebsites = blockedWebsites;
		this.blockedProcesses = blockedProcesses;

		this.updatedDate = LocalDateTime.now();
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

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public ArrayList<Website> getBlockedWebsites() {
		return blockedWebsites;
	}

	public ArrayList<Process> getBlockedProcesses() {
		return blockedProcesses;
	}

	public String getBlocklistName() {
		return blocklistName;
	}

	public String getBlocklistDescription() {
		return description;
	}

	/* Setters */

	public void setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public void setBlockListId(int newId) {
		this.blockListId = newId;
	}

	public void setUpdatedDate(LocalDateTime newDate) {
		this.updatedDate = newDate;
	}

	public void setBlockedWebsites(ArrayList<Website> websites) {
		this.blockedWebsites = websites;
	}

	public void setBlockedProcesses(ArrayList<Process> processes) {
		this.blockedProcesses = processes;
	}

	public void setBlocklistName(String newBlocklistName) {
		this.blocklistName = newBlocklistName;
	}

	public void setBlocklistDescription(String newDescription) {
		this.description = newDescription;
	}

}
