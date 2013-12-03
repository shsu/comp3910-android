package ca.bcit.turnip;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import ca.bcit.turnip.config.Config_RestServer;
import ca.bcit.turnip.helper.MyApp;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

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
		Log.d("Quiz week from intent", Integer.toString(quizWeek));

		char[] correctAnswers = getIntent().getCharArrayExtra("correctAnswers");
		Log.d("correctAnswers from intent", correctAnswers.toString());

		char[] selectedAnswers = getIntent().getCharArrayExtra(
				"selectedAnswers");
		Log.d("selectedAnswers from intent", selectedAnswers.toString());

		JSONObject markedQuiz = markQuiz(correctAnswers, selectedAnswers);

		sendQuizResult(quizWeek, markedQuiz);

		setContentView(R.layout.activity_score);
	}

	@Override
	protected void onStop() {
		if (volleyRequestQueue != null)
			volleyRequestQueue.cancelAll(this);
		super.onStop();
	}

	public void sendReturn(View view) {
		Intent intent = new Intent(this, WelcomeActivity.class);

		startActivity(intent);
	}

	private JSONObject markQuiz(char[] correctAnswers, char[] selectedAnswers) {
		int score = 0;
		int totalPossibleScore = 0;

		for (int i = 0; i < correctAnswers.length; i++) {
			if (correctAnswers[i] == selectedAnswers[i]) {
				score++;
			}
			totalPossibleScore++;
		}

		JSONObject results = new JSONObject();
		try {
			results.put("score", score);
			results.put("totalPossibleScore", totalPossibleScore);
		} catch (JSONException e) {
			Log.e("markQuiz", e.toString());
		}
		
		displayQuizResult(score, totalPossibleScore);

		return results;
	}

	private void displayQuizResult(int score, int totalPossibleScore) {
		// don't forget to cast if you are getting the percentage.
	}

	private void sendQuizResult(final int quizWeek, JSONObject quizResult) {

		String resourceURL = Config_RestServer.REST_SERVER_URL + "results/"
				+ quizWeek;

		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.POST, resourceURL, quizResult,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.i("sendQuizResult", response.toString());
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("sendQuizResult", error.toString());
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

}
