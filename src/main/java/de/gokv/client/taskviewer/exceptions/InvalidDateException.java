package de.gokv.client.taskviewer.exceptions;

/**
 * Wird beim parsen eines Strings, falschen Formates in eine LocalDate mit der
 * {@link de.gokv.client.taskviewer.utils.DateUtil#parseDate(String) parseDate} geworfen.
 * 
 * @author Christoph Kiank
 *
 */
public class InvalidDateException extends Throwable {

	private static final long serialVersionUID = -7779029938817835658L;

	private String message;
	/**
	 * Gibt den Fehler und das falsche Datum zurück als Nachricht zurück.
	 * 
	 * @param message
	 * @param date
	 */
	public InvalidDateException(String message, String date) {
		super();
		this.message = (String.format(message, date));
	}

	@Override
	public String getMessage() {
		return message;
	}

}
