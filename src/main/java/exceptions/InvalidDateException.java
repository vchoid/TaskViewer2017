package exceptions;


public class InvalidDateException extends Throwable {

	private static final long serialVersionUID = -7779029938817835658L;

	private String message;
	
	public InvalidDateException(String message, String date) {
		super();
		this.message = (String.format(message, date));
	}

	@Override
	public String getMessage() {
		return message;
	}

}
