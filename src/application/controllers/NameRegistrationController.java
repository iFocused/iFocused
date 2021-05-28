package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TimeZone;

import application.usecases.UseCasePool;
import application.usecases.UserManager;
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

	private String username;

	private TimeZone timeZone;

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
		this.userManager.createUser(this.username, this.timeZone);
		Stage stage = (Stage) selectionBtn.getScene().getWindow();
		stage.close();
	}

	/**
	 * Constructor for NameRegirationController
	 * 
	 * @param useCasePool	The use case pool containing references to all the repositories and managers
	 */
	public NameRegistrationController(UseCasePool useCasePool) {
		this.useCasePool = useCasePool;
		this.userManager = useCasePool.getUserManager();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		String[] timeZones = TimeZone.getAvailableIDs();
		combTimeZone.getItems().addAll(timeZones);
	}

	/**
	 * Sets the username of the user
	 * 
	 * @param username	The username the user would like to be identified as
	 */
	public void setUsername(String username) {
		this.username = username;
		switchRight();
	}

	/**
	 * Sets the selected time zone of the user
	 * 
	 * @param timeZone	The time zone the user is in 
	 */
	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
		selectionBtn.setDisable(false);
		switchLeft();

	}

	/** 
	 * Switch to time zone selection window
	 */
	private void switchRight() {
		rightArrowBtn.setDisable(true);
		leftArrowBtn.setDisable(false);
		usernameLabel.setVisible(false);
		combTimeZone.setVisible(true);
	}

	/**
	 * Switch to username selection window
	 */
	private void switchLeft() {
		leftArrowBtn.setDisable(true);
		rightArrowBtn.setDisable(false);
		combTimeZone.setVisible(false);
		usernameLabel.setVisible(true);
	}

}
