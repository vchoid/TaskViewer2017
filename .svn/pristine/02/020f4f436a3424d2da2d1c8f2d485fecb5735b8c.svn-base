package de.gokv.client.taskviewer.module.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.X509Certificate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import de.gokv.client.taskviewer.exceptions.ClientConfigurationExeception;
import de.gokv.client.taskviewer.module.http.proxy.ProxyAuthentication;
import de.gokv.client.taskviewer.module.http.ssl.ClientCertificate;
import de.gokv.client.taskviewer.module.http.ssl.ClientCertificateException;
import de.gokv.client.taskviewer.module.http.ssl.ServerException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HTTPSClient {

	private static final char[] KEYSTORE_PASSWORD = String.valueOf(System.currentTimeMillis()).toCharArray();
	private ClientCertificate clientCertificate;
	private URI apiEntryPoint;
	private URI apiAdminEntryPoint;
	private URI apiTaskEntryPoint;
	@SuppressWarnings("unused")
	private ExecutorService downloadPool;
	private CloseableHttpClient client;
	private X509Certificate[] trustedCertficates;
	public static JSONObject task;

	public static String errMsg;

	@SuppressWarnings("deprecation")
	public HTTPSClient(URL apiURL)
			throws ServerException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException,
			KeyStoreException, GeneralSecurityException, IOException, ClientCertificateException, URISyntaxException {

		clientCertificate = ClientCertificate.readCertificate();
		apiEntryPoint = apiURL.toURI();

		downloadPool = Executors.newFixedThreadPool(3);

		final PoolingHttpClientConnectionManager connectionManager;

		if (apiURL.getProtocol().equals("https")) {
			configureTrustedServerCertficate();

			SSLContext sslContext = buildSSL();

			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
					.register("https",
							new SSLConnectionSocketFactory(sslContext,
									SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER))
					.register("http", PlainConnectionSocketFactory.getSocketFactory()).build();

			connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		} else {
			connectionManager = new PoolingHttpClientConnectionManager();
		}
		HttpClientBuilder httpClientBuilder = HttpClients.custom().setConnectionManager(connectionManager);

		if (ProxyAuthentication.isProxy()) {
			httpClientBuilder.setProxy(ProxyAuthentication.buildProxy());
			if (ProxyAuthentication.isAuthentication()) {
				httpClientBuilder.setDefaultCredentialsProvider(ProxyAuthentication.buildCredentials());
			}
		}
		client = httpClientBuilder.build();

	}

	private SSLContext buildSSL()
			throws KeyStoreException, GeneralSecurityException, IOException, ClientCertificateException {
		SSLContext sslContext = SSLContext.getInstance("TLSv1");

		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());

		kmf.init(createClientKeyStore(), KEYSTORE_PASSWORD);

		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

		tmf.init(createTrustKeyStore());

		final TrustManager[] tms = tmf.getTrustManagers();

		sslContext.init(kmf.getKeyManagers(), tms, null);

		return sslContext;
	}

	private KeyStore createClientKeyStore() throws GeneralSecurityException, IOException, ClientCertificateException {
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

		keyStore.load(null);

		keyStore.setKeyEntry("default", clientCertificate.getClientPrivateKey(), KEYSTORE_PASSWORD,
				clientCertificate.getClientCertificateChain());

		return keyStore;
	}

	private KeyStore createTrustKeyStore() throws GeneralSecurityException, IOException, ClientCertificateException {
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

		keyStore.load(null);

		int i = 1;
		for (X509Certificate cert : trustedCertficates) {
			keyStore.setCertificateEntry(String.format("server-%d", i), cert);
			i++;
		}

		return keyStore;
	}

	private void configureTrustedServerCertficate() throws ClientCertificateException {
		String filename = "/trusted.cer";
		trustedCertficates = ClientCertificate.readCertificateFromPKCS7(filename);
	}

	public void loadTaskDetails(String taskID) throws ClientConfigurationExeception {
		try {
			JSONObject taskDetails = getTaskDetails(taskID);
			JSONArray results = taskDetails.getJSONArray("results");
			task = results.getJSONObject(0);
		} catch (ServerException | IndexOutOfBoundsException e) {
			throw new ClientConfigurationExeception(e, "Eintrag wurde nicht gefunden", "Der gewählte Eintrag konnte nicht geladen werden.", 5);
		}
	}

	public <T> T get(URI url, ResponseFormat<T> format) throws ServerException {
		HttpGet request = new HttpGet(url);
		return execRequest(request, format);
	}

	public <T> T post(URI url, ResponseFormat<T> format, JSONObject data) throws ServerException {
		HttpPost request = new HttpPost(url);
		request.setEntity(new StringEntity(data.toString(), ContentType.APPLICATION_JSON));
		return execRequest(request, format);
	}

	private <T> T execRequest(HttpUriRequest request, ResponseFormat<T> format) throws ServerException {
		CloseableHttpResponse res = null;
		try {
			res = client.execute(request);
			return format.getData(res, request.getURI());
		} catch (IOException e) {
			throw new ServerException(String.format("IO error accessing URI %s: %s", request.getURI(), e.getMessage()),
					e);
		}
	}

	public URI getApiAdminEntryPoint() throws ServerException {
		if (apiAdminEntryPoint == null)
			discoverApi();
		return apiAdminEntryPoint;
	}

	private void discoverApi() throws ServerException {
		try {
			JSONObject info = get(apiEntryPoint, ResponseFormat.JSON);
			apiAdminEntryPoint = JSONEntityUtil.getLink(apiEntryPoint, info, "gokv:admin");
		} catch (ServerException e) {
			throw new ServerException("Der Serverpfad wurde nicht gefunden", e);
		}
	}

	public URI getApiTaskEntryPoint() throws ServerException {
		if (apiTaskEntryPoint == null) {
			discoverApiAdmin();
		}
		return apiTaskEntryPoint;
	}

	private void discoverApiAdmin() throws ServerException {
		JSONObject info;
		try {
			info = get(getApiAdminEntryPoint(), ResponseFormat.JSON);
			apiTaskEntryPoint = JSONEntityUtil.getLink(apiAdminEntryPoint, info, "gokv:task");
		} catch (ServerException e) {
			throw new ServerException("Der Serverpfad wurde nicht gefunden", e);
		}
	}

	public JSONObject getTaskDetails(String taskID) throws ServerException {
		try {
			return get(new URI(getApiTaskEntryPoint().toString() + "/" + taskID), ResponseFormat.JSON);
		} catch (URISyntaxException e) {
			throw new ServerException("", e);
		}
	}

}