package application.gateways.serialization;

import java.util.ArrayList;

import application.entities.Task;
import application.gateways.TODOListGateway;
import application.usecases.TODOList;

public class SerTODOListGateway implements TODOListGateway {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean populateUserData(TODOList pomodoroRepository) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean saveUserData(ArrayList<Task> currentTasks, ArrayList<Task> completedTasks) {
		// TODO Auto-generated method stub
		return false;
	}

}
