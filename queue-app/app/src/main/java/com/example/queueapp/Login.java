package com.example.queueapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity {

    private static final int REGISTER_ACTIVITY_REQUEST_CODE = 1;

    EditText usernameUI;
    EditText passwordUI;
    Button btLoginUI;
    Button btRegisterUI;

    String result;
    JSONObject objDataResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameUI = (EditText) findViewById(R.id.username);
        passwordUI = (EditText) findViewById(R.id.password);
        btLoginUI = (Button) findViewById(R.id.btLogin);
        btRegisterUI = (Button) findViewById(R.id.btRegisterUI);
        final SharedData sharedData = SharedData.getInstance();

        btLoginUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    result = new RequestAsync(usernameUI.getText().toString(), passwordUI.getText().toString()).execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    objDataResult = new JSONObject(result);
                    sharedData.setToken((String) objDataResult.get("token"));
                    sharedData.setName((String) objDataResult.get("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        btRegisterUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "go to register", Toast.LENGTH_LONG).show();
                Intent Register = new Intent(Login.this, Register.class);
                startActivityForResult(Register, REGISTER_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    // This method is called when the second activity finishes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SharedData sharedData = SharedData.getInstance();

        // check that it is the SecondActivity with an OK result
        if (requestCode == REGISTER_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                Toast.makeText(getApplicationContext(), "have Token, go to Queue", Toast.LENGTH_LONG).show();
                if(sharedData.getToken() != "") {
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }
        }
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
    }
}
