package de.gokv.client.taskviewer.exceptions;

public class AbstractException extends Throwable {


	private static final long serialVersionUID = 1L;

	protected Throwable cause;
	protected String title;
	protected String message;
	protected int errSuperCode;
	protected static int errChildCode;

	public AbstractException() {
		
	}

	public AbstractException(Throwable cause, String title, String message, int errSuperCode) {
		this.cause = cause;
		this.title = title;
		this.message = message;
		this.errSuperCode = errSuperCode;
		
	}
	
	public static void setErrChildCode(int errChildCode) {
		AbstractException.errChildCode = errChildCode;
	}

	public int getErrChildCode() {
		return errChildCode;
	}
	
	public Throwable getCause() {
		return cause;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getMessage() {
		return message;
	}
	
	public int getErrSuperCode() {
		return errSuperCode;
	}

	
	public void setCause(Throwable cause) {
		this.cause = cause;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setErrSuperCode(int errSuperCode) {
		this.errSuperCode = errSuperCode;
	}

}
