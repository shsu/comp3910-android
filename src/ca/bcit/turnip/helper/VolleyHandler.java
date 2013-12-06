package ca.bcit.turnip.helper;

import org.json.JSONObject;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

public final class VolleyHandler {

	public static final Response.ErrorListener getDefaultErrorListner() {

		Response.ErrorListener response = new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("errorResponse", error.toString());
			}
		};

		return response;
	}

	public static final Response.Listener<JSONObject> getDefaultResponseListner() {

		Listener<JSONObject> response = new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				Log.d("successResponse", response.toString());
			}

		};

		return response;
	}
}
