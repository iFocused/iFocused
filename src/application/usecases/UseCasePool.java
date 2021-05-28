package application.usecases;

import java.io.Serializable;

/**
 * Used to initialize the use cases and provide a pool of references to access
 * each
 */
public class UseCasePool implements Serializable {
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

	public void initalizeUseCases() {
		/**
		 * (-) Would need to check at some point if there is data to be de-serialized and fetched  
		 */
		
		blockListRepository = new BlockListRepository(); /* (-) */
		
		sessionRepository = new SessionRepository(); /* (-) */
		
		pomodoroRepository = new PomodoroRepository(); /* (-) */
		
		websiteRepository = new WebsiteRepository(); /* (-) */
		
		processRepository = new ProcessRepository(); /* (-) */
		
		todoList = new TODOList(); /* (-) */
		
		pointEligbility = new PointEligibility(todoList, sessionRepository, pomodoroRepository);
		blocksManager = new BlocksManager(null, blockListRepository, sessionRepository,
				pomodoroRepository); /* TODO: FIX */
		
		statisticsRepository = new StatisticsRepository(null); /* TODO: FIX (-)  */
		
		userManager = new UserManager(null); /* TODO: FIX (-) */

		/**
		 * NEED TO FIGURE OUT WHERE TO GET THE USERNAME AND TIMEZONE TO initialize a
		 * USER
		 */
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
