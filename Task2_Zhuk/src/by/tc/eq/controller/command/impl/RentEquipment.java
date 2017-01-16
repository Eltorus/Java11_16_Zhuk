package by.tc.eq.controller.command.impl;

import by.tc.eq.bean.Request;
import by.tc.eq.bean.Response;
import by.tc.eq.bean.requestimpl.RentEquipmentRequest;
import by.tc.eq.controller.command.Command;
import by.tc.eq.controller.exception.CommandException;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.exception.ServiceExceptionEmptyFields;
import by.tc.eq.service.factory.ServiceFactory;
import by.tc.eq.service.service.ClientService;

public class RentEquipment implements Command {

	@Override
	public Response execute(Request request) throws CommandException {
		RentEquipmentRequest rentequipmentrequest = null;
		if (request instanceof RentEquipmentRequest) {
			rentequipmentrequest = (RentEquipmentRequest) request;
		} else {
			throw new CommandException("Incorrect command");
		}

		Response response = null;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();
		try {
			response = new Response();
			clientService.rentEquipment(rentequipmentrequest.getUser(), rentequipmentrequest.getGood());
			response.setMessage(rentequipmentrequest.getUser().getName() + ", your order is accepted");
		} catch (ServiceExceptionEmptyFields e) {
			response.setErrorStatus(true);
			response.setErrorMessage("Wrong login or password");
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage("Error during ordering procedure: " + e.getMessage());
		}

		return response;
	}

}
