package application.gateways;

public interface GatewayPool {

	BlockListGateway getBlockListGateway();

	SessionRepositoryGateway getSessionRepositoryGateway();

	PomodoroRepositoryGateway getPomodoroRepositoryGateway();

	WebsiteRepositoryGateway getWebsiteRepositoryGateway();

	ProcessRepositoryGateway getProcessRepositoryGateway();

	TODOListGateway getTODOListGateway();

	UserManagerGateway getUserManagerGateway();

	StatisticsRepositoryGateway getStatisticsRepositoryGateway();

	BlocksManagerGateway getBlocksManagerGateway();

}
