package ca.bcit.turnip;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}
	
	public void sendQuiz(View view) {
		Intent intent = new Intent(this, QuizActivity.class);
		
		startActivity(intent);
	}

	public void sendLogout(View view) {
		// insert Logout credentials deletion procedures
		
		Intent intent = new Intent(this, LoginActivity.class);
		
		startActivity(intent);
	}
	
}
