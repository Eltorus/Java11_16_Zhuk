package by.tr.xml.parsers.controller.exception;

public class ControllerException extends Exception{

	private static final long serialVersionUID = 2142025777800006807L;

	public ControllerException(){
		super();
	}
	
	public ControllerException(String message) {
		super(message);
	}
	
	public ControllerException(Exception e){
		super(e);
	}
	
	public ControllerException(Exception e, String message){
		super(message, e);
	}
}
