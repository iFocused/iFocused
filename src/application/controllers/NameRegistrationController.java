package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TimeZone;

import application.usecases.UseCasePool;
import application.usecases.UserManager;
import application.views.FxmlViewBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NameRegistrationController implements Initializable {

	/**
	 * Java components
	 */

	private final UseCasePool useCasePool;

	private final UserManager userManager;
	
	private FxmlViewBuilder fxmlViewBuilder;

	/*
	 * FXML components
	 */

	@FXML
	private Text label;

	@FXML
	private TextField usernameLabel;

	@FXML
	private ComboBox<String> combTimeZone;

	@FXML
	private Button selectionBtn;

	@FXML
	private Button rightArrowBtn;

	@FXML
	private Button leftArrowBtn;

	/**
	 * Switches focus onto the username selection window
	 * 
	 * @param event The click event of the button
	 */
	@FXML
	void onClickArrowLeft(ActionEvent event) {
		switchLeft();
	}

	/**
	 * Switches focus onto the time zone selection window
	 * 
	 * @param event The click event of the button
	 */
	@FXML
	void onClickArrowRight(ActionEvent event) {
		switchRight();
	}

	/**
	 * Creates a user profile and closes the window
	 * 
	 * @param event The click event of the button
	 */
	@FXML
	void onHandleProfileCreation(ActionEvent event) {
		this.userManager.createUser(usernameLabel.getText(), TimeZone.getTimeZone(combTimeZone.getSelectionModel().getSelectedItem()));
		Stage stage = (Stage) selectionBtn.getScene().getWindow();
		stage.close();
	}

	/**
	 * Constructor for NameRegirationController
	 * 
	 * @param useCasePool The use case pool containing references to all the
	 *                    repositories and managers
	 */
	public NameRegistrationController(UseCasePool useCasePool, FxmlViewBuilder fxmlViewBuilder) {
		this.useCasePool = useCasePool;
		this.userManager = useCasePool.getUserManager();
		this.fxmlViewBuilder = fxmlViewBuilder;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		String[] timeZones = TimeZone.getAvailableIDs();
		combTimeZone.getItems().addAll(timeZones);

		usernameLabel.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty()) {
				rightArrowBtn.setDisable(true);
			} else {
				rightArrowBtn.setDisable(false);
			}
		});

		combTimeZone.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
			selectionBtn.setDisable(false);
		});
	}
	
	/**
	 * Switch to time zone selection window
	 */
	private void switchRight() {
		rightArrowBtn.setDisable(true);
		leftArrowBtn.setDisable(false);
		usernameLabel.setVisible(false);
		combTimeZone.setVisible(true);
		label.setText("Timezone Selection");

	}

	/**
	 * Switch to username selection window
	 */
	private void switchLeft() {
		leftArrowBtn.setDisable(true);
		rightArrowBtn.setDisable(false);
		combTimeZone.setVisible(false);
		usernameLabel.setVisible(true);
		label.setText("Username Selection");
	}

}
