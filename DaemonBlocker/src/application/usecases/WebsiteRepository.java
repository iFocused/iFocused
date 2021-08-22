package application.usecases;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import application.entities.Website;
import application.gateways.WebsiteRepositoryGateway;

public class WebsiteRepository {
	private Map<Integer, Website> websites;
	private final WebsiteRepositoryGateway websiteRepositoryGateway;
	private int currId;

	/**
	 * Constructor for the Website repository
	 */
	public WebsiteRepository(WebsiteRepositoryGateway websiteRepositoryGateway) {
		this.currId = 0;
		this.websites = new HashMap<>();
		this.websiteRepositoryGateway = websiteRepositoryGateway;
	}

	/**
	 * Adds a new Website.
	 * 
	 * @param Website The Website to be added
	 */
	public int createWebsite(String websiteName, String websiteURL) {
		int tmpId = this.currId;
		int currSiteId;
		try {
			// check if a website with the given website name already exists
			if ((currSiteId = checkWebsiteExisting(websiteName)) == -1) {
				this.websites.put(this.currId, new Website(websiteName, websiteURL, this.currId));
				this.currId++;
			} else {
				return currSiteId;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return tmpId;
	}

	private int checkWebsiteExisting(String websiteName) {
		for (int websiteId : this.websites.keySet()) {
			if (this.websites.get(websiteId).getWebsiteName().equals(websiteName)) {
				return websiteId;
			}
		}
		return -1;
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

		this.currId = prevKey;
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
	 * @return a list of Websites up to a certain time in asccurrIding order
	 */
	public Website getWebsiteById(int id) {
		return this.websites.get(id);
	}

	/**
	 * Returns the highest identifier number in the list of websites
	 * 
	 * @return the highest identifier number in the list of websites
	 */
	public int getHighestId() {
		return this.currId;
	}

}
