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
	 * 
	 * 
	 * @return
	 */
	public ArrayList<Website> getActiveBlockLists() {
		ArrayList<Website> activeBlockLists = new ArrayList<>(Arrays.asList());
		for(BlockList blockList: blockListRepository.getBlockLists()) {
			if(blockList.getIsEnabled()) {
				activeBlockLists.addAll(activeBlockLists);
			}
		}
		return activeBlockLists;
	}
}
