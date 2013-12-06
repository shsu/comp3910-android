package ca.bcit.turnip;

import java.text.DecimalFormat;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import ca.bcit.turnip.config.Config_RestServer;
import ca.bcit.turnip.domain.QuizUser;
import ca.bcit.turnip.helper.MyApp;
import ca.bcit.turnip.helper.VolleyHandler;
import ca.bcit.turnip.volley.toolbox.MyJsonObjectRequest;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.google.gson.Gson;

public class WelcomeActivity extends Activity {

	private RequestQueue volleyRequestQueue;
	private String token;
	private QuizUser quizUser;
	private TextView t_username;
	private TextView t_quizStats;
	private double quizAverage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.volleyRequestQueue = MyApp.getRequestQueue();

		token = getIntent().getStringExtra("token");
		Log.d("Auth token from intent", token);

		getProfile();
		getQuizAverage();

		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				Log.d("handler delay", "1000ms");
				displayProfile();
				displayQuizAverage();
			}
		}, 1000);

		setContentView(R.layout.activity_welcome);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_logout:
			logoutRequest();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onStop() {
		if (volleyRequestQueue != null) {
			volleyRequestQueue.cancelAll(this);
		}
		super.onStop();
	}

	public void getProfile() {
		String resourceURL = Config_RestServer.REST_SERVER_URL + "user/profile";

		MyJsonObjectRequest jsonObjectRequest = new MyJsonObjectRequest(
				Request.Method.GET, resourceURL, null, token,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Gson gson = new Gson();
						quizUser = gson.fromJson(response.toString(),
								QuizUser.class);
						Log.d("getProfile", response.toString());
					}
				}, VolleyHandler.getDefaultErrorListner());
		volleyRequestQueue.add(jsonObjectRequest);
	}

	public void displayProfile() {
		
		t_username = (TextView) findViewById(R.id.textView_username_welcome); 
		t_username.setText(quizUser.getFirstName() + " "  + quizUser.getLastName() + " (" + quizUser.getStudentNumber() + ")");
		
	}

	public void displayQuizAverage(){
		t_quizStats = (TextView) findViewById(R.id.textView_user_average_score);
		t_quizStats.setText(round() + "%");
	}
	
	private String round() {
		double x = quizAverage * 100;
		DecimalFormat twoDForm = new DecimalFormat("#.#");
		return twoDForm.format(x);
	}

	public void getQuizAverage() {

		String resourceURL = Config_RestServer.REST_SERVER_URL
				+ "results/average";

		MyJsonObjectRequest jsonObjectRequest = new MyJsonObjectRequest(
				Request.Method.GET, resourceURL, null, token,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d("getQuizAverage", response.toString());
						try {
							quizAverage = response
									.getDouble("cumulativeAverage");
						} catch (JSONException e) {
							Log.e("getQuizAverage", e.toString());
						}
					}
				}, VolleyHandler.getDefaultErrorListner());
		volleyRequestQueue.add(jsonObjectRequest);
	}

	public void startNextQuiz(View view) {
		Intent intent = new Intent(this, QuizActivity.class);
		intent.putExtra("token", token);
		startActivity(intent);
	}

	private void logoutRequest() {
		String resourceURL = Config_RestServer.REST_SERVER_URL + "user/logout";

		MyJsonObjectRequest jsonObjectRequest = new MyJsonObjectRequest(
				Request.Method.GET, resourceURL, null, token,
				VolleyHandler.getDefaultResponseListner(), VolleyHandler.getDefaultErrorListner());
		volleyRequestQueue.add(jsonObjectRequest);
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}
}
