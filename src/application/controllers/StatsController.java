package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.usecases.UseCasePool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;

public class StatsController implements Initializable {

	private UseCasePool useCasePool;
	
	@FXML
	private TabPane statsTabPane;

	@FXML
	void onHandleCommonApps(ActionEvent event) {
		statsTabPane.getSelectionModel().select(0);
	}

	@FXML
	void onHandleBlockedProccesses(ActionEvent event) {
		statsTabPane.getSelectionModel().select(1);
	}

	@FXML
	void onHandleTimeStudying(ActionEvent event) {
		statsTabPane.getSelectionModel().select(2);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
	}
	
	public StatsController(UseCasePool useCasePool) {
		this.useCasePool = useCasePool;
	}

}
