package ca.bcit.turnip;

import com.android.volley.RequestQueue;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class QuizActivity extends Activity {

	private RequestQueue volleyRequestQueue;

	private String token;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		token = getIntent().getStringExtra("token");
		Log.i("Token for this activity", token);
	}

	public void sendScore(View view) {
		Intent intent = new Intent(this, ScoreActivity.class);

		startActivity(intent);
	}

}
