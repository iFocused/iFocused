package application.gateways;

import application.usecases.*;

public interface DataSerializerGateway {
	
	/**
	 * Return whether the user is a new user
	 * 
	 * @return True iff the user is a new user, otherwise false.
	 */
	boolean isNewUser();
	
	/**
	 * Loads data from external storage into the account manager
	 *
	 * @param useCasePool A use case pool containing references to all repositories
	 *                    and managers
	 * 
	 * @return True iff loading the data was successful, otherwise false is returned
	 */
	boolean populateUserData(UseCasePool useCasePool);

	/**
	 * Syncs the user's data with external storage
	 * 
	 * @param sessionRepository		The session repository of the user
	 * @param pomodoroRepository    The pomodoro session repository of the user
	 * @param websiteRepository		The website repository of the user
	 * @param processRepository		The process repository of the user
	 * @param todoList				The todo list the user has made
	 * @param statisticsRepository	The observed stats of the user
	 * @param userManager			The user manager in charge of manipulating the user
	 * 
	 * @return True iff the the sync was successful, otherwise false is returned
	 */
	boolean saveUserData(SessionRepository sessionRepository, PomodoroRepository pomodoroRepository,
			WebsiteRepository websiteRepository, ProcessRepository processRepository, TODOList todoList,
			StatisticsRepository statisticsRepository, UserManager userManager);
}
