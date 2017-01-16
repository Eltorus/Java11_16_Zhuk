package by.tc.eq.controller.command.impl;

import by.tc.eq.bean.Request;
import by.tc.eq.bean.Response;
import by.tc.eq.bean.requestimpl.AddEquipmentRequest;
import by.tc.eq.controller.command.Command;
import by.tc.eq.controller.exception.CommandException;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.exception.ServiceExceptionEmptyFields;
import by.tc.eq.service.factory.ServiceFactory;
import by.tc.eq.service.service.ShopService;

public class AddEquipment implements Command {

	@Override
	public Response execute(Request request) throws CommandException {
		AddEquipmentRequest addequipmentrequest = null;
		if (request instanceof AddEquipmentRequest) {
			addequipmentrequest = (AddEquipmentRequest) request;
		} else {
			throw new CommandException("Incorrect command");
		}

		Response response = null;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ShopService shopService = serviceFactory.getShopService();

		try {
			response = new Response();
			shopService.addEquipment(addequipmentrequest.getEquipment());
			response.setMessage("Equipment " + addequipmentrequest.getEquipment().getTitle() + " added");
		} catch (ServiceExceptionEmptyFields e) {
			response.setErrorStatus(true);
			response.setErrorMessage("Fields of equipment are empty");
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage("Error during adding equipment...");
		}
		return response;
	}
}
