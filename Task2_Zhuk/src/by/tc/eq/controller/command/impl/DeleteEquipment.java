package by.tc.eq.controller.command.impl;

import by.tc.eq.bean.Request;
import by.tc.eq.bean.Response;
import by.tc.eq.bean.requestimpl.DeleteEquipmentRequest;
import by.tc.eq.controller.command.Command;
import by.tc.eq.controller.exception.CommandException;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.exception.ServiceExceptionEmptyFields;
import by.tc.eq.service.factory.ServiceFactory;
import by.tc.eq.service.service.ShopService;

public class DeleteEquipment implements Command {

	@Override
	public Response execute(Request request) throws CommandException {
		DeleteEquipmentRequest deleteequipmentrequest = null;
		if (request instanceof DeleteEquipmentRequest) {
			deleteequipmentrequest = (DeleteEquipmentRequest) request;
		} else {
			throw new CommandException("Incorrect command");
		}

		Response response = null;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ShopService shopService = serviceFactory.getShopService();
		try {
			response = new Response();
			shopService.deleteEquipment(deleteequipmentrequest.getEquipment());
			response.setMessage("Equipment " + deleteequipmentrequest.getEquipment().getTitle() + " has been deleted");
		} catch (ServiceExceptionEmptyFields e) {
			response.setErrorStatus(true);
			response.setErrorMessage("Fields of equipment are empty");
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage("Error during deleting equipment procedure");
		}

		return response;
	}
}
