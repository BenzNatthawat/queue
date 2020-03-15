package com.example.queueapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.concurrent.ExecutionException;

public class queueShow extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner statusUI;
    String status;
    String result, dataResult;
    JSONObject objDataResult;
    Button confrimUI;
    TextView queueNumbertv, commenttv, insurancetv, statustv, jobnumbertv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_show);

        confrimUI = (Button) findViewById(R.id.btnUpdateQueue);
        Bundle bundle = getIntent().getExtras();
        final int id = bundle.getInt("id");
        queueNumbertv = (TextView) findViewById(R.id.tv_queueNumber);
        commenttv = (TextView) findViewById(R.id.tv_comment);
        insurancetv = (TextView) findViewById(R.id.tv_insurance);
        statustv = (TextView) findViewById(R.id.tv_status);
        jobnumbertv = (TextView) findViewById(R.id.tv_jobnumber);

        statusUI = findViewById(R.id.ed_status);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusUI.setAdapter(adapter);
        statusUI.setOnItemSelectedListener(this);

        try {
            result = new RequestAsync("GET", id).execute().get();
            objDataResult = new JSONObject(result);

            queueNumbertv.setText("หมายเลขคิว" +objDataResult.getString("queueNumber"));
            commenttv.setText("หมายเหตุ : "+objDataResult.getString("comment"));
            if(objDataResult.getString("insurance").equals("have")) {
              insurancetv.setText("มีสิทธิประกัน");
            } else {
              insurancetv.setText("ไม่มีสิทธิประกัน");
            }
            statustv.setText("สถานะ : "+objDataResult.getString("status"));
            jobnumbertv.setText("หมายเลขงาน : "+objDataResult.getString("jobnumber"));

            if(objDataResult.getString("status").equals("completed")) {
                statusUI.setSelection(2);
            } else if(objDataResult.getString("status").equals("proceed")) {
                statusUI.setSelection(1);
            } else {
                statusUI.setSelection(0);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        confrimUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dataResult = new RequestAsync("POST",  status, id).execute().get();
                    finish();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        if(text.equals("รอคิว")) {
            status = "wait";
        } else if(text.equals("กำลังดำเนินการ")){
            status = "proceed";
        } else {
            status = "completed";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class RequestAsync extends AsyncTask<String,String,String> {

        String status;
        String method;
        String result;
        int id;

        public RequestAsync(String method, int id) {
                this.method = method;
                this.id = id;
        }

        public RequestAsync(String method, String status, int id) {
                this.method = method;
                this.status = status;
                this.id = id;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                //GET Request
                if(method == "GET"){
                    result = RequestHandler.sendGet(BuildConfig.SERVER_URL + "/queues/" + id);
                }

                // POST Request
                else if(method == "POST"){
                    JSONObject postDataParams = new JSONObject();
                    postDataParams.put("status", status);
                    result =  RequestHandler.sendPost(BuildConfig.SERVER_URL + "/queues/" + id, postDataParams);
                }
                return result;
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
    }
}
