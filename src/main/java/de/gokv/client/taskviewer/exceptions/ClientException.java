package de.gokv.client.taskviewer.exceptions;

/**
 * 
 * Wird bei fehlerhaften Dateien, die nicht vom Typ CSV sind geworfen oder bei
 * falschen Dateipfadangaben.
 * 
 * <p>
 * <b>Kontruktor:</b>
 * <ul>
 * <li><b>{@link #ClientException(Throwable e, String message)}</b>: Fängt den
 * Fehler und die Nachricht auf und speichert diese in neue Variablen ab.</li>
 * </ul>
 * </p>
 * <p>
 * <b>Methoden:</b>
 * <ul>
 * <li><b>{@link #getCause()}</b>: Holt einen Grund des Fehlers und gibt ihn zurück.</li>
 * <li><b>{@link #getMessage()}</b>: Holt eine Nachricht und gibt sie zurück.</li>
 * </ul>
 * </p>
 * 
 * @author Christoph Kiank
 *
 */
public class ClientException extends RuntimeException {

	private static final long serialVersionUID = 4381501137429755486L;

	private Throwable cause;
	private String message;

	/**
	 * Kontruktor zum aufangen eines Fehlers, der Klasse {@link Trowable} und einer Nachricht.
	 * 
	 * @param e
	 * @param message
	 */
	public ClientException(Throwable e, String message) {
		this.cause = e;
		this.message = message + "(ClientException)";
	}
	/**
	 * Holt einen Grund des Fehlers und gibt ihn zurück.
	 */
	public Throwable getCause() {
		return cause;
	}
	/**
	 * Holt eine Nachricht und gibt sie zurück.
	 */
	public String getMessage() {
		return message;
	}

}
