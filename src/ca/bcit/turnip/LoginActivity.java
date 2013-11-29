package ca.bcit.turnip;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}


	public void sendMessage(View view) {
		Intent intent = new Intent(this, WelcomeActivity.class);
	
		startActivity(intent);
	}
	
	public void sendNewUser(View view) {
		Intent intent = new Intent(this, NewUserActivity.class);
		
		startActivity(intent);
	}
	
}
