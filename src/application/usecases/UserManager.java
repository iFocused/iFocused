package application.usecases;

import java.util.TimeZone;

import application.entities.User;
import application.gateways.UserManagerGateway;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * The User manager allows editing all of the user's fields
 */
public class UserManager {
	private User user;
	private final UserManagerGateway userManagerGateway;
	private final ObjectProperty<UserManager> userManagerObserved;

	public UserManager(UserManagerGateway userManagerGateway) {
		this.userManagerGateway = userManagerGateway;
		this.userManagerObserved = new SimpleObjectProperty<>();
	}

	public void saveUserData(User user, boolean isNewUser) {
		userManagerGateway.saveUserData(user, isNewUser);
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

	public void updateUserManager(UserManager updatedUserManager) {
		// ensuring that updatedUserManager will have a different hash code to trigger
		// listeners
		UserManager tmp = new UserManager(updatedUserManager.getUserManagerGateway());
		tmp.setUser(updatedUserManager.getUser());
		this.userManagerObserved.set(tmp);

	}

	public ObjectProperty<UserManager> getUserManagerProperty() {
		return this.userManagerObserved;
	}

	public UserManagerGateway getUserManagerGateway() {
		return this.userManagerGateway;
	}

	/**
	 * A setter to set the user's object
	 * 
	 * @param user The user object to be set
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
