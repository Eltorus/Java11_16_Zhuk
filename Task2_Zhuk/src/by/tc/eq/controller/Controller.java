package by.tc.eq.controller;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import by.tc.eq.bean.Request;
import by.tc.eq.bean.Response;
import by.tc.eq.controller.command.Command;
import by.tc.eq.controller.exception.CommandException;
import by.tc.eq.controller.exception.CommandNotFoundException;

public class Controller {
	private final CommandProvider provider = new CommandProvider();
	private static final Logger logger = Logger.getLogger(Controller.class.getName());
	private final FileHandler fh;

	public Controller() {
		try {
			fh = new FileHandler("LogFile.log", true);
			logger.addHandler(fh);
			logger.setLevel(Level.ALL);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (IOException e) {
			throw new NullPointerException("Could not setup logger configuration");
		}
	}

	public Response executeTask(Request request) {

		Command executionCommand = null;
		Response response = null;

		try {
			executionCommand = provider.getCommand(request.getCommand());
			response = executionCommand.execute(request);
			if (!response.isErrorStatus()) {
				logger.log(Level.FINE, "Info: " + response.getMessage());
			} else {
				logger.log(Level.SEVERE, "Error: " + response.getErrorMessage());
			}
		} catch (CommandNotFoundException e) {
			logger.log(Level.SEVERE, "Exception: Command not found, ", e);
		} catch (CommandException e) {
			logger.log(Level.SEVERE, "Exception: ", e);
		}

		return response;

	}
}
