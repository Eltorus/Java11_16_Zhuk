package by.tc.eq.controller.exception;

public class CommandException extends Exception {

	private static final long serialVersionUID = -4119667825771392837L;

	public CommandException() {
		super();
	}

	public CommandException(String message) {
		super(message);
	}

	public CommandException(Exception e) {
		super(e);
	}

	public CommandException(String message, Exception e) {
		super(message, e);
	}
}
