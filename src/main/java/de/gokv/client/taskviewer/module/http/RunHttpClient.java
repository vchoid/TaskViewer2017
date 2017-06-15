package de.gokv.client.taskviewer.module.http;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;

import de.gokv.client.taskviewer.module.http.ssl.ClientCertificateException;
import de.gokv.client.taskviewer.module.http.ssl.ServerException;

public class RunHttpClient {
	public static void main(String[] args) {
		try {
			HTTPSClient httpsClient = new HTTPSClient(new URL("http://localhost:9080/gokv-tenant/api"));
			httpsClient.testConnection("");
		} catch (ServerException | GeneralSecurityException | IOException | ClientCertificateException
				| URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
