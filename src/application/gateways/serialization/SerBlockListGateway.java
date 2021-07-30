package application.gateways.serialization;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import application.gateways.BlockListGateway;
import application.gateways.network.ObserverNotifier;
import application.gateways.network.UpdateNotifierObservable;
import application.usecases.BlockListRepository;

public class SerBlockListGateway implements BlockListGateway, UpdateNotifierObservable {

	private static final String SERIALIZED_USER_BLOCKLISTS_FILE = "user_blocklists.xml";
	private ArrayList<ObserverNotifier> observers;

	public SerBlockListGateway(ArrayList<ObserverNotifier> observers) {
		this.observers = observers;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean populateUserData(BlockListRepository blockListRepository) {
		BlockListRepository tmpBlockListRepository = XmltoObject();
		if (tmpBlockListRepository != null) {
			blockListRepository.setCurrId(tmpBlockListRepository.getCurrId());
			blockListRepository.setTimeSinceLastModification(tmpBlockListRepository.getTimeSinceLastModification());
			blockListRepository.setBlockLists(tmpBlockListRepository.getBlockLists());
			return true;
		}

		// something went wrong
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean saveUserData(BlockListRepository blockListRepository) {
		objectToXml(blockListRepository);
		setChanged();
		return true;
	}

	private void objectToXml(BlockListRepository blockListRepository) {
		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(
					new BufferedOutputStream(new FileOutputStream("data/" + SERIALIZED_USER_BLOCKLISTS_FILE)));
		} catch (FileNotFoundException fileNotFound) {
			System.err.println("ERROR: While Creating or Opening the File " + SERIALIZED_USER_BLOCKLISTS_FILE);
		}
		encoder.writeObject(blockListRepository);
		encoder.close();
	}

	private BlockListRepository XmltoObject() {
		XMLDecoder decoder = null;
		try {
			decoder = new XMLDecoder(
					new BufferedInputStream(new FileInputStream("data/" + SERIALIZED_USER_BLOCKLISTS_FILE)));
			return (BlockListRepository) decoder.readObject();
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: File " + SERIALIZED_USER_BLOCKLISTS_FILE + " not found");
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
