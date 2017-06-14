package de.gokv.client.taskviewer.module.http.ssl;


public class ClientCertificateException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ClientCertificateException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClientCertificateException(String message) {
		super(message);
	}
}
