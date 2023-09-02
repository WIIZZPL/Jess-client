package com.wiizz.jess.client.jessclient.controllers;

public class ServerModel {
    private String name;
    private String IP;
    private int port;

    public ServerModel(String name, String IP, int port){
        this.name = name;
        this.IP = IP;
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
