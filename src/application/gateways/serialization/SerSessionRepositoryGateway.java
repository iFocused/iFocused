package application.gateways.serialization;

import java.util.ArrayList;

import application.entities.Session;
import application.gateways.SessionRepositoryGateway;
import application.usecases.SessionRepository;

public class SerSessionRepositoryGateway implements SessionRepositoryGateway {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean populateUserData(SessionRepository sessionRepository) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean saveUserData(ArrayList<Session> sessions) {
		// TODO Auto-generated method stub
		return false;
	}

}
