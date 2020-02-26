package com.example.queueapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Add a different request code for every activity you are starting from here
    private static final int LOGIN_ACTIVITY_REQUEST_CODE = 0;
    private static final int QUEUETECH_ACTIVITY_REQUEST_CODE = 1;

    Button nextQueueUI;
    Button logoutUI;
    TextView queueNumberUI;
    TextView queueTechnicianUI;
    TextView queueCommentUI;
    TextView nameUI;
    EditText commentUI;
    EditText jobnumberUI;
    Spinner insuranceUI;
    String insurance;
    SharedData sharedData = SharedData.getInstance();
    JSONObject objDataResult;
    String dataResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextQueueUI = (Button) findViewById(R.id.nextQueue);
        logoutUI = (Button) findViewById(R.id.logout);
        queueNumberUI = (TextView) findViewById(R.id.queueNumber);
        queueCommentUI = (TextView) findViewById(R.id.queueComment);
        queueTechnicianUI = (TextView) findViewById(R.id.queueTechnician);
        commentUI = (EditText) findViewById(R.id.comment);
        nameUI = (TextView) findViewById(R.id.name);
        jobnumberUI = (EditText) findViewById(R.id.jobnumber);

        insuranceUI = findViewById(R.id.insurance);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.insurance, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        insuranceUI.setAdapter(adapter);
        insuranceUI.setOnItemSelectedListener(this);

        if(sharedData.getToken() == "") { // go to login
            Toast.makeText(getApplicationContext(), "กรุณาเข้าสู่ระบบ", Toast.LENGTH_LONG).show();
            Intent Login = new Intent(MainActivity.this, Login.class);
            startActivityForResult(Login, LOGIN_ACTIVITY_REQUEST_CODE);
        }

        nextQueueUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sharedData.getToken() == "") { // go to login
                    Toast.makeText(getApplicationContext(), "กรุณาเข้าสู่ระบบ", Toast.LENGTH_LONG).show();
                    Intent Login = new Intent(MainActivity.this, Login.class);
                    startActivityForResult(Login, LOGIN_ACTIVITY_REQUEST_CODE);
                } else { // queue
                    try {
                        dataResult = new RequestAsync("POST", commentUI.getText().toString(), jobnumberUI.getText().toString(), insurance).execute().get();
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
                        nameUI.setText(sharedData.getName());
                        commentUI.setText("");
                        jobnumberUI.setText("");
                        insuranceUI.setSelection(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        logoutUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedData.setToken("");
                Intent Login = new Intent(MainActivity.this, Login.class);
                startActivityForResult(Login, LOGIN_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        insurance = text;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    // This method is called when the second activity finishes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        SharedData sharedData = SharedData.getInstance();
        // check that it is the SecondActivity with an OK result
        if (requestCode == LOGIN_ACTIVITY_REQUEST_CODE || requestCode == QUEUETECH_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK
                if(sharedData.getRole().equals("technician")) {
                    Intent queueTechnician = new Intent(MainActivity.this, QueueTechnician.class);
                    startActivityForResult(queueTechnician, QUEUETECH_ACTIVITY_REQUEST_CODE);
                } else {
                    if(sharedData.getToken() == "") { // go to login
                        Toast.makeText(getApplicationContext(), "กรุณาเข้าสู่ระบบ", Toast.LENGTH_LONG).show();
                        Intent Login = new Intent(MainActivity.this, Login.class);
                        startActivityForResult(Login, LOGIN_ACTIVITY_REQUEST_CODE);
                    } else {
                        nameUI.setText(sharedData.getName());
                        Toast.makeText(getApplicationContext(), "เข้าสู่ระบบสำเร็จ", Toast.LENGTH_LONG).show();
                    }
                }
            } else {
                Toast.makeText(getApplicationContext(), "กรุณาเข้าสู่ระบบ", Toast.LENGTH_LONG).show();
                Intent Login = new Intent(MainActivity.this, Login.class);
                startActivityForResult(Login, LOGIN_ACTIVITY_REQUEST_CODE);
            }
        }
    }

    public class RequestAsync extends AsyncTask<String,String,String> {

        String comment, insurance, jobnumber;
        String method;
        String result;

        public RequestAsync(String method, String comment, String jobnumber, String insurance) {
            this.method = method;
            this.comment = comment;
            this.insurance = insurance;
            this.jobnumber = jobnumber;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                //GET Request
                if(method == "GET"){
                    result = RequestHandler.sendGet(BuildConfig.SERVER_URL + "/queues");
                }

                // POST Request
                else if(method == "POST"){
                  JSONObject postDataParams = new JSONObject();
                    postDataParams.put("comment", comment);
                    postDataParams.put("insurance", insurance);
                    postDataParams.put("jobnumber", jobnumber);
                    result =  RequestHandler.sendPost(BuildConfig.SERVER_URL + "/queues/create", postDataParams);
                }
                return result;
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
    }

}
