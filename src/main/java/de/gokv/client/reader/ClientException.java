package de.gokv.client.reader;

public class ClientException extends RuntimeException {
	
	private static final long serialVersionUID = 4381501137429755486L;
	
	private Throwable cause;
	private String message;

	public ClientException(Throwable e, String message) {
		this.cause = e;
		this.message = message;
	}
	
	public Throwable getCause() {
		return cause;
	}
	
	public String getMessage() {
		return message;
	}
	
}
