package application.usecases;

import java.util.ArrayList;
import java.util.Arrays;

import application.entities.*;

public class BlocksManager {
	private BlockSet blockSet;
	private BlockListRepository blockListRepository;
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
			SessionRepository sessionRepository, PomodoroRepository pomodoroRepository) {
		this.blockSet = blockSet;
		this.blockListRepository = blockListRepository;
		this.sessionRepository = sessionRepository;
		this.pomodoroRepository = pomodoroRepository;
	}

	/**
	 * Returns a list containing all the active block lists
	 * 
	 * @return a list containing all the active block lists
	 */
	public ArrayList<BlockList> getActiveBlockLists() {
		ArrayList<BlockList> activeBlockLists = new ArrayList<>(Arrays.asList());
		for(BlockList blockList: blockListRepository.getBlockLists()) {
			if(blockList.getIsEnabled()) {
				activeBlockLists.addAll(activeBlockLists);
			}
		}
		return activeBlockLists;
	}
	
	/**
	 * Returns the set containing the websites and processes that need to be blocked
	 * 
	 * @return the set containing the websites and processes that need to be blocked right now
	 */
	public BlockSet getBlockedSet() {
		return this.blockSet;
	}
	
	/**
	 * Return true iff any of the sessions are in break mode, otherwise
	 * false is returned.
	 * 
	 * @return true iff any of the sessions are in break mode
	 */
	public boolean isOnBreak() {
		for(Session session : sessionRepository.getSessions()) {
			if(session.getSessionStatus() == SessionStatus.BREAK) {
				return true;
			}
		}
		
		for(PomodoroSession pSession : pomodoroRepository.getPomodoroSessions()) {
			if(pSession.getSession().getSessionStatus() == SessionStatus.BREAK) {
				return true;
			}
		}
		return false;
	}
}
