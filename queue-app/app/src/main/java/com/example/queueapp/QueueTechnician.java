package com.example.queueapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class QueueTechnician extends AppCompatActivity {

    String result;
    Button logoutUI;
    JSONArray objDataResult;
    TextView nameUI;
    JSONObject objQueue1, objQueue2, objQueue3, objQueue4;
    TextView Text1, Text2, Text3, Text4;
    SharedData sharedData = SharedData.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_technician);

        Text1 = (TextView) findViewById(R.id.text1);
        Text2 = (TextView) findViewById(R.id.text2);
        Text3 = (TextView) findViewById(R.id.text3);
        Text4 = (TextView) findViewById(R.id.text4);
        logoutUI = (Button) findViewById(R.id.logout);
        nameUI = (TextView) findViewById(R.id.name);

        nameUI.setText(sharedData.getName());
        new Timer().schedule(new TimerTask() {
            @Override
            public void run(){
                try {
                    result = new RequestAsync().execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    objDataResult = new JSONArray(result);
                    objQueue1 = objDataResult.getJSONObject(0);
                    objQueue2 = objDataResult.getJSONObject(1);
                    objQueue3 = objDataResult.getJSONObject(2);
                    objQueue4 = objDataResult.getJSONObject(3);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Text1.setText(""+objQueue1.get("queueNumber"));
                                Text2.setText(""+objQueue2.get("queueNumber"));
                                Text3.setText(""+objQueue3.get("queueNumber"));
                                Text4.setText(""+objQueue4.get("queueNumber"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },0,20000);


        logoutUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedData.setToken("");
                sharedData.setRole("");
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public class RequestAsync extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                return RequestHandler.sendGet(BuildConfig.SERVER_URL + "/queues/show");
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
    }

    public static void setTimeoutSync(Runnable runnable, int delay) {
        try {
            Thread.sleep(delay);
            runnable.run();
        }
        catch (Exception e){
            System.err.println(e);
        }
    }

}


