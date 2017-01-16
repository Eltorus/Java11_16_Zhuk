package by.tc.eq.controller.command;

import by.tc.eq.bean.Request;
import by.tc.eq.bean.Response;
import by.tc.eq.controller.exception.CommandException;

public interface Command {
	public Response execute(Request request) throws CommandException;
}
