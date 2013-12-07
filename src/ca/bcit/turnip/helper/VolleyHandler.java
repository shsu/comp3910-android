package ca.bcit.turnip.helper;

import org.json.JSONObject;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

/**
 * The Class VolleyHandler.
 */
public final class VolleyHandler {

	/**
	 * Gets the default error listner.
	 * 
	 * @return the default error listner
	 */
	public static final Response.ErrorListener getDefaultErrorListner() {

		Response.ErrorListener response = new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("errorResponse", error.toString());
			}
		};

		return response;
	}

	/**
	 * Gets the default response listner.
	 * 
	 * @return the default response listner
	 */
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
