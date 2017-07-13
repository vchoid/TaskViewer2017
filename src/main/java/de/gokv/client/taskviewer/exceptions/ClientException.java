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
	private int errCodeSuper;
	private static int errorCode;



	/**
	 * Kontruktor zum aufangen eines Fehlers, der Klasse {@link Trowable} und einer Nachricht.
	 * 
	 * @param e
	 * @param message
	 */
	public ClientException(Throwable e, String title, String message, int errCodeSuper) {
		this.cause = e;
		this.title = title;
		this.message = message;
		this.errCodeSuper = errCodeSuper;
		errorCode = 200;
	}
	
	
	/**
	 * Gibt den Titel des Fehlers zur�ck
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Gibt den ErrorCode der Superklasse der Exception zur�ck.
	 * @return
	 */
	public int getCode() {
		return errCodeSuper;
	}
	/**
	 * Gibt den ErrorCode der ChildException zur�ck.
	 * @return
	 */
	public static int getErrCode() {
		return errorCode;
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
