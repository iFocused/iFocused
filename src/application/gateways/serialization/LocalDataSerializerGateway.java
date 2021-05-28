package application.gateways.serialization;

import application.gateways.BlockListGateway;
import application.gateways.GatewayPool;
import application.gateways.PomodoroRepositoryGateway;
import application.gateways.ProcessRepositoryGateway;
import application.gateways.SessionRepositoryGateway;
import application.gateways.TODOListGateway;
import application.gateways.UserManagerGateway;
import application.gateways.WebsiteRepositoryGateway;

public class LocalDataSerializerGateway implements GatewayPool {
	private final BlockListGateway blockListGateway;
	private final PomodoroRepositoryGateway pomodoroRepositoryGateway;
	private final ProcessRepositoryGateway processRepositoryGateway;
	private final WebsiteRepositoryGateway websiteRepositoryGateway;
	private final SessionRepositoryGateway sessionRepositoryGateway;
	private final TODOListGateway todoListGateway;
	private final UserManagerGateway userManagerGateway;

	public LocalDataSerializerGateway() {
		blockListGateway = new SerBlockListGateway();
		pomodoroRepositoryGateway = new SerPomodoroRepositoryGateway();
		processRepositoryGateway = new SerProcessRepositoryGateway();
		websiteRepositoryGateway = new SerWebsiteRepositoryGateway();
		sessionRepositoryGateway = new SerSessionRepositoryGateway();
		todoListGateway = new SerTODOListGateway();
		userManagerGateway = new SerUserManagerGateway();

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

}
