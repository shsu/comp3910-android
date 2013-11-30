package ca.bcit.turnip;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ca.bcit.turnip.domain.QuizUser;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class LoginActivity extends Activity {

    private JSONParser parser = new JSONParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, WelcomeActivity.class);

        String username = (String) findViewById(R.id.editText_username)
                .toString();
        String password = (String) findViewById(R.id.editText_password)
                .toString();

        QuizUser userLogin = new QuizUser(username, password);
        new UserLoginTask().execute(userLogin);

        startActivity(intent);
    }

    public void sendNewUser(View view) {
        Intent intent = new Intent(this, NewUserActivity.class);

        startActivity(intent);
    }

    private class UserLoginTask extends AsyncTask<QuizUser, Void, HttpResponse> {
        @SuppressWarnings("unchecked")
        @Override
        protected HttpResponse doInBackground(QuizUser... users) {

            String resourceURL = "http://localhost:8080/a3-server-jhou-shsu/user/";
            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpResponse response = null;

            QuizUser user = users[0];
            JSONObject jsonRequestObject = new JSONObject();
            jsonRequestObject.put("username", user.getUsername());
            jsonRequestObject.put("password", user.getPassword());

            HttpPut putRequest = new HttpPut(resourceURL + "authenticate");
            putRequest.setHeader("content-type", "application/json");

            try {
                putRequest.setEntity(new StringEntity(jsonRequestObject
                        .toJSONString()));

                response = httpClient.execute(putRequest);
                httpClient.getConnectionManager().shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(HttpResponse response) {
            int responseCode;
            String responseBody = null;

            if (response != null) {
                responseCode = response.getStatusLine().getStatusCode();
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                try {
                    responseBody = responseHandler.handleResponse(response);
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                switch (responseCode) {
                case 200:
                    Object object = null;
                    try {
                        object = parser.parse(responseBody);
                        JSONObject jsonResponseObject = (JSONObject) object;
                        String token = (String) jsonResponseObject.get("token");
                        // STORE THIS TOKEN!
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case 401:// incorrect username/password
                    break;
                default:
                    // unknown error.
                }
            }

        }
    }

}
