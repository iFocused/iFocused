package application.gateways;

import application.usecases.BlocksManager;

public interface BlocksManagerGateway {
	/**
	 * Loads block set from external storage into the Blocks Manager
	 *
	 * @param blocksManager The manager in charge of the active blocks
	 * 
	 * @return True iff loading the block set was successful, otherwise false is
	 *         returned
	 */
	boolean populateBlockSet(BlocksManager blocksManager);

	/**
	 * Syncs the user's data with external storage
	 * 
	 * @param blocksManager The manager in charge of the active blocks
	 * @return True iff the the sync was successful, otherwise false is returned
	 */
	boolean saveBlockSet(BlocksManager blocksManager);
}
