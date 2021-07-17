package application.gateways;

import java.util.ArrayList;

import application.gateways.network.ObserverNotifier;
import application.gateways.serialization.LocalDataSerializerGateway;

public class GatewayPoolFactory {
	public GatewayPool getGatewayPool(String gatewayType, ArrayList<ObserverNotifier> observers) {
		switch (gatewayType) {
		case "ser":
			return new LocalDataSerializerGateway(observers);
		}
		return null;
	}
}
