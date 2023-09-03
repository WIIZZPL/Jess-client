package com.wiizz.jess.client.jessclient.controllers;

public class GameModel {
    private String user;
    private String time;
    private boolean password;

    public GameModel(String user, String time, boolean password){
        this.user = user;
        this.time = time;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isPassword() {
        return password;
    }

    public void setPassword(boolean password) {
        this.password = password;
    }
}
