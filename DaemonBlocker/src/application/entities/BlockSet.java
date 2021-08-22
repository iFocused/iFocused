package application.entities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import application.usecases.FileEditor;

public class BlockSet {
	private Map<Integer, Integer> blockedSites;
	private Map<Integer, Integer> blockedApps;
	private FileEditor fe;

	public BlockSet() {
		blockedSites = new HashMap<Integer, Integer>();
		blockedApps = new HashMap<Integer, Integer>();
		// isHostFileFound(); /* this will be called instead of the line below once
		// implemented */
		fe = new FileEditor(new File("C:\\Windows\\System32\\drivers\\etc\\hosts"));
	}

	/**
	 * Returns true if and only if the block list with <id> was successfully blocked
	 * 
	 * @param id of the block list that is to be blocked
	 * @return whether the block list with the given id was blocked successfully or
	 *         not
	 */
	public boolean blockById(BlockList selectedBlockList) {
		int count = 0;
		for (Website website : selectedBlockList.getBlockedWebsites()) {
			if (this.blockedSites.containsKey(website.getWebsiteId())) {
				count = this.blockedSites.get(website.getWebsiteId());
				this.blockedSites.replace(website.getWebsiteId(), count + 1);

			} else {
				System.out.println("website id: " + website.getWebsiteId());
				this.blockedSites.put(website.getWebsiteId(), 1);
				fe.blockWebsite(website.getWebsiteName());
			}
		}
		return true;
	}

	/**
	 * Returns true if and only if the block list with <id> was successfully blocked
	 * 
	 * @param id of the block list that is to be blocked
	 * @return whether the block list with the given id was blocked successfully or
	 *         not
	 */
	public boolean unblockById(BlockList selectedBlockList) {
		int count = 0;
		for (Website website : selectedBlockList.getBlockedWebsites()) {
			if (this.blockedSites.containsKey(website.getWebsiteId())) {

				/*
				 * PROBLEM: does not get here after de-serialization of the block list
				 * repository. need to serialize data in BlocksManager and make sure BlockSet
				 * also has data from the previous time the program ran
				 * 
				 */

				// only occurred once
				count = this.blockedSites.get(website.getWebsiteId());
				if (this.blockedSites.get(website.getWebsiteId()) > 1) {
					this.blockedSites.replace(website.getWebsiteId(), count - 1);
				} else {
					System.out.println(this.blockedSites.get(website.getWebsiteId()));
					fe.unblockWebsite(website.getWebsiteName());
					this.blockedSites.remove(website.getWebsiteId());
				}

			}
		}

		return true;
	}

	/**
	 * @return the blockedSites
	 */
	public Map<Integer, Integer> getBlockedSites() {
		return blockedSites;
	}

	/**
	 * @param blockedSites the blockedSites to set
	 */
	public void setBlockedSites(Map<Integer, Integer> blockedSites) {
		this.blockedSites = blockedSites;
	}

	/**
	 * @return the blockedApps
	 */
	public Map<Integer, Integer> getBlockedApps() {
		return blockedApps;
	}

	/**
	 * @param blockedApps the blockedApps to set
	 */
	public void setBlockedApps(Map<Integer, Integer> blockedApps) {
		this.blockedApps = blockedApps;
	}

	/**
	 * @return the fe
	 */
	public FileEditor getFe() {
		return fe;
	}

	/**
	 * @param fe the fe to set
	 */
	public void setFe(FileEditor fe) {
		this.fe = fe;
	}

	/**
	 * Returns True iff the host file directory was found. Otherwise, false is
	 * returned.
	 * 
	 * @return whether the host file was found and the host file object was set
	 */
	private boolean isHostFileFound() {
		// TODO. This can be useful:
		// https://mkyong.com/java/search-directories-recursively-for-file-in-java/
		return true;
	}

}
