package application.usecases;

import java.io.File;
import java.util.*;

import application.entities.*;
import application.entities.Process;

public class BlocksManager {
	private BlockSet blockSet;
	private Map<Integer, Integer> blockedSites;
	private Map<Integer, Integer> blockedApps;
	private BlockListRepository blockListRepository;
	private WebsiteRepository websiteRepository;
	private ProcessRepository processRepository;
	private SessionRepository sessionRepository;
	private PomodoroRepository pomodoroRepository;
	private FileEditor fe;

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
		blockedSites = new HashMap<Integer, Integer>();
		blockedApps = new HashMap<Integer, Integer>();
		// foundHostFile(); /* this will be called instead of the line below once implemented */ 
		fe = new FileEditor(new File("C:\\Windows\\System32\\drivers\\etc\\hosts"));
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
		int count = 0;
		for (Website website : selectedBlockList.getBlockedWebsites()) {
			if (this.blockedSites.containsKey(website.getWebsiteId())) {
				count = this.blockedSites.get(website.getWebsiteId());
				this.blockedSites.replace(website.getWebsiteId(), count + 1);

			} else {
				System.out.println("website id: " + website.getWebsiteId());
				this.blockedSites.put(website.getWebsiteId(), 1);
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
			if (this.blockedSites.containsKey(website.getWebsiteId())) {
				// only occurred once
				count = this.blockedSites.get(website.getWebsiteId());
				if (this.blockedSites.get(website.getWebsiteId()) > 1) {
					this.blockedSites.replace(website.getWebsiteId(), count - 1);
				} else {
					fe.unblockWebsite(website.getWebsiteName());
					this.blockedSites.remove(website.getWebsiteId());
				}

			}
		}

		return true;
	}
	
	
	/**
	 * Returns True iff the host file directory was found. Otherwise,
	 * false is returned.
	 * 
	 * @return whether the host file was found and the host file object was set
	 */
	private boolean foundHostFile() {
		// TODO. This can be useful: https://mkyong.com/java/search-directories-recursively-for-file-in-java/
		return true;
	}

}
