package application.gateways.serialization;

import java.io.File;

import application.gateways.DataSerializerGateway;
import application.usecases.PomodoroRepository;
import application.usecases.ProcessRepository;
import application.usecases.SessionRepository;
import application.usecases.StatisticsRepository;
import application.usecases.TODOList;
import application.usecases.UseCasePool;
import application.usecases.UserManager;
import application.usecases.WebsiteRepository;

public class localDataSerializerGateway implements DataSerializerGateway {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isNewUser() {
		File dataFile = new File("/user_data.ser");
		// check if file exists
		return !dataFile.exists();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean populateUserData(UseCasePool useCasePool) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean saveUserData(SessionRepository sessionRepository, PomodoroRepository pomodoroRepository,
			WebsiteRepository websiteRepository, ProcessRepository processRepository, TODOList todoList,
			StatisticsRepository statisticsRepository, UserManager userManager) {
		// TODO Auto-generated method stub
		return false;
	}

}
