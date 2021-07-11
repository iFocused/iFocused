package application.gateways;

import application.gateways.serialization.LocalDataSerializerGateway;

public class GatewayPoolFactory {
	public GatewayPool getGatewayPool(String gatewayType) {
		switch (gatewayType) {
		case "ser":
			return new LocalDataSerializerGateway();
		}
		return null;
	}
}
