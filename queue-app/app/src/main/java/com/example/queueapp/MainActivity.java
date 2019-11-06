package com.example.queueapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    SharedData sharedData = SharedData.getInstance();
    String token = sharedData.getToken();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(token == "") {
          Intent Login = new Intent(MainActivity.this, Login.class);
          startActivity(Login);
        }
    }

}
