package de.gokv.client.reader;

import java.text.ParseException;
import java.time.format.DateTimeParseException;

public class InvalidDateException extends Throwable {

	private static final long serialVersionUID = -7779029938817835658L;

	private String message;

	private InvalidDateException(String message) {
		super();
		this.message = message;
	}

	public InvalidDateException(String message, Object... args){
		this(String.format(message, args));
	}
	
	public InvalidDateException(DateTimeParseException e) {
		this(String.format("Datum kann nicht konvertiert werden", e.getMessage()));
	}

	@Override
	public String getMessage() {
		return message;
	}

}
