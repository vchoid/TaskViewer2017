package de.gokv.client.taskviewer.module.http.ssl;

import net.sf.json.JSONObject;

public class ServerException extends Exception {

	private static final long serialVersionUID = 1L;

	private JSONObject serverError;

	public ServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServerException(String message) {
		super(message);
	}

	public ServerException(String message, JSONObject serverError) {
		super(message);
		this.serverError = serverError;
	}

	/**
	 * The API supplies error details in case of STATUS 400 Responses.
	 * 
	 * @return error details (if available) or null
	 */
	public JSONObject getServerError() {
		return serverError;
	}

	@Override
	public String getMessage() {
		if (serverError != null)
			return super.getMessage() + " (details available)";
		else
			return super.getMessage();
	}
}
