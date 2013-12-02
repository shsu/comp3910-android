package ca.bcit.turnip;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class Act_Register extends Activity {

	private RequestQueue volleyRequestQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		volleyRequestQueue = Volley.newRequestQueue(this);
	}

	@Override
	protected void onStop() {
		if (volleyRequestQueue != null)
			volleyRequestQueue.cancelAll(this);
		super.onStop();
	}

	public void sendRegister(View view) {
		EditText et_username = (EditText) findViewById(R.id.editText_username);
		EditText et_password = (EditText) findViewById(R.id.editText_password);
		EditText et_firstName = (EditText) findViewById(R.id.editText_firstName);
		EditText et_lastName = (EditText) findViewById(R.id.editText_lastName);
		EditText et_studentId = (EditText) findViewById(R.id.editText_studentId);

		JSONObject newUser = new JSONObject();
		try {
			newUser.put("username", et_username.getText().toString());
			newUser.put("password", et_password.getText().toString());
			newUser.put("studentNumber", et_studentId.getText().toString());
			newUser.put("firstName", et_firstName.getText().toString());
			newUser.put("lastName", et_lastName.getText().toString());
			registerRequest(newUser);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}

	private void registerRequest(JSONObject newUser) {

		String resourceURL = "http://10.0.3.2:8080/a3-server-jhou-shsu/user/register";
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.POST, resourceURL, newUser,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.i("registerRequestResponse", response.toString());
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {

					}
				});
		volleyRequestQueue.add(jsonObjectRequest);
	}
}
