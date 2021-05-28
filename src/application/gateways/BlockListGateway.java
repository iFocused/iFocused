package application.gateways;

import java.time.LocalDateTime;
import java.util.Map;

import application.entities.BlockList;
import application.usecases.BlockListRepository;

public interface BlockListGateway {
	
	
	/**
	 * Loads data from external storage into the account manager
	 *
	 * @param useCasePool A use case pool containing references to all repositories
	 *                    and managers
	 * 
	 * @return True iff loading the data was successful, otherwise false is returned
	 */
	boolean populateUserData(BlockListRepository blockListRepository);

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
	boolean saveUserData(Map<Integer, BlockList> blockLists, LocalDateTime timeSinceLastModification,
			int currId);
}
