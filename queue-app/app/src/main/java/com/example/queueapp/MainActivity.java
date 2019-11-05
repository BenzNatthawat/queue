package com.example.queueapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btLogin = (Button) findViewById(R.id.btLogin);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RequestAsync(username.getText().toString(), password.getText().toString()).execute();
                if(true) {
                    Intent B1 = new Intent(MainActivity.this, callQueue.class);
                    startActivity(B1);
                } else {
                    Toast.makeText(getApplicationContext(), "รหัสผ่านไม่ถูกต้อง", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public class RequestAsync extends AsyncTask<String,String,String> {

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

                return RequestHandler.sendPost("http://10.0.2.2:5000/api/login", postDataParams, "");
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
