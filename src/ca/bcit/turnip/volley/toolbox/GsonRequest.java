package ca.bcit.turnip.volley.toolbox;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class GsonRequest<T> extends Request<T> {

	private Map<String, String> headers = new HashMap<String, String>();

	private String token;

	private Gson mGson;

	private final Class<T> mJavaClass;

	private final Listener<T> listener;

	public GsonRequest(int method, String url, Class<T> cls,
			String requestBody, String token, Listener<T> listener,
			ErrorListener errorListener) {
		super(method, url, errorListener);
		this.token = token;
		this.mJavaClass = cls;
		this.listener = listener;
		this.mGson = new Gson();
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			String json = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			return Response.success(mGson.fromJson(json, mJavaClass),
					HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JsonSyntaxException e) {
			return Response.error(new ParseError(e));
		}
	}

	@Override
	protected void deliverResponse(T response) {
		listener.onResponse(response);
	}

	@Override
	public String getBodyContentType() {
		return "application/json; charset=UTF-8";
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return headers;
	}

	public void setHeader(String title, String content) {
		if (token != null) {
			headers.put("token", token);
			Log.i("Auth token sent", token);
		}
	}

}