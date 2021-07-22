package application.gateways;

import application.usecases.UseCasePool;

public interface GatewayPool {

	BlockListGateway getBlockListGateway();

	SessionRepositoryGateway getSessionRepositoryGateway();

	PomodoroRepositoryGateway getPomodoroRepositoryGateway();

	WebsiteRepositoryGateway getWebsiteRepositoryGateway();

	ProcessRepositoryGateway getProcessRepositoryGateway();

	TODOListGateway getTODOListGateway();

	StatisticsRepositoryGateway getStatisticsRepositoryGateway();

	BlocksManagerGateway getBlocksManagerGateway();
	
	void refreshData(UseCasePool useCasePool);

}
