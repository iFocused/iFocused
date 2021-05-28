package application.gateways.serialization;

import application.entities.User;
import application.gateways.DataSerializerGateway;
import application.gateways.UserManagerGateway;
import application.usecases.UserManager;

public class SerUserManagerGateway implements UserManagerGateway {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean populateUserData(UserManager userManager) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean saveUserData(User user, DataSerializerGateway userGateway) {
		// TODO Auto-generated method stub
		return false;
	}

}
