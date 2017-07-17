package de.gokv.client.taskviewer.exceptions;

/**
 * Wird beim parsen eines Strings(falschen Formates)
 * aus der Klasse {@link de.gokv.client.taskviewer.utils.DateUtil DateUtil}
 * mit der {@link de.gokv.client.taskviewer.utils.DateUtil#parseDate(String) parseDate()}-Methode
 * 
 * in ein LocalDate-Format geworfen.
 * 
 * @author Christoph Kiank
 *
 */
public class InvalidDateException extends AbstractException {

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
