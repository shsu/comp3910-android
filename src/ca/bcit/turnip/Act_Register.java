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
import ca.bcit.turnip.helper.VolleyHandler;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

/**
 * The Class Act_Register.
 */
public class Act_Register extends Activity {

	/** The et_username. */
	EditText et_username;

	/** The et_password. */
	EditText et_password;

	/** The et_confirm_password. */
	EditText et_confirm_password;

	/** The et_first name. */
	EditText et_firstName;

	/** The et_last name. */
	EditText et_lastName;

	/** The et_student id. */
	EditText et_studentId;

	/** The volley request queue. */
	private RequestQueue volleyRequestQueue;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		if (volleyRequestQueue != null) {
			volleyRequestQueue.cancelAll(this);
		}
		super.onStop();
	}

	/**
	 * Validate entry.
	 * 
	 * @return true, if successful
	 */
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

	/**
	 * Send register.
	 * 
	 * @param view
	 *            the view
	 */
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

	/**
	 * Register request.
	 * 
	 * @param newUser
	 *            the new user
	 */
	private void registerRequest(JSONObject newUser) {

		String resourceURL = Config_RestServer.REST_SERVER_URL
				+ "user/register";
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.POST, resourceURL, newUser,
				VolleyHandler.getDefaultResponseListner(),
				new Response.ErrorListener() {

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
