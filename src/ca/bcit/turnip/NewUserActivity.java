package ca.bcit.turnip;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;

import ca.bcit.turnip.domain.QuizUser;
import android.os.AsyncTask;
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

    public static String username;
    String password;
    public static String firstName;
    public static String lastName;
    String studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
    }

    public void sendRegister(View view) {
        et_username = (EditText) findViewById(R.id.editText_username);
        et_password = (EditText) findViewById(R.id.editText_password);
        et_firstName = (EditText) findViewById(R.id.editText_firstName);
        et_lastName = (EditText) findViewById(R.id.editText_lastName);
        et_studentId = (EditText) findViewById(R.id.editText_studentId);

        QuizUser newUser = new QuizUser(username, password, firstName,
                lastName, studentId);

        new UserRegisterTask().execute(newUser);
    }

    private class UserRegisterTask extends AsyncTask<QuizUser, Void, Integer> {
        @SuppressWarnings("unchecked")
        @Override
        protected Integer doInBackground(QuizUser... users) {

            String resourceURL = "http://localhost:8080/a3-server-jhou-shsu/user/";

            Integer responseCode = 200;

            QuizUser user = users[0];
            JSONObject jsonRequestObject = new JSONObject();
            jsonRequestObject.put("username", user.getUsername());
            jsonRequestObject.put("password", user.getPassword());
            jsonRequestObject.put("studentNumber", user.getStudentNumber());
            jsonRequestObject.put("firstName", user.getFirstName());
            jsonRequestObject.put("lastName", user.getLastName());

            HttpPost postRequest = new HttpPost(resourceURL + "register");
            postRequest.setHeader("content-type", "application/json");

            try {
                postRequest.setEntity(new StringEntity(jsonRequestObject
                        .toJSONString()));
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpResponse response = httpClient.execute(postRequest);
                responseCode = response.getStatusLine().getStatusCode();
                httpClient.getConnectionManager().shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return responseCode;
        }

        @Override
        protected void onPostExecute(Integer responseCode) {
            switch (responseCode) {
            case 201:// created, success, start intent to go back to login page.
                break;
            case 400:// bad user data. Should not occur if validation is done
                     // android side.
                break;
            case 409:// user already exists.
                break;
            default:
                // unknown error.
            }
        }
    }
}
