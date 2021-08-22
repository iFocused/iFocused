package application;

import application.usecases.BlockListRepository;

public class OSFactory {
	
	public KillerStrategy getKiller(BlockListRepository blockListRepository) {
		
		if (System.getProperty("os.name").toLowerCase().contains("windows") ) {
			return new WindowsKillerController(blockListRepository);
		}
		
		return new MacKillerController(blockListRepository);
		
	}
}
