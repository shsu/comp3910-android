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
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
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

	private ListView lv;
	
	private int quizNumber;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.volleyRequestQueue = MyApp.getRequestQueue();

		token = getIntent().getStringExtra("token");
		Log.d("Auth token from intent", token);

		setContentView(R.layout.activity_quiz);
		
		getNextQuiz();

		Handler handler = new Handler();
		
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				Log.d("handler delay", "1000ms");
				if (questions != null) {
					displayNextQuiz();
				}
			}
		}, 1000);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}

	@Override
	protected void onStop() {
		if (volleyRequestQueue != null) {
			volleyRequestQueue.cancelAll(this);
		}
		super.onStop();
	}

	public void sendScore(View view) {

		int score = 0;
		for (QuizQuestion question : questions) {
			if (question.getAnswer().equals(question.getSelectedAnswer())) {
				score++;
			}
		}

		Intent intent = new Intent(this, ScoreActivity.class);
		intent.putExtra("token", token);
		intent.putExtra("quizWeek", questions.get(0).getWeek());
		intent.putExtra("score", score);
		intent.putExtra("totalPossibleScore", questions.size());
		startActivity(intent);
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
						Log.e("getNextQuiz", error.toString());
						switch (error.networkResponse.statusCode) {
						case 404:
							// quiz not found, happens when you reach 15th quiz
							break;
						}
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
		// iterate through and create question elements, 4 choices, link choices to the 
		Log.d("quiz", questions.toString());
		
		lv = (ListView) findViewById(R.id.ListView_quiz);
	
		quizNumber = questions.get(0).getWeek();
		setTitle("Quiz #" + quizNumber);
			
		// This is the array adapter, it takes the context of the activity as a first 
        // parameter, the type of list view as a second parameter and your array as a third parameter
        
		QuizAdapter arrayAdapter = new QuizAdapter(this,
				android.R.layout.simple_list_item_multiple_choice, questions);
        lv.setAdapter(arrayAdapter);
		
	}
}
