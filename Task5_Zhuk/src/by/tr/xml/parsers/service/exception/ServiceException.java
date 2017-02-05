package by.tr.xml.parsers.service.exception;

public class ServiceException extends Exception {
	private static final long serialVersionUID = -3342371207353989661L;

	public ServiceException() {
		super();
	}
	
	public ServiceException(Exception e) {
		super(e);
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Exception e, String message) {
		super(message, e);
	}
}
