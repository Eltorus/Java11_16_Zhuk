package by.tc.eq.controller.command.impl;

import by.tc.eq.bean.Request;
import by.tc.eq.bean.Response;
import by.tc.eq.bean.requestimpl.RegistrationRequest;
import by.tc.eq.controller.command.Command;
import by.tc.eq.controller.exception.CommandException;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.exception.ServiceExceptionEmptyFields;
import by.tc.eq.service.factory.ServiceFactory;
import by.tc.eq.service.service.ClientService;

public class Registration implements Command {

	@Override
	public Response execute(Request request) throws CommandException {
		RegistrationRequest registrationrequest = null;
		if (request instanceof RegistrationRequest) {
			registrationrequest = (RegistrationRequest) request;
		} else {
			throw new CommandException("Incorrect command");
		}

		Response response = null;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();
		try {
			response = new Response();
			clientService.addNewUser(registrationrequest.getUser());
			response.setMessage(registrationrequest.getUser().getName() + ", your account succefully added");
		} catch (ServiceExceptionEmptyFields e) {
			response.setErrorStatus(true);
			response.setErrorMessage("Fields are empty");
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage("Error during registration procedure");
		}

		return response;
	}

}
