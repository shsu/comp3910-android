package ca.bcit.turnip;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class QuizActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
	}

	
	public void sendScore(View view) {
		Intent intent = new Intent(this, ScoreActivity.class);
		
		startActivity(intent);
	}

}
