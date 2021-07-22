package application;

import application.usecases.BlockListRepository;

public interface KillerStrategy {
	public void update(BlockListRepository blockListRepository);
	public void run();
}
