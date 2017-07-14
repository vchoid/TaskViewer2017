package de.gokv.client.taskviewer.exceptions;

import de.gokv.client.taskviewer.view.Frame_ExceptionMsg;

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
	
	
	
	public InvalidDateException(Throwable cause, String title, String message, int code, String date) {
		super(cause, title, message, code);
		this.message = (String.format(message, date));
		setErrChildCode(400);
	}
	
	public InvalidDateException(String title, String message, int code, String date) {
		setTitle(title);
		this.message = (String.format(message, date));
		setErrChildCode(400);
	}
	/**
	 * Gibt den Fehler und das falsche Datum zur�ck als Nachricht zur�ck.
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
