package application.entities;

import java.net.MalformedURLException;
import java.net.URL;

public class Website {

	private String websiteName;
	private URL websiteURL;
	private int websiteId;
	private int timesBlocked;

	public Website(String websiteName, String websiteURL, int websiteId) throws MalformedURLException {
		this.websiteName = websiteName;
		this.websiteId = websiteId;
		this.websiteURL = new URL(websiteURL);
		this.timesBlocked = 0;
	}

	public String getWebsiteName() {
		return websiteName;
	}
	
	public int getWebsiteId() {
		return websiteId;
	}

	public int getTimesBlocked() {
		return timesBlocked;
	}
	
	public URL getWebsiteURL() {
		return this.websiteURL;
	}

	public void setWebsiteName(String newwebsiteName) {
		this.websiteName = newwebsiteName;
	}

	public void setWebsiteId(int newwebsiteId) {
		this.websiteId = newwebsiteId;
	}

	public void setTimesBlocked(int newTimesBlocked) {
		this.timesBlocked = newTimesBlocked;
	}
	
	public void setWebsiteURL(URL newURL) {
		this.websiteURL = newURL;
	}

}
