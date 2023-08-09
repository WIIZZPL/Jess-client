package com.wiizz.jess.client.jessclient.chessboard.pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.LinkedList;
import java.util.Objects;

public abstract class ChessPiece {
    int row;
    int column;
    boolean isWhite;
    double tileSize;
    ImageView sprite;
    LinkedList<ChessPiece> pieces;
    public ChessPiece(int row, int column, boolean isWhite, String pieceName, Pane checkerBoard, LinkedList<ChessPiece> pieces){
        this.row = row;
        this.column = column;
        this.isWhite = isWhite;
        String filePath = "/com/wiizz/jess/client/jessclient/pieces/"+((isWhite)?"w":"b")+pieceName+".png";
        System.out.println(filePath);
        sprite = new ImageView(new Image(Objects.requireNonNull(ChessPiece.class.getResourceAsStream(filePath))));
        tileSize = checkerBoard.getPrefHeight()/8;
        sprite.setFitHeight(tileSize);
        sprite.setFitWidth(tileSize);
        move(row, column);
        checkerBoard.getChildren().add(sprite);
        this.pieces = pieces;
        pieces.add(this);
    }

    private void move(int row, int column){
        sprite.setX(tileSize*column);
        sprite.setY(tileSize*row);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isWhite(){
        return isWhite;
    }
}
