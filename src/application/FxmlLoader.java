package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class FxmlLoader {
	private int tabId;

	public FxmlLoader(int tabId) {
		this.tabId = tabId;
	}

	/**
	 * Returns the pane corresponding to <tabId>. If an invalid id
	 * was given, returning null.
	 * 
	 * @return pane corresponding to <tabId>. If an invalid id was
	 * given, null is returned.
	 */
	public Parent getTab() {
		try {
			switch (this.tabId) {
			case 0:
				return FXMLLoader.load(getClass().getResource("views/dashboard.fxml"));

			case 1:
				return FXMLLoader.load(getClass().getResource("views/blocklists.fxml"));

			case 2:
				return FXMLLoader.load(getClass().getResource("views/schedule.fxml"));

			case 3:
				return FXMLLoader.load(getClass().getResource("views/timer.fxml"));

			case 4:
				return FXMLLoader.load(getClass().getResource("views/stats.fxml"));

			case 5:
				return FXMLLoader.load(getClass().getResource("views/settings.fxml"));

			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setTabId(int newTabId) {
		this.tabId = newTabId;
	}

}
