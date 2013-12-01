package ca.bcit.turnip;

import org.json.JSONException;
import org.json.JSONObject;

import ca.bcit.turnip.volley.toolbox.MyJsonObjectRequest;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.util.Log;
import android.view.View;

public class LoginActivity extends Activity {

	private RequestQueue volleyRequestQueue;

	private String token;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		volleyRequestQueue = Volley.newRequestQueue(this);
	}

	@Override
	protected void onStop() {
		if (volleyRequestQueue != null)
			volleyRequestQueue.cancelAll(this);
		super.onStop();
	}

	public void sendLogin(View view) {

		// String username = ((Editable)
		// findViewById(R.id.editText_username_login)).toString();
		// String password = ((Editable)
		// findViewById(R.id.editText_password_login)).toString();

		loginRequest("admin", "admin");
		if (token != null && !token.equals("")) {
			Intent intent = new Intent(this, WelcomeActivity.class);
			intent.putExtra("token", token);
			startActivity(intent);
		}

	}

	public void sendRegister(View view) {
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
	}

	private void loginRequest(String username, String password) {

		String resourceURL = "http://10.0.2.2:8080/a3-server-jhou-shsu/user/authenticate";
		JSONObject credentials = new JSONObject();

		try {
			credentials.put("username", username);
			credentials.put("password", password);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		Log.i("login attempt", credentials.toString());

		MyJsonObjectRequest jsonObjectRequest = new MyJsonObjectRequest(
				Request.Method.PUT, resourceURL, credentials, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {
							token = response.getString("token");
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {

					}
				});
		volleyRequestQueue.add(jsonObjectRequest);
	}
}
