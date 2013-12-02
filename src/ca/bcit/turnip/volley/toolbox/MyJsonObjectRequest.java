package ca.bcit.turnip.volley.toolbox;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;

public class MyJsonObjectRequest extends JsonObjectRequest {

	private Map<String, String> headers = new HashMap<String, String>();

	private String token;

	public MyJsonObjectRequest(int method, String url, JSONObject jsonRequest,
			String token, Listener<JSONObject> listener,
			ErrorListener errorListener) {
		super(method, url, jsonRequest, listener, errorListener);
		this.token = token;
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return headers;
	}

	public void setHeader(String title, String content) {
		if (token != null) {
			headers.put("token", token);
			Log.i("Auth token in request", token);
		}
	}
}
