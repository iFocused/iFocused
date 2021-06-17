package application.usecases;

import java.util.ArrayList;
import java.util.Arrays;

import application.entities.BlockList;
import application.entities.BlockSet;
import application.entities.PomodoroSession;
import application.entities.Session;
import application.entities.SessionStatus;

public class BlocksManager {
	private BlockSet blockSet;
	private BlockListRepository blockListRepository;
	private WebsiteRepository websiteRepository;
	private ProcessRepository processRepository;
	private SessionRepository sessionRepository;
	private PomodoroRepository pomodoroRepository;

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
			WebsiteRepository websiteRepository, ProcessRepository processRepository) {
		this.blockSet = blockSet;
		this.blockListRepository = blockListRepository;
		this.sessionRepository = sessionRepository;
		this.pomodoroRepository = pomodoroRepository;
		this.websiteRepository = websiteRepository;
		this.processRepository = processRepository;
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
	 * Returns the set containing the websites and processes that need to be blocked
	 * 
	 * @return the set containing the websites and processes that need to be blocked
	 *         right now
	 */
	public BlockSet getBlockedSet() {
		return this.blockSet;
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

}
