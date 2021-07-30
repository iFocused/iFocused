package application.gateways.serialization;

import java.util.ArrayList;
import java.util.Map;

import application.entities.Website;
import application.gateways.WebsiteRepositoryGateway;
import application.gateways.network.ObserverNotifier;
import application.gateways.network.UpdateNotifierObservable;
import application.usecases.WebsiteRepository;

public class SerWebsiteRepositoryGateway implements WebsiteRepositoryGateway, UpdateNotifierObservable {
	private ArrayList<ObserverNotifier> observers;

	public SerWebsiteRepositoryGateway(ArrayList<ObserverNotifier> observers) {
		this.observers = observers;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean populateUserData(WebsiteRepository websiteRepository) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean saveUserData(Map<Integer, Website> websites, int currId) {
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
