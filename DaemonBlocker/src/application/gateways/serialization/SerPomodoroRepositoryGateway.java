package application.gateways.serialization;

import java.util.ArrayList;

import application.entities.PomodoroSession;
import application.gateways.PomodoroRepositoryGateway;
import application.usecases.PomodoroRepository;

public class SerPomodoroRepositoryGateway implements PomodoroRepositoryGateway {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean populateUserData(PomodoroRepository pomodoroRepository) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean saveUserData(ArrayList<PomodoroSession> pomodoroSessions) {
		// TODO Auto-generated method stub
		return false;
	}

}
