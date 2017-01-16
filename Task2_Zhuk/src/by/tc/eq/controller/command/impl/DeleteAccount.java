package by.tc.eq.controller.command.impl;

import by.tc.eq.bean.Request;
import by.tc.eq.bean.Response;
import by.tc.eq.bean.requestimpl.DeleteAccountRequest;
import by.tc.eq.controller.command.Command;
import by.tc.eq.controller.exception.CommandException;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.exception.ServiceExceptionEmptyFields;
import by.tc.eq.service.factory.ServiceFactory;
import by.tc.eq.service.service.ClientService;

public class DeleteAccount implements Command {

	@Override
	public Response execute(Request request) throws CommandException {
		DeleteAccountRequest deleteaccountrequest = null;
		if (request instanceof DeleteAccountRequest) {
			deleteaccountrequest = (DeleteAccountRequest) request;
		} else {
			throw new CommandException("Incorrect command");
		}

		Response response = null;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();
		try {
			response = new Response();
			clientService.deleteUser(deleteaccountrequest.getUser());
			response.setMessage("Account " + deleteaccountrequest.getUser().getName() + " has been deleted");
		} catch (ServiceExceptionEmptyFields e) {
			response.setErrorStatus(true);
			response.setErrorMessage("Login and password are empty");
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage("Error during deleting account procedure");
		}

		return response;
	}

}
