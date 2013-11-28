package ca.bcit.turnip;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class NewUserActivity extends Activity {

	EditText et_username;
	EditText et_password;
	EditText et_firstName;
	EditText et_lastName;
	EditText et_studentId;
	
	
	String username;
	String password;
	String firstName;
	String lastName;
	String studentId;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_user, menu);
		return true;
	}
	
	public void sendRegister(View view) {
		et_username = (EditText) findViewById(R.id.editText_username);
		et_password = (EditText) findViewById(R.id.editText_password);
		et_firstName = (EditText) findViewById(R.id.editText_firstName);
		et_lastName = (EditText) findViewById(R.id.editText_lastName);
		et_studentId = (EditText) findViewById(R.id.editText_studentId);
		
//		Intent intent = new Intent(this, RegisterActivity.class);
		
		// Registration
		
	}

}
