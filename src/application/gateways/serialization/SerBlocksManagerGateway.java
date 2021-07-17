package application.gateways.serialization;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import application.gateways.BlocksManagerGateway;
import application.gateways.network.ObserverNotifier;
import application.gateways.network.UpdateNotifierObservable;
import application.usecases.BlocksManager;

public class SerBlocksManagerGateway implements BlocksManagerGateway, UpdateNotifierObservable {

	private static final String SERIALIZED_USER_BLOCKSETS = "user_blockset.xml";
	private ArrayList<ObserverNotifier> observers;

	public SerBlocksManagerGateway(ArrayList<ObserverNotifier> observers) {
		this.observers = observers;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean populateBlockSet(BlocksManager blocksManager) {
		BlocksManager tmpBlocksManager = XmltoObject();
		if (tmpBlocksManager != null) {
			blocksManager.setBlockSet(tmpBlocksManager.getBlockSet());
			return true;
		}

		// something went wrong
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean saveBlockSet(BlocksManager blocksManager) {
		objectToXml(blocksManager);
		setChanged();
		return true;
	}

	private void objectToXml(BlocksManager blocksManager) {
		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(
					new BufferedOutputStream(new FileOutputStream("data/" + SERIALIZED_USER_BLOCKSETS)));
		} catch (FileNotFoundException fileNotFound) {
			System.err.println("ERROR: While Creating or Opening the File " + SERIALIZED_USER_BLOCKSETS);
		}
		encoder.writeObject(blocksManager);
		encoder.close();
	}

	private BlocksManager XmltoObject() {
		XMLDecoder decoder = null;
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("data/" + SERIALIZED_USER_BLOCKSETS)));
			return (BlocksManager) decoder.readObject();
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: File " + SERIALIZED_USER_BLOCKSETS + " not found");
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
