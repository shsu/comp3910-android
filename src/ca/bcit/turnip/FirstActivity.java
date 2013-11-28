package ca.bcit.turnip;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class FirstActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first, menu);
		return true;
	}

	public void sendMessage(View view) {
		Intent intent = new Intent(this, SecondActivity.class);
	
		startActivity(intent);
	}
	
	public void sendNewUser(View view) {
		Intent intent = new Intent(this, NewUserActivity.class);
		
		startActivity(intent);
	}
	
}
