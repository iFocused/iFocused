package application.gateways;

import application.usecases.StatisticsRepository;

public interface StatisticsRepositoryGateway {
	/**
	 * Loads data from external storage into the account manager
	 *
	 * @param useCasePool A use case pool containing references to all repositories
	 *                    and managers
	 * 
	 * @return True iff loading the data was successful, otherwise false is returned
	 */
	boolean populateUserData(StatisticsRepository statisticsRepository);

	/**
	 * Syncs the user's data with external storage
	 * 
	 * @param statisticsRepository	The repository holding today's statistics of the user
	 * 
	 * @return True iff the the sync was successful, otherwise false is returned
	 */
	boolean saveUserData(StatisticsRepository statisticsRepository);
}
