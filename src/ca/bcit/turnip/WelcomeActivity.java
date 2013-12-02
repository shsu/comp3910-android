package ca.bcit.turnip;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import ca.bcit.turnip.config.Config_RestServer;
import ca.bcit.turnip.domain.QuizUser;
import ca.bcit.turnip.helper.MyApp;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

public class WelcomeActivity extends Activity {

	private RequestQueue volleyRequestQueue;

	private String token;

	private QuizUser quizUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.volleyRequestQueue = MyApp.getRequestQueue();
		
		token = getIntent().getStringExtra("token");
		Log.d("Auth token from intent", token);

		getProfile();

		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				Log.d("handler delay", "500ms");
				displayProfile();
			}
		}, 500);

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
		String resourceURL = Config_RestServer.REST_SERVER_URL + "user/profile";

		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.GET, resourceURL, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Gson gson = new Gson();
						quizUser = gson.fromJson(response.toString(),
								QuizUser.class);
						Log.d("getProfile", response.toString());
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.d("getProfile", error.toString());
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

	public void displayProfile() {
		// Joseph, use the quizUser and populate the firstname, lastname, and
		// studentNumber fields.
	}

	public void startNextQuiz(View view) {
		Intent intent = new Intent(this, QuizActivity.class);
		intent.putExtra("token", token);
		startActivity(intent);
	}

	public void sendLogout(View view) {
		logoutRequest();
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}

	private void logoutRequest() {
		String resourceURL = Config_RestServer.REST_SERVER_URL + "user/logout";

		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.GET, resourceURL, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d("logoutRequest", response.toString());
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.d("logoutRequest", error.toString());
					}
				});
		volleyRequestQueue.add(jsonObjectRequest);
	}
}
