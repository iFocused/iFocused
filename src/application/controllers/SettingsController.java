package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.usecases.UseCasePool;
import application.views.FxmlViewBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class SettingsController implements Initializable {
	private UseCasePool useCasePool;
	private FxmlViewBuilder fxmlViewBuilder;

	@FXML
	private ComboBox<String> studyModeComb;

	@FXML
	private ComboBox<String> blockModeComb;

	@FXML
	private ComboBox<String> ColorThemeComb;

	@FXML
	private ComboBox<String> timezoneComb;

    @FXML
    private TextField usernameLbl;

    /**
     * {@inheritDoc}
     */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// add listeners for all combo boxes at some point
	}

	@FXML
	void onHandleUsernameChange(ActionEvent event) {
		this.useCasePool.getUserManager().getUser().setUsername(usernameLbl.getText());
		this.useCasePool.getUserManager().getUserManagerGateway().saveUserData(useCasePool.getUserManager().getUser(), false);
	}

	public SettingsController(UseCasePool useCasePool, FxmlViewBuilder fxmlViewBuilder) {
		this.useCasePool = useCasePool;
		this.fxmlViewBuilder = fxmlViewBuilder;
	}

}
