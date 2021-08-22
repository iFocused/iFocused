package application.gateways.serialization;

import java.util.Map;

import application.entities.Process;
import application.gateways.ProcessRepositoryGateway;
import application.usecases.ProcessRepository;

public class SerProcessRepositoryGateway implements ProcessRepositoryGateway {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean populateUserData(ProcessRepository processRepository) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean saveUserData(Map<Integer, Process> processes, int currId) {
		// TODO Auto-generated method stub
		return false;
	}

}
