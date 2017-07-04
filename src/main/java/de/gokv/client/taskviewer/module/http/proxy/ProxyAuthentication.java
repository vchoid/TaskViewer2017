package de.gokv.client.taskviewer.module.http.proxy;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;

public class ProxyAuthentication {

	private static boolean AUTHENTICATIONREQUIRED = false;
	private static boolean PROXY = false;

	private static String SERVER_KEY = "SERVER";
	private static String PORT_KEY = "PORT";
	private static String AUTHUSER_KEY = "AUTHUSER";
	private static String AUTHPASSWORD_KEY = "AUTHPASSWORD";

	private static Map<String, String> params = new HashMap<String, String>();

	public static void initialize(Properties proxyProperties) throws MissingProxyPropertyException {
		AUTHENTICATIONREQUIRED = parseBoolean(proxyProperties.getProperty("authentication-required"));
		fillField(SERVER_KEY, proxyProperties, "proxy-host", false);
		fillField(PORT_KEY, proxyProperties, "proxy-port", false);
		if(params.get(SERVER_KEY) != null && params.get(PORT_KEY) != null)
			PROXY = true;
		fillField(AUTHUSER_KEY, proxyProperties, "proxy-user", AUTHENTICATIONREQUIRED);
		fillField(AUTHPASSWORD_KEY, proxyProperties, "proxy-password", AUTHENTICATIONREQUIRED);
	}

	public static HttpHost buildProxy() {
		return new HttpHost(params.get(SERVER_KEY), Integer.parseInt(params.get(PORT_KEY)));
	}

	public static CredentialsProvider buildCredentials() {
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(buildProxy()),
				new UsernamePasswordCredentials(params.get(AUTHUSER_KEY), params.get(AUTHPASSWORD_KEY)));
		return credsProvider;
	}

	private static void fillField(String field, Properties proxyProperties, String keyName, boolean required)
			throws MissingProxyPropertyException {
		if (proxyProperties.containsKey(keyName) && StringUtils.isNotEmpty(proxyProperties.getProperty(keyName))) {
			params.put(field, proxyProperties.getProperty(keyName));
		} else if (required) {
			throw new MissingProxyPropertyException(keyName, proxyProperties);
		}
	}

	private static boolean parseBoolean(String value) {
		return Boolean.parseBoolean(value);
	}

	public static boolean isAuthentication() {
		return AUTHENTICATIONREQUIRED;
	}

	public static boolean isProxy() {
		return PROXY;
	}

}
