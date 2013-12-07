package ca.bcit.turnip.volley.toolbox;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;

/**
 * The Class MyJsonObjectRequest.
 */
public class MyJsonObjectRequest extends JsonObjectRequest {

	/** The headers. */
	private final Map<String, String> headers = new HashMap<String, String>();

	/** The token. */
	private final String token;

	/**
	 * Instantiates a new my json object request.
	 * 
	 * @param method
	 *            the method
	 * @param url
	 *            the url
	 * @param jsonRequest
	 *            the json request
	 * @param token
	 *            the token
	 * @param listener
	 *            the listener
	 * @param errorListener
	 *            the error listener
	 */
	public MyJsonObjectRequest(int method, String url, JSONObject jsonRequest,
			String token, Listener<JSONObject> listener,
			ErrorListener errorListener) {
		super(method, url, jsonRequest, listener, errorListener);
		this.token = token;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.android.volley.Request#getHeaders()
	 */
	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		Map<String, String> headers = super.getHeaders();
		if (headers == null || headers.equals(Collections.emptyMap())) {
			headers = new HashMap<String, String>();
		}
		headers.put("token", token);
		return headers;
	}
}
