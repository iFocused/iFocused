package application.usecases;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

import application.entities.BlockList;
import application.entities.Process;
import application.entities.Website;


/**
 * Repository containing reference to all the block lists the user has created
 */
public class BlockListRepository {
	private final Map<Integer, BlockList> blockLists;
	private String timeSinceLastModification; 
	private int start;

	/**
	 * Constructor for the Block List repository
	 */
	public BlockListRepository() {
		this.start = 0;
		this.blockLists = new HashedMap<>();
		this.updateTime();
	}
	
	/**
	 * Updates the time since the last modification
	 */
	private void updateTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.timeSinceLastModification = formatter.format(new Date());
	}

	public int saveBlockList(int id, boolean isEnabled, ArrayList<Website> blockedWebsites, 
			ArrayList<Process> blockedProcesse) {
		this.updateBlockListById(start, isEnabled, blockedWebsites, blockedProcesse);
		int tmpId = start;
		this.start++;
		return tmpId;
	}

	/**
	 * Returns the block list with the given identifier <id>.
	 * 
	 * @param id Identifier of the block list
	 * @return the block list with the given identifier
	 */
	public BlockList getBlockListById(int id) {
		return this.blockLists.get(id);
	}

	/**
	 * Adds a new block list with a specified identifier <id>.
	 * 
	 * @param id        Identifier of the block list to be added
	 * @param blockList The block list to be added
	 */
	public void updateBlockListById(int id, boolean isEnabled, ArrayList<Website> blockedWebsites, 
			ArrayList<Process> blockedProcesses) {
		this.blockLists.put(id, new BlockList(id, isEnabled, blockedWebsites, blockedProcesses));
		this.updateTime();
	}

	/**
	 * Removes a block list with a specified identifier <id>.
	 * 
	 * @param id        Identifier of the block list to be added
	 * @param blockList The block list to be removed
	 */
	public void removeBlockList(int id, BlockList blockList) {
		this.blockLists.remove(id, blockList);
		this.updateTime();
	}
	
	/**
	 * Returns a list containing all the block lists
	 *  
	 * @return a list containing all the block lists
	 */
	public ArrayList<BlockList> getBlockLists() {
		return (ArrayList<BlockList>) this.blockLists.values();
	}

	/**
	 * Return a list of block lists with identifiers up to <n> in ascending order.
	 * 
	 * @param n The identifier to be counted up to
	 * @return a list of block lists with their ids in ascending order
	 */
	public ArrayList<BlockList> getNBlockListsAsc(int n) {
		ArrayList<BlockList> blockLists = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			blockLists.add(blockLists.get(i));
		}
		return blockLists;
	}

	/**
	 * Return a list of block lists with identifiers up to <n> in descending order.
	 * 
	 * @param n The identifier to be counted up to
	 * @return a list of block lists with their ids in descending order
	 */
	public ArrayList<BlockList> getNBlockListsDesc(int n) {
		ArrayList<BlockList> blockLists = new ArrayList<>();
		for (int i = n; i < 0; i--) {
			blockLists.add(blockLists.get(i));
		}
		return blockLists;
	}
	
	/**
	 * Returns the time since the last modification of a block list
	 * 
	 * @return the exact time since the last modification done to a block list
	 */
	public String getTimeSinceLastModification() {
		return this.timeSinceLastModification;
	}

}
