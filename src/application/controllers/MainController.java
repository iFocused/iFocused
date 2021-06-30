package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.entities.ViewMode;
import application.usecases.UseCasePool;
import application.views.FxmlViewBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {

	private final int NUM_TABS = 6;
	private UseCasePool useCasePool;
	private FxmlViewBuilder fxmlViewBuilder;

	@FXML
	private Button btnDashboard;

	@FXML
	private Button btnBlocklists;

	@FXML
	private Button btnSchedule;

	@FXML
	private Button btnPomodoro;

	@FXML
	private Button btnStats;

	@FXML
	private Button btnSettings;

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

	@FXML
	void onDashboardSelection(ActionEvent event) {
		resetBtnStyles();
		((Button) event.getSource()).getStyleClass().add("btn_selected");
		changeView(0);
	}

	@FXML
	void onBlockListSelection(ActionEvent event) {
		resetBtnStyles();
		((Button) event.getSource()).getStyleClass().add("btn_selected");
		changeView(1);
	}

	@FXML
	void onScheduleSelection(ActionEvent event) {
		resetBtnStyles();
		((Button) event.getSource()).getStyleClass().add("btn_selected");
		changeView(2);
	}

	@FXML
	void onTimerSelection(ActionEvent event) {
		resetBtnStyles();
		((Button) event.getSource()).getStyleClass().add("btn_selected");
		changeView(3);
	}

	@FXML
	void onStatsSelection(ActionEvent event) {
		resetBtnStyles();
		((Button) event.getSource()).getStyleClass().add("btn_selected");
		changeView(4);
	}

	@FXML
	void onSettingsSelection(ActionEvent event) {
		resetBtnStyles();
		((Button) event.getSource()).getStyleClass().add("btn_selected");
		changeView(5);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		populateTabs();
		btnDashboard.getStyleClass().add("btn_selected");

	}

	public MainController(UseCasePool useCasePool, FxmlViewBuilder fxmlBuilder) {
		this.useCasePool = useCasePool;
		this.fxmlViewBuilder = fxmlBuilder;
	}

	private void resetBtnStyles() {
		btnDashboard.getStyleClass().remove("btn_selected");
		btnBlocklists.getStyleClass().remove("btn_selected");
		btnSchedule.getStyleClass().remove("btn_selected");
		btnPomodoro.getStyleClass().remove("btn_selected");
		btnStats.getStyleClass().remove("btn_selected");
		btnSettings.getStyleClass().remove("btn_selected");
	}

	private void setTab(ViewMode whichView) {
		AnchorPane currPane;
		try {
			currPane = (AnchorPane) this.fxmlViewBuilder.getView(whichView);
			if (currPane != null) {
				switch (whichView) {
				case DASHBOARD:
					dashboardPane.setCenter(currPane);
					break;

				case BLOCKLISTS:
					blocklistPane.setCenter(currPane);
					break;

				case SCHEDULE:
					schedulePane.setCenter(currPane);
					break;

				case POMODORO:
					timerPane.setCenter(currPane);
					break;

				case STATS:
					statsPane.setCenter(currPane);
					break;

				case SETTINGS:
					settingsPane.setCenter(currPane);
					break;

				default: // unexisting tab
					return;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void populateTabs() {
		setTab(ViewMode.DASHBOARD);
		setTab(ViewMode.BLOCKLISTS);
		setTab(ViewMode.SCHEDULE);
		setTab(ViewMode.POMODORO);
		setTab(ViewMode.STATS);
		setTab(ViewMode.SETTINGS);
	}

	private void changeView(int tabId) {
		tabsPane.getSelectionModel().select(tabId);
	}

}
