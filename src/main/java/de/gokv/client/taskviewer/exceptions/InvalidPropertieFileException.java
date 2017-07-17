package de.gokv.client.taskviewer.exceptions;

public class InvalidPropertieFileException extends AbstractException {
	private static final long serialVersionUID = 1L;

	public InvalidPropertieFileException() {
	}

	public InvalidPropertieFileException(Throwable cause, String title, String message, int code) {
		super(cause, title, message, code);
		setErrChildCode(100);
	}

}
