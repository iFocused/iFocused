package application.gateways;

import application.usecases.SessionRepository;

public interface SessionRepositoryGateway {
	
	/**
	 * Loads session repository from external storage into the Blocks Manager
	 *
	 * @param sessionRepository The manager in charge of the active blocks
	 * 
	 * @return True iff loading the session repository was successful, otherwise false is
	 *         returned
	 */
	boolean populateUserData(SessionRepository sessionRepository);

	/**
	 * Syncs the user's data with external storage
	 * 
	 * @param sessionRepository The repository in charge of the sessions
	 * @return True iff the the sync was successful, otherwise false is returned
	 */
	boolean saveUserData(SessionRepository sessionRepository);
}
