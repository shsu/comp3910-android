package ca.bcit.turnip;

import java.text.DecimalFormat;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import ca.bcit.turnip.config.Config_RestServer;
import ca.bcit.turnip.helper.MyApp;
import ca.bcit.turnip.helper.VolleyHandler;
import ca.bcit.turnip.volley.toolbox.MyJsonObjectRequest;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

public class ScoreActivity extends Activity {

	private RequestQueue volleyRequestQueue;

	private String token;

	private TextView t;

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

		setContentView(R.layout.activity_score);

		displayQuizResult(score, totalPossibleScore);
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
		t = (TextView) findViewById(R.id.textView_quiz_score);

		if (totalPossibleScore == 0) {
	
			t.setText("0%");

		} else {
		
			double r = 100 * (double) score / totalPossibleScore;
			DecimalFormat twoDForm = new DecimalFormat("#.#");
			String j = twoDForm.format(r);
			t.setText(j + "%");
		}

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
				Request.Method.POST, resourceURL, results, token,
				VolleyHandler.getDefaultResponseListner(),
				new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("sendQuizResult", error.toString());
						// TODO: maybe tell the user their stuff is not saved.
					}
				});
		volleyRequestQueue.add(jsonObjectRequest);
	}

}
