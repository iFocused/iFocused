package application.usecases;

import java.util.TimeZone;

import application.entities.User;
import application.gateways.UserManagerGateway;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * The User manager allows editing all of the user's fields
 */
public class UserManager {
	private User user;
	private final UserManagerGateway userManagerGateway;
	private final BooleanProperty isDataChanged;

	public UserManager(UserManagerGateway userManagerGateway) {
		this.userManagerGateway = userManagerGateway;
		this.isDataChanged = new SimpleBooleanProperty(false);
		this.userManagerGateway.populateUserData(this);
	}

	public void saveUserData(User user) {
		userManagerGateway.saveUserData(user);
	}

	/**
	 * Creates a user profile with a username and time zone
	 * 
	 * @param username The name the user would like to be identified as
	 * @param timeZone The time zone of where the user is currently located
	 */
	public void createUser(String username, TimeZone timeZone) {
		this.user = new User(username, timeZone);
	}

	/**
	 * Increases the user's points by one
	 */
	public void increastePoints() {
		this.user.addPoint();
	}

	/**
	 * Return reference to the user object
	 * 
	 * @return reference to the user object
	 */
	public User getUser() {
		return this.user;
	}

	public UserManagerGateway getUserManagerGateway() {
		return this.userManagerGateway;
	}

	public BooleanProperty getIsDataChangedProperty() {
		return this.isDataChanged;
	}
	
	public void SetIsUserManagerChangedProperty(boolean state) {
		if(state) this.userManagerGateway.saveUserData(user);
		this.isDataChanged.set(state);
	}

	/**
	 * A setter to set the user's object
	 * 
	 * @param user The user object to be set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Increments the number of pomodoro sessions the user has done
	 */
	public void incrementPomodoroCounter() {
		this.user.setPomodoroSessionsCount(this.user.getPomodoroSessionsCount() + 1);
		this.user.setPoints(this.user.getPoints() + 1);
	}
	
	/**
	 * Increments the number of tasks the user has completed
	 */
	public void incrementTasksCompleted() {
		this.user.setTasksCompleted(this.user.getTasksCompleted() + 1);
		this.user.setPoints(this.user.getPoints() + 1);
	}
}
