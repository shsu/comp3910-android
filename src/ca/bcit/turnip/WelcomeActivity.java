package ca.bcit.turnip;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class WelcomeActivity extends Activity {

	private RequestQueue volleyRequestQueue;

	private String token;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		token = getIntent().getStringExtra("token");
		Log.i("Token for this activity", token);

		volleyRequestQueue = Volley.newRequestQueue(this);
		getProfile();

		setContentView(R.layout.activity_welcome);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	@Override
	protected void onStop() {
		if (volleyRequestQueue != null)
			volleyRequestQueue.cancelAll(this);
		super.onStop();
	}

	public void getProfile() {
		String resourceURL = "http://10.0.2.2:8080/a3-server-jhou-shsu/user/profile";

		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.GET, resourceURL, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.i("getProfileResponse", response.toString());
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("getProfileError", error.toString());
					}
				}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> headers = super.getHeaders();
				if (headers == null || headers.equals(Collections.emptyMap())) {
					headers = new HashMap<String, String>();
				}
				headers.put("token", token);
				return headers;
			}
		};
		volleyRequestQueue.add(jsonObjectRequest);
	}

	public void startNextQuiz(View view) {
		startNextQuizRequest();
		Intent intent = new Intent(this, QuizActivity.class);
		intent.putExtra("token", token);
		startActivity(intent);
	}

	private void startNextQuizRequest() {

		String resourceURL = "http://10.0.2.2:8080/a3-server-jhou-shsu/quiz/next";

		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(resourceURL,
				new Response.Listener<JSONArray>() {

					@Override
					public void onResponse(JSONArray response) {
						Log.i("startNextQuizRequestResponse",
								response.toString());
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("startNextQuizRequestResponse", error.toString());
					}
				}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> headers = super.getHeaders();
				if (headers == null || headers.equals(Collections.emptyMap())) {
					headers = new HashMap<String, String>();
				}
				headers.put("token", token);
				return headers;
			}
		};
		volleyRequestQueue.add(jsonArrayRequest);
	}

	public void sendLogout(View view) {
		logoutRequest();
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}

	private void logoutRequest() {

		String resourceURL = "http://10.0.2.2:8080/a3-server-jhou-shsu/user/logout";

		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.GET, resourceURL, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.i("logoutRequestResponse", response.toString());
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {

					}
				});
		volleyRequestQueue.add(jsonObjectRequest);
	}
}
