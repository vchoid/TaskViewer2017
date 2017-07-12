package de.gokv.client.taskviewer.exceptions;

public class AbstractException extends Throwable {

	private static final long serialVersionUID = 1L;

	protected Throwable cause;
	protected String title;
	protected String message;
	protected int code;

	public AbstractException() {
		
	}

	public AbstractException(Throwable cause, String title, String message, int code) {
		this.cause = cause;
		this.title = title;
		this.message = message;
		this.code = code;
		
	}
	
	public Throwable getCause() {
		return cause;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getMessage() {
		return message;
	}
	
	public int getCode() {
		return code;
	}

}
