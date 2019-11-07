package com.example.queueapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    EditText usernameUI;
    EditText passwordUI;
    Button btLoginUI;

    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameUI = (EditText) findViewById(R.id.username);
        passwordUI = (EditText) findViewById(R.id.password);
        btLoginUI = (Button) findViewById(R.id.btLogin);

        btLoginUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = new RequestAsync(usernameUI.getText().toString(), passwordUI.getText().toString()).execute().get();
                console.log(result)
                // sharedData.setToken(result.token);
                // Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public class RequestAsync extends AsyncTask<String,String,String> {

        SharedData sharedData = SharedData.getInstance();
        String username, password;
        public RequestAsync(String userName, String password) {
            this.username = userName;
            this.password = password;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                //GET Request
                //return RequestHandler.sendGet("https://prodevsblog.com/android_get.php");

                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("username", username);
                postDataParams.put("password", password);

                return RequestHandler.sendPost("http://10.0.2.2:5000/api/login", postDataParams);
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s!=null){
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        }
    }
}
