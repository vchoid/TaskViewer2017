package de.gokv.client.reader;

import java.text.ParseException;

public class InvalidDateException extends Throwable{

	private String message;

	public InvalidDateException(String message) {
		super();
		this.message = message;
	}

	public InvalidDateException(ParseException e) {
		this(String.format("Datum kann nicht umgewandelt werden", e.getMessage()));
	}

	@Override
	public String getMessage() {
		return message;
	}

}
