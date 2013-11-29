package ca.bcit.turnip;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ScoreActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
	}

	
	public void sendReturn(View view) {
		Intent intent = new Intent(this, WelcomeActivity.class);
		
		startActivity(intent);
	}

}
