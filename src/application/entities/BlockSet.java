package application.entities;

import java.util.Set;

public class BlockSet {
	
	/**
	 * DISCUSS: 
	 */
	private Set<Integer> blockListIds;

	public void addBlockListId(int blockListId) {
		this.blockListIds.add(blockListId);
	}

	public void removeBlockListId(int blockListId) {
		this.blockListIds.remove(blockListId);
	}
}
