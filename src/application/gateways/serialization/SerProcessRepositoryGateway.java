package application.gateways.serialization;

import java.util.ArrayList;
import java.util.Map;

import application.entities.Process;
import application.gateways.ProcessRepositoryGateway;
import application.gateways.network.ObserverNotifier;
import application.gateways.network.UpdateNotifierObservable;
import application.usecases.ProcessRepository;

public class SerProcessRepositoryGateway implements ProcessRepositoryGateway, UpdateNotifierObservable {
	private ArrayList<ObserverNotifier> observers;

	public SerProcessRepositoryGateway(ArrayList<ObserverNotifier> observers) {
		this.observers = observers;
	}

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
