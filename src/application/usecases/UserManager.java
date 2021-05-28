package application.usecases;

import java.io.Serializable;
import java.util.TimeZone;

import application.entities.User;
import application.gateways.DataSerializerGateway;

/**
 * The User manager allows editing all of the user's fields 
 */
public class UserManager implements Serializable {
	private User user;
	private DataSerializerGateway userGateway;
	
	public UserManager(User user) {
		this.user = user;
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
}
