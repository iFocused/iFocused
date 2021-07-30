package application.gateways.serialization;

import java.io.File;
import java.util.ArrayList;

import application.gateways.StatisticsRepositoryGateway;
import application.gateways.network.ObserverNotifier;
import application.gateways.network.UpdateNotifierObservable;
import application.usecases.StatisticsRepository;

public class SerStatisticsRepositoryGateway implements StatisticsRepositoryGateway, UpdateNotifierObservable {
	private static final String SERIALIZED_USER_STATS_FILE = "user_data.xml";
	private ArrayList<ObserverNotifier> observers;

	public SerStatisticsRepositoryGateway(ArrayList<ObserverNotifier> observers) {
		this.observers = observers;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean populateUserData(StatisticsRepository statisticsRepository) {
		// only populate if file exists
		if (new File("./data/" + SERIALIZED_USER_STATS_FILE).exists()) {

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
