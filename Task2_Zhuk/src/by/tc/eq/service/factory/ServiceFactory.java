package by.tc.eq.service.factory;

import by.tc.eq.service.impl.ClientServiceImpl;
import by.tc.eq.service.impl.ShopServiceImpl;
import by.tc.eq.service.service.ClientService;
import by.tc.eq.service.service.ShopService;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	private ClientService clientService = new ClientServiceImpl();
	private ShopService shopService = new ShopServiceImpl();

	private ServiceFactory() {
	}

	public ClientService getClientService() {
		return clientService;
	}

	public ShopService getShopService() {
		return shopService;
	}

	public static ServiceFactory getInstance() {
		return instance;
	}
}
