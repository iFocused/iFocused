package application.usecases;

import java.io.File;

import application.gateways.GatewayPool;

/**
 * Used to initialize the use cases and provide a pool of references to access
 * each
 */
public class UseCasePool {
	private BlockListRepository blockListRepository;
	private SessionRepository sessionRepository;
	private PomodoroRepository pomodoroRepository;
	private WebsiteRepository websiteRepository;
	private ProcessRepository processRepository;
	private PointEligibility pointEligbility;
	private BlocksManager blocksManager;
	private StatisticsRepository statisticsRepository;
	private UserManager userManager;
	private TODOList todoList;
	private GatewayPool gatewayPool;

	public UseCasePool(GatewayPool gatewayPool) {
		this.gatewayPool = gatewayPool;
		initalizeUseCases();
	}

	public void initalizeUseCases() {
		/**
		 * (-) Would need to check at some point if there is data to be de-serialized
		 * and fetched
		 */

		// make every use case take a gateway that will de-serialized the data like here
		// https://github.com/nigow/TradingSystem/blob/e1241568adf33499be90258abe8cbef208d55a3a/src/main/java/org/twelve/usecases/UseCasePool.java#L15
		// make every gateway take reference to controller pool
		blockListRepository = new BlockListRepository(gatewayPool.getBlockListGateway()); 

		sessionRepository = new SessionRepository(gatewayPool.getSessionRepositoryGateway());

		pomodoroRepository = new PomodoroRepository(gatewayPool.getPomodoroRepositoryGateway()); 

		websiteRepository = new WebsiteRepository(gatewayPool.getWebsiteRepositoryGateway()); 

		processRepository = new ProcessRepository(gatewayPool.getProcessRepositoryGateway()); 

		todoList = new TODOList(gatewayPool.getTODOListGateway()); 

		pointEligbility = new PointEligibility(todoList, sessionRepository, pomodoroRepository);
		blocksManager = new BlocksManager(null, blockListRepository, sessionRepository,
				pomodoroRepository); /* TODO: FIX */

		statisticsRepository = new StatisticsRepository(gatewayPool.getStatisticsRepositoryGateway()); /* TODO: FIX (-) */

		userManager = new UserManager(gatewayPool.getUserManagerGateway());

		/**
		 * NEED TO FIGURE OUT WHERE TO GET THE USERNAME AND TIMEZONE TO initialize a
		 * USER
		 */
	}

	/**
	 * Return whether the user is a new user
	 * 
	 * @return True iff the user is a new user, otherwise false.
	 */
	private boolean isNewUser() {
		File dataFile = new File("./data/user_data.xml");
		// check if file exists
		return !dataFile.exists();
	}

	/**
	 * Returns true iff there is user data that can be populated and is being populated, 
	 * otherwise, if the user is new and there is nothing to populate, false is returned
	 * 
	 * @return whether an attempt to populate the user data was placed
	 */
	public boolean populateAll() {
		if (isNewUser()) {
			// need to create directory
			new File("./data").mkdirs();
			return false;
		} else {
			gatewayPool.getUserManagerGateway().populateUserData(userManager);
			gatewayPool.getSessionRepositoryGateway().populateUserData(sessionRepository);
			gatewayPool.getPomodoroRepositoryGateway().populateUserData(pomodoroRepository);
			gatewayPool.getWebsiteRepositoryGateway().populateUserData(websiteRepository);
			gatewayPool.getProcessRepositoryGateway().populateUserData(processRepository);
			gatewayPool.getTODOListGateway().populateUserData(todoList);
			gatewayPool.getStatisticsRepositoryGateway().populateUserData(statisticsRepository);
			return true;
		}
	}

	public BlockListRepository getBlockListRepository() {
		return blockListRepository;
	}

	public void setBlockListRepository(BlockListRepository blockListRepository) {
		this.blockListRepository = blockListRepository;
	}

	public SessionRepository getSessionRepository() {
		return sessionRepository;
	}

	public void setSessionRepository(SessionRepository sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	public PomodoroRepository getPomodoroRepository() {
		return pomodoroRepository;
	}

	public void setPomodoroRepository(PomodoroRepository pomodoroRepository) {
		this.pomodoroRepository = pomodoroRepository;
	}

	public WebsiteRepository getWebsiteRepository() {
		return websiteRepository;
	}

	public void setWebsiteRepository(WebsiteRepository websiteRepository) {
		this.websiteRepository = websiteRepository;
	}

	public ProcessRepository getProcessRepository() {
		return processRepository;
	}

	public void setProcessRepository(ProcessRepository processRepository) {
		this.processRepository = processRepository;
	}

	public PointEligibility getPointEligbility() {
		return pointEligbility;
	}

	public void setPointEligbility(PointEligibility pointEligbility) {
		this.pointEligbility = pointEligbility;
	}

	public BlocksManager getBlocksManager() {
		return blocksManager;
	}

	public void setBlocksManager(BlocksManager blocksManager) {
		this.blocksManager = blocksManager;
	}

	public StatisticsRepository getStatisticsRepository() {
		return statisticsRepository;
	}

	public void setStatisticsRepository(StatisticsRepository statisticsRepository) {
		this.statisticsRepository = statisticsRepository;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public TODOList getTodoList() {
		return todoList;
	}

	public void setTodoList(TODOList todoList) {
		this.todoList = todoList;
	}
}
