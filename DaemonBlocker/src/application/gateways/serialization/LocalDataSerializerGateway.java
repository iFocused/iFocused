package application.gateways.serialization;

import application.gateways.*;
import application.usecases.UseCasePool;

public class LocalDataSerializerGateway implements GatewayPool {
	private final BlockListGateway blockListGateway;
	private final PomodoroRepositoryGateway pomodoroRepositoryGateway;
	private final ProcessRepositoryGateway processRepositoryGateway;
	private final WebsiteRepositoryGateway websiteRepositoryGateway;
	private final SessionRepositoryGateway sessionRepositoryGateway;
	private final TODOListGateway todoListGateway;
	private final StatisticsRepositoryGateway statisticsRepositoryGateway;
	private final BlocksManagerGateway blocksManagerGateway;

	public LocalDataSerializerGateway() {
		blockListGateway = new SerBlockListGateway();
		pomodoroRepositoryGateway = new SerPomodoroRepositoryGateway();
		processRepositoryGateway = new SerProcessRepositoryGateway();
		websiteRepositoryGateway = new SerWebsiteRepositoryGateway();
		sessionRepositoryGateway = new SerSessionRepositoryGateway();
		todoListGateway = new SerTODOListGateway();
		statisticsRepositoryGateway = new SerStatisticsRepositoryGateway();
		blocksManagerGateway = new SerBlocksManagerGateway();

	}

	@Override
	public BlockListGateway getBlockListGateway() {
		// TODO Auto-generated method stub
		return blockListGateway;
	}

	@Override
	public SessionRepositoryGateway getSessionRepositoryGateway() {
		// TODO Auto-generated method stub
		return sessionRepositoryGateway;
	}

	@Override
	public PomodoroRepositoryGateway getPomodoroRepositoryGateway() {
		// TODO Auto-generated method stub
		return pomodoroRepositoryGateway;
	}

	@Override
	public WebsiteRepositoryGateway getWebsiteRepositoryGateway() {
		// TODO Auto-generated method stub
		return websiteRepositoryGateway;
	}

	@Override
	public ProcessRepositoryGateway getProcessRepositoryGateway() {
		// TODO Auto-generated method stub
		return processRepositoryGateway;
	}

	@Override
	public TODOListGateway getTODOListGateway() {
		// TODO Auto-generated method stub
		return todoListGateway;
	}

	@Override
	public StatisticsRepositoryGateway getStatisticsRepositoryGateway() {
		// TODO Auto-generated method stub
		return this.statisticsRepositoryGateway;
	}

	@Override
	public BlocksManagerGateway getBlocksManagerGateway() {
		// TODO Auto-generated method stub
		return this.blocksManagerGateway;
	}

	@Override
	public void refreshData(UseCasePool useCasePool) {
		this.blockListGateway.populateUserData(useCasePool.getBlockListRepository());
		this.blocksManagerGateway.populateBlockSet(useCasePool.getBlocksManager());
		// TODO rest
		
	}

}
