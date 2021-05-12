package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.FxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {

	final int NUM_TABS = 6;

	@FXML
	private TabPane tabsPane;

	@FXML
	private BorderPane dashboardPane;

	@FXML
	private BorderPane blocklistPane;

	@FXML
	private BorderPane schedulePane;

	@FXML
	private BorderPane timerPane;

	@FXML
	private BorderPane statsPane;

	@FXML
	private BorderPane settingsPane;

	private void setTab(int tabId) {
		FxmlLoader fxmlLoader = new FxmlLoader(tabId);
		AnchorPane currPane = (AnchorPane) fxmlLoader.getTab();

		if (currPane != null) {
			switch (tabId) {
			case 0:
				dashboardPane.setCenter(currPane);
				break;

			case 1:
				blocklistPane.setCenter(currPane);
				break;

			case 2:
				schedulePane.setCenter(currPane);
				break;

			case 3:
				timerPane.setCenter(currPane);
				break;

			case 4:
				statsPane.setCenter(currPane);
				break;

			case 5:
				settingsPane.setCenter(currPane);
				break;
			}
		}
	}

	private void populateTabs() {
		for (int i = 0; i < NUM_TABS; i++) {
			setTab(i);
		}
	}

	private void changeView(int tabId) {
		tabsPane.getSelectionModel().select(tabId);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		populateTabs();
	}

	@FXML
	void onDashboardSelection(ActionEvent event) {
		changeView(0);
	}

	@FXML
	void onBlockListSelection(ActionEvent event) {
		changeView(1);
	}

	@FXML
	void onScheduleSelection(ActionEvent event) {
		changeView(2);
	}

	@FXML
	void onTimerSelection(ActionEvent event) {
		changeView(3);
	}

	@FXML
	void onStatsSelection(ActionEvent event) {
		changeView(4);
	}

	@FXML
	void onSettingsSelection(ActionEvent event) {
		changeView(5);
	}
}
