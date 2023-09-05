package com.wiizz.jess.client.jessclient.controllers;

import com.wiizz.jess.client.jessclient.chessboard.Chessboard;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.*;
import java.net.Socket;

public class GameController {

    @FXML
    Pane checkerboard;
    Chessboard chessboard;
    Socket clientSocket;
    public void initialize(){
        drawCheckerboard();
    }

    public void setController(Socket clientSocket, boolean isPlayerWhite) {
        this.clientSocket = clientSocket;
        try {
            chessboard = new Chessboard(checkerboard, new BufferedReader(new InputStreamReader(clientSocket.getInputStream())), new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), isPlayerWhite);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void drawCheckerboard(){

        double tileSideLength = checkerboard.getPrefWidth()/8;

        Color whiteTileColour = Color.rgb(227, 193, 111);
        Color blackTileColour = Color.rgb(184, 139, 74);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Rectangle rectangle = new Rectangle();

                rectangle.setX(j*tileSideLength);
                rectangle.setY(i*tileSideLength);

                rectangle.setWidth(tileSideLength);
                rectangle.setHeight(tileSideLength);

                if (i%2==0 ^ j%2==0)
                    rectangle.setFill(blackTileColour);
                else rectangle.setFill(whiteTileColour);

                checkerboard.getChildren().add(rectangle);
            }
        }
    }
}
