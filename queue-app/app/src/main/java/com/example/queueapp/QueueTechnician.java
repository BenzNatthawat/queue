package com.example.queueapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import androidx.appcompat.app.AppCompatActivity;

public class QueueTechnician extends AppCompatActivity {

    int idQueue;
    Button queueBtn;
    String result;
    Button logoutUI;
    JSONArray objDataResult;
    TextView nameUI, tv_comment1, tv_comment2, tv_comment3, tv_comment4;
    JSONObject objQueue1, objQueue2, objQueue3, objQueue4;
    Button Text2, Text3, Text4;
    SharedData sharedData = SharedData.getInstance();
    Timer t;
    TimerTask tt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_technician);

        queueBtn = (Button) findViewById(R.id.queueBtn);
        Text2 = (Button) findViewById(R.id.text2);
        Text3 = (Button) findViewById(R.id.text3);
        Text4 = (Button) findViewById(R.id.text4);
        logoutUI = (Button) findViewById(R.id.logout);
        nameUI = (TextView) findViewById(R.id.name);
        tv_comment1 = (TextView) findViewById(R.id.tv_comment1);
        tv_comment2 = (TextView) findViewById(R.id.tv_comment2);
        tv_comment3 = (TextView) findViewById(R.id.tv_comment3);
        tv_comment4 = (TextView) findViewById(R.id.tv_comment4);
        nameUI.setText(sharedData.getName());

        t = new Timer();
        tt = new TimerTask() {
            @Override
            public void run(){
                try {
                    result = new RequestAsync("show").execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    objDataResult = new JSONArray(result);
                    System.out.println(objDataResult);
                    for(int i = 0; i < objDataResult.length(); i++) {
                        switch (i) {
                            case 0:  objQueue1 = objDataResult.getJSONObject(0);
                                break;
                            case 1:  objQueue2 = objDataResult.getJSONObject(1);
                                break;
                            case 2:  objQueue3 = objDataResult.getJSONObject(2);
                                break;
                            case 3:  objQueue4 = objDataResult.getJSONObject(3);
                                break;
                        }
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                Text4.setText("0");
                                Text3.setText("0");
                                Text2.setText("0");
                                queueBtn.setText("0");
                                idQueue = 0;

                                switch (objDataResult.length() - 1) {
                                    case 3:  {
                                        Text4.setText(""+objQueue4.get("queueNumber"));
                                        tv_comment4.setText(""+ objQueue4.get("comment"));
                                        if(objQueue4.get("insurance").equals("have")) {
                                            Text4.setBackgroundColor(Color.RED);
                                        } else {
                                            Text4.setBackgroundColor(Color.GREEN);
                                        }
                                    }
                                    case 2:  {
                                        Text3.setText(""+objQueue3.get("queueNumber"));
                                        tv_comment3.setText(""+ objQueue3.get("comment"));
                                        if(objQueue3.get("insurance").equals("have") ) {
                                            Text3.setBackgroundColor(Color.RED);
                                        } else {
                                            Text3.setBackgroundColor(Color.GREEN);
                                        }
                                    }
                                    case 1:  {
                                        Text2.setText(""+objQueue2.get("queueNumber"));
                                        tv_comment2.setText(""+ objQueue2.get("comment"));
                                        if(objQueue2.get("insurance").equals("have")) {
                                            Text2.setBackgroundColor(Color.RED);
                                        } else {
                                            Text2.setBackgroundColor(Color.GREEN);
                                        }
                                    }
                                    case 0:  {
                                        queueBtn.setText(""+objQueue1.get("queueNumber") );
                                        tv_comment1.setText(""+ objQueue1.get("comment"));
                                        idQueue = Integer.parseInt(objQueue1.get("id").toString());
                                        if(objQueue1.get("insurance").equals("have")) {
                                            queueBtn.setBackgroundColor(Color.RED);
                                        } else {
                                            queueBtn.setBackgroundColor(Color.GREEN);
                                        }
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        t.schedule(tt,5000,5000);

        queueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent queueShow = new Intent(QueueTechnician.this, queueShow.class);
                queueShow.putExtra("id", idQueue);
                startActivity(queueShow);
            }
        });

        logoutUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    t.cancel();
                    tt.cancel();
                    result = new RequestAsync("logout").execute().get();
                    Toast.makeText(getApplicationContext(), "logoutUI", Toast.LENGTH_LONG).show();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sharedData.setToken("");
                sharedData.setRole("");
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public class RequestAsync extends AsyncTask<String,String,String> {
        String path = "";
        public RequestAsync (String path) {
            this.path = path;
        }
        @Override
        protected String doInBackground(String... strings) {
            try {
                if(path.equals("logout")) {
                    return RequestHandler.sendGet(BuildConfig.SERVER_URL + "/logout");
                } else {
                    return RequestHandler.sendGet(BuildConfig.SERVER_URL + "/queues");
                }
            } catch(Exception e){
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


