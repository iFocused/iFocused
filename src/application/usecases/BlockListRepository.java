package application.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

import application.entities.BlockList;
import application.entities.Process;
import application.entities.Website;
import application.gateways.BlockListGateway;

/**
 * Repository containing reference to all the block lists the user has created
 */
public class BlockListRepository {
	private Map<Integer, BlockList> blockLists;
	private final BlockListGateway blockListGateway;
	private LocalDateTime timeSinceLastModification;
	private int currId;

	/**
	 * Constructor for the Block List repository
	 */
	public BlockListRepository(BlockListGateway blockListGateway) {
		this.currId = 0;
		this.blockLists = new HashedMap<>();
		this.updateTime();
		this.blockListGateway = blockListGateway;
		this.blockListGateway.populateUserData(this);
	}
	

	/**
	 * Updates the time since the last modification
	 */
	private void updateTime() {
		this.timeSinceLastModification = LocalDateTime.now();
	}

	public int createBlockList(String blocklistName, String description, boolean isEnabled, ArrayList<Website> blockedWebsites,
			ArrayList<Process> blockedProcesse) {
		this.updateBlockListById(currId, blocklistName, description, isEnabled, blockedWebsites, blockedProcesse);
		int tmpId = currId;
		this.currId++;
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
	public void updateBlockListById(int id, String blocklistName, String description, boolean isEnabled, ArrayList<Website> blockedWebsites,
			ArrayList<Process> blockedProcesses) {
		this.blockLists.put(id, new BlockList(blocklistName, description, id, isEnabled, blockedWebsites, blockedProcesses));
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
	public ArrayList<BlockList> getBlockListsAsList() {
		return (ArrayList<BlockList>) this.blockLists.values();
	}
	
	/**
	 * Returns a list containing all the block lists
	 * 
	 * @return a list containing all the block lists
	 */
	public Map<Integer, BlockList> getBlockLists() {
		return this.blockLists;
	}

	/**
	 * Sets the new dictionary of block lists
	 * 
	 * @param blockLists	The dictionary containing the ids and their corresponding block lists
	 */
	public void setBlockLists(Map<Integer, BlockList> blockLists) {
		this.blockLists = blockLists;
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
	public LocalDateTime getTimeSinceLastModification() {
		return this.timeSinceLastModification;
	}

	/**
	 * @param timeSinceLastModification the timeSinceLastModification to set
	 */
	public void setTimeSinceLastModification(LocalDateTime timeSinceLastModification) {
		this.timeSinceLastModification = timeSinceLastModification;
	}

	/**
	 * Getter for the currId
	 * 
	 * @return the currId
	 */
	public int getCurrId() {
		return currId;
	}

	/**
	 * Setter for the currId
	 * 
	 * @param currId the currId to set
	 */
	public void setCurrId(int currId) {
		this.currId = currId;
	}

	/**
	 * Getter for BlockListGateway
	 * 
	 * @return the blockListGateway
	 */
	public BlockListGateway getBlockListGateway() {
		return blockListGateway;
	}

}
