package de.gokv.client.taskviewer.exceptions;

public class ClientConfigurationExeception extends AbstractException {

	private static final long serialVersionUID = 1L;

	public ClientConfigurationExeception() {
	}
	
	public ClientConfigurationExeception(Throwable cause, String title, String message, int code) {
		super(cause, title, message, code);
	}
	
}
