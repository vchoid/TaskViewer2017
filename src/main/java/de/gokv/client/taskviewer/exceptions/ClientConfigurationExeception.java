package de.gokv.client.taskviewer.exceptions;

public class ClientConfigurationExeception extends Throwable {

	private static final long serialVersionUID = 1L;
	
	public final static int ERROR_CODE = 101;
	
	private Throwable cause;
	private String message;

	/**
	 * 
	 * @param cause
	 * @param message
	 */
	public ClientConfigurationExeception(Throwable cause, String message) {
		this.cause = cause;
		this.message = message + " (Fehlercode: " + ERROR_CODE + ")";
	}
	
	public static int getErrorCode() {
		return ERROR_CODE;
	}

	public Throwable getCause() {
		return cause;
	}
	
	public String getMessage() {
		return message;
	}
	

}
