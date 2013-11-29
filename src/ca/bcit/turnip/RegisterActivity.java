package ca.bcit.turnip;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		Intent intent = getIntent();
		String username = intent.getStringExtra(NewUserActivity.username);
		String firstName = intent.getStringExtra(NewUserActivity.firstName);
		String lastName = intent.getStringExtra(NewUserActivity.lastName);
		
//		TextView textView_username = new TextView(this);
//		textView_username.setText(username);
//		setContentView(textView_username);
		
	}


	public void sendLogin(View view) {
		Intent intent = new Intent(this, LoginActivity.class);
		
		startActivity(intent);
	}
	
}
