package application.usecases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import application.entities.Website;

public class WebsiteRepository {
	private Map<Integer, Website> websites;
	private int end;

	/**
	 * Constructor for the Website repository
	 */
	public WebsiteRepository() {
		this.end = 0;
		this.websites = new HashMap<>();
	}

	/**
	 * Adds a new Website.
	 * 
	 * @param Website The Website to be added
	 */
	public int createWebsite(String websiteName) {
		this.websites.put(this.end, new Website(websiteName, this.end));
		int tmpId = this.end;
		this.end++;
		return tmpId;
	}

	/**
	 * Removes a Website.
	 * 
	 * @param Website The Website to be removed
	 */
	public void removeWebsiteByName(String websiteName) {
		boolean found = false;
		int prevKey = 0;

		// finding the key corresponding to website
		for (int key : this.websites.keySet()) {
			Website tmpWebsite = this.websites.get(key);
			if (!found) {
				if (tmpWebsite.getWebsiteName().equals(websiteName)) {
					found = true;
					this.websites.remove(key);
					prevKey = key;
				}
			} else {
				// shifting all the rest of the ids down by 1 to maintain order
				this.websites.remove(key);
				this.websites.put(prevKey, tmpWebsite);
				prevKey = key;
			}

		}

		this.end = prevKey;
	}

	/**
	 * Return a list of all the Websites in this repository
	 * 
	 * @return a list of all the Websites
	 */
	public ArrayList<Website> getWebsites() {
		ArrayList<Website> websites = new ArrayList<>();
		for (Website website : this.websites.values()) {
			websites.add(website);
		}
		return websites;
	}

	/**
	 * Return a Website corresponding to the given <id>.
	 * 
	 * @param id The identifier for the sought after website
	 * @return a list of Websites up to a certain time in ascending order
	 */
	public Website getNWebsiteById(int id) {
		return this.websites.get(id);
	}

	/**
	 * Returns the highest identifier number in the list of websites
	 * 
	 * @return the highest identifier number in the list of websites
	 */
	public int getHighestId() {
		return this.end;
	}

}
