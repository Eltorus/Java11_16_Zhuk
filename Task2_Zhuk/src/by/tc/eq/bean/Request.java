package by.tc.eq.bean;

import by.tc.eq.controller.command.CommandName;

public class Request {
	private CommandName command;

	public CommandName getCommand() {
		return command;
	}

	public void setCommand(CommandName command) {
		this.command = command;
	}
}
