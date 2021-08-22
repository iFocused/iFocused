package application.gateways.serialization;

import java.io.File;

import application.gateways.StatisticsRepositoryGateway;
import application.usecases.StatisticsRepository;

public class SerStatisticsRepositoryGateway implements StatisticsRepositoryGateway {
	private static final String SERIALIZED_USER_STATS_FILE = "user_data.xml";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean populateUserData(StatisticsRepository statisticsRepository) {
		// only populate if file exists
		if(new File("./data/" + SERIALIZED_USER_STATS_FILE).exists()) {
			
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean saveUserData(StatisticsRepository statisticsRepository) {
		// TODO Auto-generated method stub
		return false;
	}

}
