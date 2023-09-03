package com.wiizz.jess.client.jessclient.controllers;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class BroadcastListener extends Thread{

    private final MainController controller;
    DatagramSocket socket;
    private final byte[] buf = new byte[50];

    public BroadcastListener(MainController controller) {
        this.controller = controller;
        this.setDaemon(true);

        try {
            this.socket = new DatagramSocket(55555);
        } catch (SocketException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while (!isInterrupted()){
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            try {
                socket.receive(packet);
            } catch (SocketException e) {
                break;
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            String received = new String(packet.getData(), 0, packet.getLength());

            if (!received.matches("^Jess;.*$")) continue;

            String[] strings = received.split(";");

            if (controller.isServerInTable(strings[1])) continue;

            controller.addServerToTable(strings[3], strings[1], strings[2]);

        }

    }

}
