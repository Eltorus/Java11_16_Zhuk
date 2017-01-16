package by.tc.eq.controller.command.impl;

import by.tc.eq.bean.Request;
import by.tc.eq.bean.Response;
import by.tc.eq.bean.entity.User;
import by.tc.eq.bean.requestimpl.SignInRequest;
import by.tc.eq.controller.command.Command;
import by.tc.eq.controller.exception.CommandException;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.exception.ServiceExceptionEmptyFields;
import by.tc.eq.service.factory.ServiceFactory;
import by.tc.eq.service.service.ClientService;

public class SignIn implements Command {

	@Override
	public Response execute(Request request) throws CommandException {
		SignInRequest signinrequest = null;
		if (request instanceof SignInRequest) {
			signinrequest = (SignInRequest) request;
		} else {
			throw new CommandException("Incorrect command");
		}

		Response response = null;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();
		try {
			response = new Response();
			User user = clientService.signIn(signinrequest.getEmail(), signinrequest.getPassword());
			response.setMessage("Welcome, " + user.getName() + "!");
		} catch (ServiceExceptionEmptyFields e) {
			response.setErrorStatus(true);
			response.setErrorMessage("Login and password are empty");
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage("Error during login procedure");
		}

		return response;
	}

}
