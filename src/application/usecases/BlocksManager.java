package application.usecases;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import application.entities.BlockList;
import application.entities.BlockSet;
import application.entities.Website;
import application.gateways.BlocksManagerGateway;

public class BlocksManager {
	private BlockSet blockSet;
	private BlockListRepository blockListRepository;
	private SessionRepository sessionRepository;
	private PomodoroRepository pomodoroRepository;
	private final BlocksManagerGateway blocksManagerGateway;
	private FileEditor fe;

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
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			fe = new FileEditor(new File("C:\\Windows\\System32\\drivers\\etc\\hosts"));
		} else {
			fe = new FileEditor(new File("/etc/hosts"));
		}

	}

	/**
	 * Syncs the user's block sets with external storage
	 */
	public void saveBlockSets() {
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
//	public boolean isOnBreak() {
//		for (Session session : sessionRepository.getSessions()) {
//			if (session.getSessionStatus() == SessionStatus.BREAK) {
//				return true;
//			}
//		}
//
//		for (PomodoroSession pSession : pomodoroRepository.getPomodoroSessions()) {
//			if (pSession.getSession().getSessionStatus() == SessionStatus.BREAK) {
//				return true;
//			}
//		}
//		return false;
//	}

	/**
	 * Returns true if and only if the block list with <id> was successfully blocked
	 * 
	 * @param id of the block list that is to be blocked
	 * @return whether the block list with the given id was blocked successfully or
	 *         not
	 */
	public boolean blockById(BlockList selectedBlockList) {
		int count = 0;
		for (Website website : selectedBlockList.getBlockedWebsites()) {
			if (this.blockSet.getBlockedSites().containsKey(website.getWebsiteId())) {
				count = this.blockSet.getBlockedSites().get(website.getWebsiteId());
				this.blockSet.getBlockedSites().replace(website.getWebsiteId(), count + 1);

			} else {
				System.out.println("website id: " + website.getWebsiteId());
				this.blockSet.getBlockedSites().put(website.getWebsiteId(), 1);
				fe.blockWebsite(website.getWebsiteName());
			}
		}
		return true;
	}

	/**
	 * Returns true if and only if the block list with <id> was successfully blocked
	 * 
	 * @param id of the block list that is to be blocked
	 * @return whether the block list with the given id was blocked successfully or
	 *         not
	 */
	public boolean unblockById(BlockList selectedBlockList) {
		int count = 0;
		for (Website website : selectedBlockList.getBlockedWebsites()) {
			if (this.blockSet.getBlockedSites().containsKey(website.getWebsiteId())) {

				/*
				 * PROBLEM: does not get here after de-serialization of the block list
				 * repository. need to serialize data in BlocksManager and make sure BlockSet
				 * also has data from the previous time the program ran
				 * 
				 */

				// only occurred once
				count = this.blockSet.getBlockedSites().get(website.getWebsiteId());
				if (this.blockSet.getBlockedSites().get(website.getWebsiteId()) > 1) {
					this.blockSet.getBlockedSites().replace(website.getWebsiteId(), count - 1);
				} else {
					// System.out.println(this.blockedSites.get(website.getWebsiteId()));
					fe.unblockWebsite(website.getWebsiteName());
					this.blockSet.getBlockedSites().remove(website.getWebsiteId());
				}

			}
		}

		return true;
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
