package de.gokv.client.taskviewer.module.http.ssl;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Collection;

import org.apache.commons.io.IOUtils;

/**
 * wrapper for managing an SSL client certificate including the private key
 */
public class ClientCertificate {

	public static final String pkcs7 = "PKCS7";

	/**
	 * public certificate for client, with additional certificate chain.
	 * corresponds to {@link #clientPrivateKey}
	 */
	private X509Certificate[] clientCertificateChain;

	/**
	 * private key specified read from an PKCS#8 file, corresponds to
	 * {@link #clientCertificateChain}
	 */
	private PrivateKey clientPrivateKey;

	public X509Certificate[] getClientCertificateChain() {
		return clientCertificateChain;
	}

	public X509Certificate getMainCertificate() {
		return clientCertificateChain[0];
	}

	public void setClientCertificateChain(X509Certificate[] clientCertificateChain) {
		this.clientCertificateChain = clientCertificateChain;
	}

	public PrivateKey getClientPrivateKey() {
		return clientPrivateKey;
	}

	public void setClientPrivateKey(PrivateKey clientPrivateKey) {
		this.clientPrivateKey = clientPrivateKey;
	}

	public static ClientCertificate readKeyAndCertficateFromPKCS7_8(String certFile, String keyFile)
			throws ClientCertificateException {
		ClientCertificate keyCertificate = new ClientCertificate();

		keyCertificate.setClientCertificateChain(readCertificateFromPKCS7(certFile));
		keyCertificate.setClientPrivateKey(readRSAKeyFromPKCS8(keyFile));

		return keyCertificate;
	}

	public static PrivateKey readRSAKeyFromPKCS8(String keyFileName) throws ClientCertificateException {
		InputStream input = null;
		try {
			input = ClientCertificate.class.getResourceAsStream(keyFileName);
			byte[] keyFileContents = IOUtils.toByteArray(input);
			PKCS8EncodedKeySpec pkSpec = new PKCS8EncodedKeySpec(keyFileContents);
			return KeyFactory.getInstance("RSA").generatePrivate(pkSpec);
		} catch (InvalidKeySpecException e) {
			throw new ClientCertificateException(
					String.format("file %s does not contain a valid key: %s", keyFileName, e.getMessage()), e);
		} catch (NoSuchAlgorithmException e) {
			throw new ClientCertificateException(
					String.format("the key algorithm is not supported: %s", e.getMessage()), e);
		} catch (IOException e) {
			throw new ClientCertificateException(
					String.format("file %s cannot be read: %s", keyFileName, e.getMessage()), e);
		} finally {
			try {
				if (input != null)
					input.close();
			} catch (IOException e) {
				// swallow
			}
		}
	}

	public static X509Certificate[] readCertificateFromPKCS7(String certFileName) throws ClientCertificateException {
		InputStream input = null;
		Collection<? extends Certificate> c;
		try {
			input = ClientCertificate.class.getResourceAsStream(certFileName);
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			c = cf.generateCertificates(input);

		} catch (CertificateException e) {
			throw new ClientCertificateException(
					String.format("file %s is not a valid PKCS#7 certificate file: %s", certFileName, e.getMessage()),
					e);
		}finally{
			if(input != null)
				try {
					input.close();
				} catch (IOException e) {
					//Swallow exception
				}
		}

		if (c.isEmpty())
			throw new ClientCertificateException(
					String.format("file %s must contain at least ONE certficate", certFileName));

		return c.toArray(new X509Certificate[c.size()]);
	}

	public static ClientCertificate readCertificate()
			throws ClientCertificateException {
		String pathCert = "/clientCert.p7b";
		String pathKey = "/clientKey.pk8";
		return ClientCertificate.readKeyAndCertficateFromPKCS7_8(pathCert, pathKey);
	}

}
