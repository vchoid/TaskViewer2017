package de.gokv.client.taskviewer.exceptions;

/**
 * 
 * Wird bei fehlerhaften Dateien, die nicht vom Typ CSV sind oder bei
 * falschen Dateipfadangaben haben geworfen.
 * 
 * <p>
 * <b>Kontruktor:</b>
 * <ul>
 * <li><b>{@link #ClientException(Throwable e, String message)}</b>: F�ngt den
 * Fehler und die Nachricht auf und speichert diese in neue Variablen ab.</li>
 * </ul>
 * </p>
 * <p>
 * <b>Methoden:</b>
 * <ul>
 * <li><b>{@link #getCause()}</b>: Holt einen Grund des Fehlers und gibt ihn zur�ck.</li>
 * <li><b>{@link #getMessage()}</b>: Holt eine Nachricht und gibt sie zur�ck.</li>
 * </ul>
 * </p>
 * 
 * @author Christoph Kiank
 *
 */
public class ClientException extends RuntimeException {

	private static final long serialVersionUID = 4381501137429755486L;

	private Throwable cause;
	private String title;
	private String message;
	private int code;

	/**
	 * Kontruktor zum aufangen eines Fehlers, der Klasse {@link Trowable} und einer Nachricht.
	 * 
	 * @param e
	 * @param message
	 */
	public ClientException(Throwable e, String title, String message, int code) {
		this.cause = e;
		this.title = title;
		this.message = message;
		this.code = code;
	}
	/**
	 * Gibt den Titel des Fehlers zur�ck
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Gibt den Code des Fehlers zur�ck.
	 *TODO verweis auf FehlercodeTabelle machen
	 * @return
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Gibt die genaue Fehlermeldung zur�ck.
	 * @return
	 */
	public Throwable getCause() {
		return cause;
	}
	/**
	 * Gibt eine kurze Beschreibung des Fehlers zur�ck.
	 * @return
	 */
	public String getMessage() {
		return message;
	}

}
