package application.gateways.serialization;

import java.util.ArrayList;

import application.entities.Task;
import application.gateways.TODOListGateway;
import application.gateways.network.ObserverNotifier;
import application.gateways.network.UpdateNotifierObservable;
import application.usecases.TODOList;

public class SerTODOListGateway implements TODOListGateway, UpdateNotifierObservable {
	private ArrayList<ObserverNotifier> observers;

	public SerTODOListGateway(ArrayList<ObserverNotifier> observers) {
		this.observers = observers;
	}

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
		setChanged();
		return false;
	}

	@Override
	public void addObserver(ObserverNotifier observer) {
		this.observers.add(observer);

	}

	@Override
	public void removeObserver(ObserverNotifier observer) {
		this.observers.remove(observer);

	}

	@Override
	public void setChanged() {
		for (ObserverNotifier observer : this.observers) {
			observer.update();
		}

	}

}
