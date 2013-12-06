package ca.bcit.turnip;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import ca.bcit.turnip.config.Config_RestServer;
import ca.bcit.turnip.helper.MyApp;
import ca.bcit.turnip.volley.toolbox.MyJsonObjectRequest;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

public class ScoreActivity extends Activity {

	private RequestQueue volleyRequestQueue;

	private String token;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.volleyRequestQueue = MyApp.getRequestQueue();

		token = getIntent().getStringExtra("token");
		Log.d("Auth token from intent", token);

		int quizWeek = getIntent().getIntExtra("quizWeek", 0);
		int score = getIntent().getIntExtra("score", 0);
		int totalPossibleScore = getIntent().getIntExtra("totalPossibleScore",
				0);

		setTitle("Quiz #" + quizWeek + " Results");
		sendQuizResult(quizWeek, score, totalPossibleScore);
		displayQuizResult(score, totalPossibleScore);

		setContentView(R.layout.activity_score);
	}

	@Override
	protected void onStop() {
		if (volleyRequestQueue != null) {
			volleyRequestQueue.cancelAll(this);
		}
		super.onStop();
	}

	public void sendReturn(View view) {
		Intent intent = new Intent(this, WelcomeActivity.class);
		intent.putExtra("token", token);
		startActivity(intent);
	}

	private void displayQuizResult(int score, int totalPossibleScore) {
		// don't forget to cast if you are getting the percentage.
	}

	private void sendQuizResult(final int quizWeek, final int score,
			final int totalPossibleScore) {
		JSONObject results = new JSONObject();
		try {
			results.put("score", score);
			results.put("totalPossibleScore", totalPossibleScore);
		} catch (JSONException e) {
			Log.e("sendQuizResult", e.toString());
		}
		Log.d("sendQuizResultObject", results.toString());

		String resourceURL = Config_RestServer.REST_SERVER_URL + "results/"
				+ quizWeek;

		MyJsonObjectRequest jsonObjectRequest = new MyJsonObjectRequest(
				Request.Method.POST, resourceURL, results,token,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.i("sendQuizResult", response.toString());
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("sendQuizResult", error.toString());
						// TODO: maybe tell the user their stuff is not saved.
					}
				});
		volleyRequestQueue.add(jsonObjectRequest);
	}

}
