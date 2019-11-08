package com.example.queueapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    // Add a different request code for every activity you are starting from here
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;

    Button nextQueueUI;
    TextView queueNumberUI;
    TextView queueTechnicianUI;
    TextView queueCommentUI;
    EditText commentUI;

    SharedData sharedData = SharedData.getInstance();
    JSONObject objDataResult;
    String dataResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextQueueUI = (Button) findViewById(R.id.nextQueue);
        queueNumberUI = (TextView) findViewById(R.id.queueNumber);
        queueCommentUI = (TextView) findViewById(R.id.queueComment);
        queueTechnicianUI = (TextView) findViewById(R.id.queueTechnician);
        commentUI = (EditText) findViewById(R.id.comment);

        if(sharedData.getToken() == "") { // go to login
            Toast.makeText(getApplicationContext(), "Don't have Token, go to Login", Toast.LENGTH_LONG).show();
            Intent Login = new Intent(MainActivity.this, Login.class);
            startActivityForResult(Login, SECOND_ACTIVITY_REQUEST_CODE);
        }

        nextQueueUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sharedData.getToken() == "") { // go to login
                    Toast.makeText(getApplicationContext(), "Don't have Token, go to Login", Toast.LENGTH_LONG).show();
                    Intent Login = new Intent(MainActivity.this, Login.class);
                    startActivityForResult(Login, SECOND_ACTIVITY_REQUEST_CODE);
                } else { // queue
                    try {
                        dataResult = new RequestAsync("POST", commentUI.getText().toString()).execute().get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    try {
                        objDataResult = new JSONObject(dataResult);
                        queueNumberUI.setText("คิวที่: " + objDataResult.get("queueNumber"));
                        queueTechnicianUI.setText("ช่าง: " + objDataResult.get("name"));
                        queueCommentUI.setText("หมายเหตุ: " + objDataResult.get("comment"));
                        commentUI.setText("");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


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
        String comment;
        String method;
        String result;
        public RequestAsync(String method) {
            this.method = method;
        }

        public RequestAsync(String method, String comment) {
            this.method = method;
            this.comment = comment;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                //GET Request
                if(method == "GET"){
                    result = RequestHandler.sendGet("http://10.0.2.2:5000/api/queues");
                }

                // POST Request
                else if(method == "POST"){
                  JSONObject postDataParams = new JSONObject();
                  postDataParams.put("comment", comment);
                    result =  RequestHandler.sendPost("http://10.0.2.2:5000/api/queues/create", postDataParams);
                }
                return result;
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
    }

}
