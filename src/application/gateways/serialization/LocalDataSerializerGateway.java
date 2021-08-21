package application.gateways.serialization;

import java.util.ArrayList;

import application.gateways.BlockListGateway;
import application.gateways.BlocksManagerGateway;
import application.gateways.GatewayPool;
import application.gateways.PomodoroRepositoryGateway;
import application.gateways.ProcessRepositoryGateway;
import application.gateways.SessionRepositoryGateway;
import application.gateways.StatisticsRepositoryGateway;
import application.gateways.TODOListGateway;
import application.gateways.UserManagerGateway;
import application.gateways.WebsiteRepositoryGateway;
import application.gateways.network.ObserverNotifier;

public class LocalDataSerializerGateway implements GatewayPool {
	private final BlockListGateway blockListGateway;
	private final PomodoroRepositoryGateway pomodoroRepositoryGateway;
	private final ProcessRepositoryGateway processRepositoryGateway;
	private final WebsiteRepositoryGateway websiteRepositoryGateway;
	private final SessionRepositoryGateway sessionRepositoryGateway;
	private final TODOListGateway todoListGateway;
	private final UserManagerGateway userManagerGateway;
	private final StatisticsRepositoryGateway statisticsRepositoryGateway;
	private final BlocksManagerGateway blocksManagerGateway;

	public LocalDataSerializerGateway(ArrayList<ObserverNotifier> observers) {
		blockListGateway = new SerBlockListGateway(observers);
		pomodoroRepositoryGateway = new SerPomodoroRepositoryGateway(observers);
		processRepositoryGateway = new SerProcessRepositoryGateway(observers);
		websiteRepositoryGateway = new SerWebsiteRepositoryGateway(observers);
		sessionRepositoryGateway = new SerSessionRepositoryGateway(observers);
		todoListGateway = new SerTODOListGateway(observers);
		userManagerGateway = new SerUserManagerGateway(observers);
		statisticsRepositoryGateway = new SerStatisticsRepositoryGateway(observers);
		blocksManagerGateway = new SerBlocksManagerGateway(observers);

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
	public UserManagerGateway getUserManagerGateway() {
		// TODO Auto-generated method stub
		return userManagerGateway;
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

}
