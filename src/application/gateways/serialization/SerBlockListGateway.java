package application.gateways.serialization;

import java.time.LocalDateTime;
import java.util.Map;

import application.entities.BlockList;
import application.gateways.BlockListGateway;
import application.usecases.BlockListRepository;

public class SerBlockListGateway implements BlockListGateway {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean populateUserData(BlockListRepository blockListRepository) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean saveUserData(Map<Integer, BlockList> blockLists, LocalDateTime timeSinceLastModification,
			int currId) {

		// consider changing the parameters to just UseCasePool!

		return false;
	}
}
