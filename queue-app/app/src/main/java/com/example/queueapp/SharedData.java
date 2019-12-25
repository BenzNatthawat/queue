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
    private String name = "";
    private String role = "";

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}