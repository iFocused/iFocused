package application.entities;

public class Website {

	private String websiteName;
	private int websiteId;
	private int timesBlocked;

	public Website(String websiteName, int websiteId) {
		this.websiteName = websiteName;
		this.websiteId = websiteId;
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

	public void setWebsiteName(String newwebsiteName) {
		this.websiteName = newwebsiteName;
	}

	public void setWebsiteId(int newwebsiteId) {
		this.websiteId = newwebsiteId;
	}

	public void setTimesBlocked(int newTimesBlocked) {
		this.timesBlocked = newTimesBlocked;
	}

}
