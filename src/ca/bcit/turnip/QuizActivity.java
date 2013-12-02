package ca.bcit.turnip;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import ca.bcit.turnip.config.Config_RestServer;
import ca.bcit.turnip.domain.QuizQuestion;
import ca.bcit.turnip.helper.MyApp;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class QuizActivity extends Activity {

	private RequestQueue volleyRequestQueue;

	private String token;

	private List<QuizQuestion> questions;

	private List<Character> selectedAnswers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.volleyRequestQueue = MyApp.getRequestQueue();

		setContentView(R.layout.activity_quiz);
		token = getIntent().getStringExtra("token");

		getNextQuiz();

		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				Log.d("handler delay", "500ms");
				displayNextQuiz();
			}
		}, 500);
	}

	@Override
	protected void onStop() {
		if (volleyRequestQueue != null)
			volleyRequestQueue.cancelAll(this);
		super.onStop();
	}

	public void sendScore(View view) {
		// need to gather results into selectedAnswers here

		if (selectedAnswers != null && !selectedAnswers.isEmpty()) {
			Intent intent = new Intent(this, ScoreActivity.class);
			intent.putExtra("selectedAnswers", selectedAnswers.toArray());
			startActivity(intent);
		}
	}

	private void getNextQuiz() {
		String resourceURL = Config_RestServer.REST_SERVER_URL + "quiz/next";

		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(resourceURL,
				new Response.Listener<JSONArray>() {

					@Override
					public void onResponse(JSONArray response) {
						Log.d("getNextQuiz", response.toString());

						Gson gson = new Gson();
						Type collectionType = new TypeToken<List<QuizQuestion>>() {
						}.getType();
						questions = gson.fromJson(response.toString(),
								collectionType);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.d("getNextQuiz", error.toString());
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

	protected void displayNextQuiz() {

	}
}
