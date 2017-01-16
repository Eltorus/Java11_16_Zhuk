package by.tc.eq.service.exception;

public class ServiceExceptionEmptyFields extends ServiceException {

	private static final long serialVersionUID = 1L;

	public ServiceExceptionEmptyFields() {
		super();
	}

	public ServiceExceptionEmptyFields(String message) {
		super(message);
	}

	public ServiceExceptionEmptyFields(Exception e) {
		super(e);
	}

	public ServiceExceptionEmptyFields(String message, Exception e) {
		super(message, e);
	}
}
