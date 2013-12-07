package ca.bcit.turnip;

import org.json.JSONException;
import org.json.JSONObject;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import ca.bcit.turnip.config.Config_RestServer;
import ca.bcit.turnip.helper.MyApp;
import ca.bcit.turnip.helper.VolleyHandler;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

public class LoginActivity extends Activity {

	private RequestQueue volleyRequestQueue;

	private String token;

	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.volleyRequestQueue = MyApp.getRequestQueue();
		token = "";
		setContentView(R.layout.activity_login);

		mLoginFormView = findViewById(R.id.login_form);
		mLoginStatusView = findViewById(R.id.login_status);
		mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);
	}

	@Override
	protected void onStop() {
		if (volleyRequestQueue != null) {
			volleyRequestQueue.cancelAll(this);
		}
		super.onStop();
	}

	public void attemptLogin(View view) {

		// Should not be in onCreate because it is blank upon creation.
		EditText username_field = (EditText) findViewById(R.id.editText_username_login);
		EditText password_field = (EditText) findViewById(R.id.editText_password_login);

		if( username_field.getText().toString().trim().equals("") 
				|| password_field.getText().toString().trim().equals("")) {
		   username_field.setError( "First name is required!" );
		   //You can Toast a message here that the Username is Empty
		} else {
		
		loginRequest(username_field.getText().toString(), password_field
				.getText().toString());

		mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
		showProgress(true);

		Handler handler = new Handler();
		
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
					Log.e("handler delay", "1000ms");
					if (!token.equals("")) {
						sendLogin();
					} else {
						showProgress(false);
					}
			}
		}, 1000);
		}
	}

	public void sendLogin() {
		if (token != null && !token.equals("")) {
			Log.d("in the intent", "in intent");
			Intent intent = new Intent(this, WelcomeActivity.class);
			intent.putExtra("token", token);
			startActivity(intent);
		}
	}

	public void sendRegister(View view) {
		Intent intent = new Intent(this, Act_Register.class);
		startActivity(intent);
	}

	private void loginRequest(String username, String password) {

		String resourceURL = Config_RestServer.REST_SERVER_URL
				+ "user/authenticate";
		JSONObject credentials = new JSONObject();

		try {
			credentials.put("username", username);
			credentials.put("password", password);
		} catch (JSONException e) {
			Log.e("loginRequest", e.toString());
		}

		Log.d("login attempt", credentials.toString());

		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.POST, resourceURL, credentials,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {
							token = response.getString("token");
							Log.d("Auth token in response", token);
						} catch (JSONException e) {
							Log.e("loginRequest", e.toString());
						}
					}
				}, VolleyHandler.getDefaultErrorListner());
		volleyRequestQueue.add(jsonObjectRequest);

	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

}
