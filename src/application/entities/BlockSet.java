package application.entities;

import java.util.HashMap;
import java.util.Map;

public class BlockSet {
	private Map<Integer, Integer> blockedSites;
	private Map<Integer, Integer> blockedApps;

	public BlockSet() {
		blockedSites = new HashMap<Integer, Integer>();
		blockedApps = new HashMap<Integer, Integer>();
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

}
