package application.gateways.serialization;

import java.util.ArrayList;

import application.entities.PomodoroSession;
import application.gateways.PomodoroRepositoryGateway;
import application.gateways.network.ObserverNotifier;
import application.gateways.network.UpdateNotifierObservable;
import application.usecases.PomodoroRepository;

public class SerPomodoroRepositoryGateway implements PomodoroRepositoryGateway, UpdateNotifierObservable {
	private ArrayList<ObserverNotifier> observers;

	public SerPomodoroRepositoryGateway(ArrayList<ObserverNotifier> observers) {
		this.observers = observers;
	}

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
