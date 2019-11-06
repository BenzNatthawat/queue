package com.example.queueapp;

public class SharedData {

    private static SharedData instance = new SharedData();
    public static SharedData getInstance() {
        return instance;
    }
    public static void setInstance(SharedData instance) {
        SharedData.instance = instance;
    }


    private String token = "";

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }



}