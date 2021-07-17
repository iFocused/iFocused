package application.gateways.serialization;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import application.gateways.SessionRepositoryGateway;
import application.gateways.network.ObserverNotifier;
import application.gateways.network.UpdateNotifierObservable;
import application.usecases.SessionRepository;

public class SerSessionRepositoryGateway implements SessionRepositoryGateway, UpdateNotifierObservable {

	private static final String SERIALIZED_USER_CALENDARS = "calendar_entries.xml";
	private ArrayList<ObserverNotifier> observers;

	public SerSessionRepositoryGateway(ArrayList<ObserverNotifier> observers) {
		this.observers = observers;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean populateUserData(SessionRepository sessionRepository) {
		SessionRepository tmpSessionRepository = XmltoObject();
		if (tmpSessionRepository != null) {
			sessionRepository.setCalendarsMap(tmpSessionRepository.getCalendarsMap());
			return true;
		}

		// something went wrong
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean saveUserData(SessionRepository sessionRepository) {
		setChanged();
		if (sessionRepository.getCalendarsMap().isEmpty()) {
			new File("data/" + SERIALIZED_USER_CALENDARS).delete();
			return false;
		} else {
			objectToXml(sessionRepository);
			return true;
		}

	}

	private void objectToXml(SessionRepository sessionRepository) {
		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(
					new BufferedOutputStream(new FileOutputStream("data/" + SERIALIZED_USER_CALENDARS)));
		} catch (FileNotFoundException fileNotFound) {
			System.err.println("ERROR: While Creating or Opening the File " + SERIALIZED_USER_CALENDARS);
		}
		encoder.writeObject(sessionRepository);
		encoder.close();
	}

	private SessionRepository XmltoObject() {
		XMLDecoder decoder = null;
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("data/" + SERIALIZED_USER_CALENDARS)));
			return (SessionRepository) decoder.readObject();
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: File " + SERIALIZED_USER_CALENDARS + " not found");
		}

		return null;
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
