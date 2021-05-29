package application.gateways.serialization;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import application.entities.User;
import application.gateways.UserManagerGateway;
import application.usecases.UserManager;

public class SerUserManagerGateway implements UserManagerGateway {

	private static final String SERIALIZED_USER_DATA_FILE = "user_data.xml";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean populateUserData(UserManager userManager) {
		userManager.setUser(XmltoObject());
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean saveUserData(User user, boolean isNewUser) {
		// TODO: Add boolean isNewUser to each Gateway (?) we would need to use the
		// boolean only to create a file
		// tho it is done automatically, so do we really need to know whether this is a
		// new user? Don't think so, but
		// let's think

		if (isNewUser) {
			// need to create file
			new File("./data").mkdirs();
			File f = new File("./data");
			if (f.exists()) {
				System.out.println("exists now.. " + f.getAbsolutePath());
			}
		}

		// serialization goes here
		objectToXml(user);

		return true;
	}

	private void objectToXml(User user) {
		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("data/" + SERIALIZED_USER_DATA_FILE)));
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
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File dvd.xml not found");
		}

		return (User) decoder.readObject();
	}

}
