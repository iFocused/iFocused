package application.gateways.serialization;

import java.util.Map;

import application.entities.Website;
import application.gateways.WebsiteRepositoryGateway;
import application.usecases.WebsiteRepository;

public class SerWebsiteRepositoryGateway implements WebsiteRepositoryGateway {

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
		return false;
	}

}
