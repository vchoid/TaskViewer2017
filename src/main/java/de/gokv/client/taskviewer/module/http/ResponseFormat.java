package de.gokv.client.taskviewer.module.http;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import de.gokv.client.taskviewer.module.http.ssl.ServerException;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public abstract class ResponseFormat<T> {

	private static final Charset UTF8 = Charset.forName("UTF-8");

	public abstract T getData(CloseableHttpResponse response, URI uri)
			throws IOException, ServerException;

	public static final ResponseFormat<JSONObject> JSON = new ResponseFormat<JSONObject>() {
		@Override
		public JSONObject getData(CloseableHttpResponse response, URI uri)
				throws IOException, ServerException {
			final HttpEntity entity = response.getEntity();
			if (entity == null)
				return null;

			final String string = EntityUtils.toString(entity, UTF8);

			try {
				return JSONObject.fromObject(string);
			} catch (JSONException e) {
				throw new ServerException(String.format(
						"non-JSON content from URI %s (HTTP %d)", uri, response
								.getStatusLine().getStatusCode()), e);
			}
		}
	};

	public static final ResponseFormat<JSONObject> MAYBE_JSON = new ResponseFormat<JSONObject>() {
		@Override
		public JSONObject getData(CloseableHttpResponse response, URI uri)
				throws IOException, ServerException {
			final HttpEntity entity = response.getEntity();
			if (entity == null)
				return null;

			final String string = EntityUtils.toString(entity, UTF8);

			try {
				return JSONObject.fromObject(string);
			} catch (JSONException e) {
				return null;
			}
		}
	};

	public static final ResponseFormat<byte[]> BINARY = new ResponseFormat<byte[]>() {
		@Override
		public byte[] getData(CloseableHttpResponse response, URI uri)
				throws IOException {
			final HttpEntity e = response.getEntity();
			if (e == null)
				return null;
			return EntityUtils.toByteArray(e);
		}
	};

	public static final ResponseFormat<Object> NONE = new ResponseFormat<Object>() {

		@Override
		public Object getData(CloseableHttpResponse response, URI uri)
				throws IOException, ServerException {
			return null;
		}
	};

}
