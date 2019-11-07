package com.example.queueapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // Add a different request code for every activity you are starting from here
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;

    Button nextQueueUI;
    TextView queueNumberUI;
    EditText commentUI;

    SharedData sharedData = SharedData.getInstance();
    String token = sharedData.getToken();
    int state = 0;
    JSONObject objDataResult;
    String dataResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextQueueUI = (Button) findViewById(R.id.nextQueue);
        queueNumberUI = (TextView) findViewById(R.id.queueNumber);
        commentUI = (EditText) findViewById(R.id.comment);

        if(token == "") { // go to login
          Toast.makeText(getApplicationContext(), "Don't have Token, go to Login", Toast.LENGTH_LONG).show();
          Intent Login = new Intent(MainActivity.this, Login.class);
          startActivityForResult(Login, SECOND_ACTIVITY_REQUEST_CODE);
        } else { // queue
          if(state==0) { // get queue first time
            dataResult = new RequestAsync("GET").execute().get();
            objDataResult = new JSONObject(dataResult);
            state++;
          } else { // next queue
            dataResult = new RequestAsync("POST", username.getText().toString(), password.getText().toString()).execute().get();
            objDataResult = new JSONObject(dataResult);
          }
        }
    }

    // This method is called when the second activity finishes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

              Toast.makeText(getApplicationContext(), "have Token, go to Queue", Toast.LENGTH_LONG).show();

            }
        }
    }

    public class RequestAsync extends AsyncTask<String,String,String> {

        SharedData sharedData = SharedData.getInstance();
        String username;
        String password;
        String method;

        public RequestAsync(String method, String userName, String password) {
            this.username = userName;
            this.password = password;
            this.method = method;
        }

        public RequestAsync(String method, String userName, String password) {
            this.username = userName;
            this.password = password;
            this.method = method;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                //GET Request
                if(method === 'GET'){
                  return RequestHandler.sendGet("https://prodevsblog.com/android_get.php");
                }

                // POST Request
                else if(method === 'POST'){
                  JSONObject postDataParams = new JSONObject();
                  postDataParams.put("comment", comment);
                  return RequestHandler.sendPost("http://10.0.2.2:5000/api/create", postDataParams);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
    }

}
