package ca.bcit.turnip.volley.toolbox;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;

public class MyJsonObjectRequest extends JsonObjectRequest {

	private final Map<String, String> headers = new HashMap<String, String>();

	private final String token;

	public MyJsonObjectRequest(int method, String url, JSONObject jsonRequest,
			String token, Listener<JSONObject> listener,
			ErrorListener errorListener) {
		super(method, url, jsonRequest, listener, errorListener);
		this.token = token;
	}

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
