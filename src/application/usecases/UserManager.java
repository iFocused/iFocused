package application.usecases;

import java.io.Serializable;
import java.util.TimeZone;

import application.entities.User;
import application.gateways.DataSerializerGateway;
import application.gateways.UserManagerGateway;

/**
 * The User manager allows editing all of the user's fields 
 */
public class UserManager implements Serializable {
	private User user;
	private final UserManagerGateway userManagerGateway;
	
	public UserManager(UserManagerGateway userManagerGateway) {
		this.userManagerGateway = userManagerGateway;
	}
	
	public void saveUserData(User user, boolean isNewUser) {
		userManagerGateway.saveUserData(user, isNewUser);
	}
	
	/**
	 * Creates a user profile with a username and time zone
	 * 
	 * @param username	The name the user would like to be identified as
	 * @param timeZone	The time zone of where the user is currently located
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
	
	/**
	 * A setter to set the user's object
	 * 
	 * @param user	The user object to be set
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
