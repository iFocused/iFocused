package application.usecases;

import java.util.ArrayList;
import java.util.Arrays;

import application.entities.BlockList;
import application.entities.BlockSet;
import application.entities.PomodoroSession;
import application.entities.Session;
import application.entities.SessionStatus;
import application.gateways.BlocksManagerGateway;

public class BlocksManager {
	private BlockSet blockSet;
	private BlockListRepository blockListRepository;
	private SessionRepository sessionRepository;
	private PomodoroRepository pomodoroRepository;
	private final BlocksManagerGateway blocksManagerGateway;

	public BlocksManager() {
		this.blocksManagerGateway = null;

	}

	/**
	 * Constructor for the Blocks Manager
	 * 
	 * @param blockSet
	 * @param blockListRepository
	 * @param sessionRepository
	 * @param pomodoroRepository
	 */
	public BlocksManager(BlockSet blockSet, BlockListRepository blockListRepository,
			SessionRepository sessionRepository, PomodoroRepository pomodoroRepository,
			BlocksManagerGateway blocksManagerGateway) {
		this.blockSet = blockSet;
		this.blockListRepository = blockListRepository;
		this.sessionRepository = sessionRepository;
		this.pomodoroRepository = pomodoroRepository;
		this.blocksManagerGateway = blocksManagerGateway;
		this.blocksManagerGateway.populateBlockSet(this);
		System.out.println(this.blockSet.getBlockedSites());
	}
	
	/**
	 * Syncs the user's block sets with external storage
	 */
	public void saveBlockSets() {
		System.out.println(this.getBlockSet().getBlockedSites());
		this.blocksManagerGateway.saveBlockSet(this);
	}

	/**
	 * Returns a list containing all the active block lists
	 * 
	 * @return a list containing all the active block lists
	 */
	public ArrayList<BlockList> getActiveBlockLists() {
		ArrayList<BlockList> activeBlockLists = new ArrayList<>(Arrays.asList());
		for (BlockList blockList : blockListRepository.getBlockListsAsList()) {
			if (blockList.getIsEnabled()) {
				activeBlockLists.addAll(activeBlockLists);
			}
		}
		return activeBlockLists;
	}

	/**
	 * Return true iff any of the sessions are in break mode, otherwise false is
	 * returned.
	 * 
	 * @return true iff any of the sessions are in break mode
	 */
	public boolean isOnBreak() {
		for (Session session : sessionRepository.getSessions()) {
			if (session.getSessionStatus() == SessionStatus.BREAK) {
				return true;
			}
		}

		for (PomodoroSession pSession : pomodoroRepository.getPomodoroSessions()) {
			if (pSession.getSession().getSessionStatus() == SessionStatus.BREAK) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns true if and only if the block list with <id> was successfully blocked
	 * 
	 * @param id of the block list that is to be blocked
	 * @return whether the block list with the given id was blocked successfully or
	 *         not
	 */
	public boolean blockById(BlockList selectedBlockList) {
		return blockSet.blockById(selectedBlockList);
	}

	/**
	 * Returns true if and only if the block list with <id> was successfully blocked
	 * 
	 * @param id of the block list that is to be blocked
	 * @return whether the block list with the given id was blocked successfully or
	 *         not
	 */
	public boolean unblockById(BlockList selectedBlockList) {
		return blockSet.unblockById(selectedBlockList);
	}

	/**
	 * Returns the set containing the websites and processes that need to be blocked
	 * 
	 * @return the set containing the websites and processes that need to be blocked
	 *         right now
	 */
	public BlockSet getBlockSet() {
		return blockSet;
	}

	/**
	 * A setter for the set containing the websites and processes that need to be
	 * blocked
	 * 
	 */
	public void setBlockSet(BlockSet blockSet) {
		this.blockSet = blockSet;
	}

	/**
	 * @return the blockListRepository
	 */
	public BlockListRepository getBlockListRepository() {
		return blockListRepository;
	}

	/**
	 * @param blockListRepository the blockListRepository to set
	 */
	public void setBlockListRepository(BlockListRepository blockListRepository) {
		this.blockListRepository = blockListRepository;
	}

	/**
	 * @return the sessionRepository
	 */
	public SessionRepository getSessionRepository() {
		return sessionRepository;
	}

	/**
	 * @param sessionRepository the sessionRepository to set
	 */
	public void setSessionRepository(SessionRepository sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	/**
	 * @return the pomodoroRepository
	 */
	public PomodoroRepository getPomodoroRepository() {
		return pomodoroRepository;
	}

	/**
	 * @param pomodoroRepository the pomodoroRepository to set
	 */
	public void setPomodoroRepository(PomodoroRepository pomodoroRepository) {
		this.pomodoroRepository = pomodoroRepository;
	}

	/**
	 * @return the blocksManagerGateway
	 */
	public BlocksManagerGateway getBlocksManagerGateway() {
		return blocksManagerGateway;
	}

}
