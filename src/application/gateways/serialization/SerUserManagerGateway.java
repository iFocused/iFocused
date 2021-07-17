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

import application.entities.User;
import application.gateways.UserManagerGateway;
import application.gateways.network.ObserverNotifier;
import application.gateways.network.UpdateNotifierObservable;
import application.usecases.UserManager;

public class SerUserManagerGateway implements UserManagerGateway, UpdateNotifierObservable {

	private static final String SERIALIZED_USER_DATA_FILE = "user_data.xml";
	private ArrayList<ObserverNotifier> observers;

	public SerUserManagerGateway(ArrayList<ObserverNotifier> observers) {
		this.observers = observers;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean populateUserData(UserManager userManager) {
		// creates data file if it exists, otherwise, overwrites it

		User populatedUser = XmltoObject();
		if (populatedUser != null) {
			userManager.setUser(populatedUser);
			return true;
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean saveUserData(User user) {
		objectToXml(user);
		setChanged();
		return true;
	}

	private void objectToXml(User user) {
		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(
					new BufferedOutputStream(new FileOutputStream("data/" + SERIALIZED_USER_DATA_FILE)));
		} catch (FileNotFoundException fileNotFound) {
			System.err.println("ERROR: While Creating or Opening the File " + SERIALIZED_USER_DATA_FILE);
		}
		encoder.writeObject(user);
		encoder.close();
	}

	private User XmltoObject() {
		XMLDecoder decoder = null;
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("data/" + SERIALIZED_USER_DATA_FILE)));
			return (User) decoder.readObject();
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: File " + SERIALIZED_USER_DATA_FILE + " not found");
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
