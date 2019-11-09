package com.example.queueapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner roleUI;
    EditText usernameUI;
    EditText passwordUI;
    EditText nameUI;
    Button btRegisterUI;

    String result;
    JSONObject objDataResult;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final SharedData sharedData = SharedData.getInstance();

        roleUI = findViewById(R.id.role);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleUI.setAdapter(adapter);
        roleUI.setOnItemSelectedListener(this);

        usernameUI = (EditText) findViewById(R.id.username);
        passwordUI = (EditText) findViewById(R.id.password);
        nameUI = (EditText) findViewById(R.id.name);
        btRegisterUI = (Button) findViewById(R.id.register);

        btRegisterUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    result = new Register.RequestAsync(usernameUI.getText().toString(), passwordUI.getText().toString(), nameUI.getText().toString(), role).execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    objDataResult = new JSONObject(result);
                    Toast.makeText(getApplicationContext(), "token: " + (String) objDataResult.get("token"), Toast.LENGTH_LONG).show();
                    sharedData.setToken((String) objDataResult.get("token"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(sharedData.getToken() !== ""){
                    finish();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        role = text;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class RequestAsync extends AsyncTask<String,String,String> {

        SharedData sharedData = SharedData.getInstance();
        String username, password, name, role;
        public RequestAsync(String userName, String password, String name, String role) {
            this.username = userName;
            this.password = password;
            this.name = name;
            this.role = role;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("username", username);
                postDataParams.put("password", password);
                postDataParams.put("name", name);
                postDataParams.put("role", role);

                return RequestHandler.sendPost("http://10.0.2.2:5000/api/register", postDataParams);
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
    }
}
