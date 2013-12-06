package ca.bcit.turnip;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import ca.bcit.turnip.config.Config_RestServer;
import ca.bcit.turnip.helper.MyApp;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

public class Act_Register extends Activity {

	EditText et_username;
	EditText et_password;
	EditText et_confirm_password;
	EditText et_firstName;
	EditText et_lastName;
	EditText et_studentId;

	private RequestQueue volleyRequestQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.volleyRequestQueue = MyApp.getRequestQueue();
		setContentView(R.layout.activity_register);
		et_username = (EditText) findViewById(R.id.editText_username);
		et_password = (EditText) findViewById(R.id.editText_password);
		et_confirm_password = (EditText) findViewById(R.id.editText_confirm_password);
		et_firstName = (EditText) findViewById(R.id.editText_firstName);
		et_lastName = (EditText) findViewById(R.id.editText_lastName);
		et_studentId = (EditText) findViewById(R.id.editText_studentId);
	}

	@Override
	protected void onStop() {
		if (volleyRequestQueue != null) {
			volleyRequestQueue.cancelAll(this);
		}
		super.onStop();
	}

	public boolean validateEntry() {
		boolean errorRaised = false;

		if (et_username.getText().toString().length() == 0) {
			et_username.setError("Username is required!");
			errorRaised = true;
		}

		if (et_password.getText().toString().length() == 0) {
			et_password.setError("Password is required!");
			errorRaised = true;
		}

		if (et_confirm_password.getText().toString().length() == 0) {
			et_confirm_password.setError("Password is required!");
			errorRaised = true;
		}

		if (!et_confirm_password.getText().toString()
				.equals(et_password.getText().toString())) {
			et_confirm_password.setError("Passwords must match!");
			errorRaised = true;
		}

		if (et_firstName.getText().toString().length() == 0) {
			et_firstName.setError("First name is required!");
			errorRaised = true;
		}

		if (et_lastName.getText().toString().length() == 0) {
			et_lastName.setError("Last name is required!");
			errorRaised = true;
		}

		if (et_studentId.getText().toString().length() == 0) {
			et_studentId.setError("Student ID is required!");
			errorRaised = true;
		}

		return errorRaised;
	}

	public void sendRegister(View view) {
		if (!validateEntry()) {
			JSONObject newUser = new JSONObject();
			try {
				newUser.put("username", et_username.getText().toString());
				newUser.put("password", et_password.getText().toString());
				newUser.put("studentNumber", et_studentId.getText().toString());
				newUser.put("firstName", et_firstName.getText().toString());
				newUser.put("lastName", et_lastName.getText().toString());
				registerRequest(newUser);
			} catch (JSONException e) {
				Log.e("sendRegister", e.toString());
			}
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		}
	}

	private void registerRequest(JSONObject newUser) {

		String resourceURL = Config_RestServer.REST_SERVER_URL
				+ "user/register";
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.POST, resourceURL, newUser,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d("registerRequest", response.toString());
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						switch (error.networkResponse.statusCode) {
						case 400:
							// Invalid user (highly unlikely)
						case 409:
							// user already exists!
						}
					}
				});
		volleyRequestQueue.add(jsonObjectRequest);
	}
}
