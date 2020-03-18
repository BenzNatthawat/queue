package com.example.queueapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
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

    int idQueue1, idQueue2, idQueue3, idQueue4;
    String result;
    Button logoutUI;
    JSONArray objDataResult;
    TextView nameUI;
    JSONObject objQueue1, objQueue2, objQueue3, objQueue4;
    Button queueBtn1, queueBtn2, queueBtn3, queueBtn4;
    SharedData sharedData = SharedData.getInstance();
    Timer t;
    TimerTask tt;

    Button nextQueue;
    TextView queueNumbertv, commenttv, insurancetv, statustv, jobnumbertv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_technician);

        queueBtn1 = (Button) findViewById(R.id.queueBtn);
        queueBtn2 = (Button) findViewById(R.id.text2);
        queueBtn3 = (Button) findViewById(R.id.text3);
        queueBtn4 = (Button) findViewById(R.id.text4);
        logoutUI = (Button) findViewById(R.id.logout);
        nameUI = (TextView) findViewById(R.id.name);
        nameUI.setText(sharedData.getName());


        nextQueue = (Button) findViewById(R.id.nextQueue);
        queueNumbertv = (TextView) findViewById(R.id.tv_queueNumber);
        commenttv = (TextView) findViewById(R.id.tv_comment);
        insurancetv = (TextView) findViewById(R.id.tv_insurance);
        statustv = (TextView) findViewById(R.id.tv_status);
        jobnumbertv = (TextView) findViewById(R.id.tv_jobnumber);

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

                                queueBtn4.setText("0");
                                queueBtn3.setText("0");
                                queueBtn2.setText("0");
                                queueBtn1.setText("0");
                                idQueue1 = 0;

                                switch (objDataResult.length() - 1) {
                                    case 3:  {
                                        queueBtn4.setText(""+objQueue4.get("queueNumber"));
                                        idQueue4 = Integer.parseInt(objQueue4.get("id").toString());
                                        if(objQueue4.get("insurance").equals("have")) {
                                            queueBtn4.setBackgroundColor(Color.RED);
                                        } else {
                                            queueBtn4.setBackgroundColor(Color.GREEN);
                                        }
                                    }
                                    case 2:  {
                                        queueBtn3.setText(""+objQueue3.get("queueNumber"));
                                        idQueue3 = Integer.parseInt(objQueue3.get("id").toString());
                                        if(objQueue3.get("insurance").equals("have") ) {
                                            queueBtn3.setBackgroundColor(Color.RED);
                                        } else {
                                            queueBtn3.setBackgroundColor(Color.GREEN);
                                        }
                                    }
                                    case 1:  {
                                        queueBtn2.setText(""+objQueue2.get("queueNumber"));
                                        idQueue2 = Integer.parseInt(objQueue2.get("id").toString());
                                        if(objQueue2.get("insurance").equals("have")) {
                                            queueBtn2.setBackgroundColor(Color.RED);
                                        } else {
                                            queueBtn2.setBackgroundColor(Color.GREEN);
                                        }
                                    }
                                    case 0:  {
                                        queueBtn1.setText(""+objQueue1.get("queueNumber") );
                                        idQueue1 = Integer.parseInt(objQueue1.get("id").toString());
                                        if(objQueue1.get("insurance").equals("have")) {
                                            queueBtn1.setBackgroundColor(Color.RED);
                                        } else {
                                            queueBtn1.setBackgroundColor(Color.GREEN);
                                        }

                                        queueNumbertv.setText(""+objQueue1.get("queueNumber"));
                                        commenttv.setText(""+objQueue1.get("comment"));
                                        insurancetv.setText(""+objQueue1.get("insurance"));
                                        statustv.setText(""+objQueue1.get("status"));
                                        jobnumbertv.setText(""+objQueue1.get("jobnumber"));
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

        nextQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //next
            }
        });
        queueBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent queueShow = new Intent(QueueTechnician.this, queueShow.class);
                queueShow.putExtra("id", idQueue1);
                startActivity(queueShow);
            }
        });
        queueBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent queueShow = new Intent(QueueTechnician.this, queueShow.class);
                queueShow.putExtra("id", idQueue2);
                startActivity(queueShow);
            }
        });
        queueBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent queueShow = new Intent(QueueTechnician.this, queueShow.class);
                queueShow.putExtra("id", idQueue3);
                startActivity(queueShow);
            }
        });
        queueBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent queueShow = new Intent(QueueTechnician.this, queueShow.class);
                queueShow.putExtra("id", idQueue4);
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


