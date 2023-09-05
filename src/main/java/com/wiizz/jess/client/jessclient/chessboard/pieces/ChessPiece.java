package com.wiizz.jess.client.jessclient.chessboard.pieces;

import com.wiizz.jess.client.jessclient.chessboard.Chessboard;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.LinkedList;
import java.util.Objects;

public abstract class ChessPiece {
    int row;
    int column;
    boolean isClientPlayer;
    boolean isWhite;
    double tileSize;
    ImageView sprite;
    LinkedList<ChessPiece> pieces;
    Pane checkerboard;

    public ChessPiece(int row, int column, boolean isClientPlayer, boolean isWhite, String pieceName, Pane checkerBoard, LinkedList<ChessPiece> pieces){
        this.row = row;
        this.column = column;
        this.isClientPlayer = isClientPlayer;
        this.isWhite = isWhite;
        this.checkerboard = checkerBoard;

        String filePath = "/com/wiizz/jess/client/jessclient/pieces/"+((isWhite)?"w":"b")+pieceName+".png";
        sprite = new ImageView(new Image(Objects.requireNonNull(ChessPiece.class.getResourceAsStream(filePath))));

        tileSize = checkerBoard.getPrefHeight()/8;
        sprite.setFitHeight(tileSize);
        sprite.setFitWidth(tileSize);
        moveSprite(row, column);
        checkerBoard.getChildren().add(sprite);

        if(isClientPlayer) makeDraggable(sprite);

        this.pieces = pieces;
    }

    private int startingRow;
    private int startingColumn;
    private void makeDraggable(ImageView node){
        node.setOnMousePressed(e -> {
            startingRow = row;
            startingColumn = column;
            sprite.toFront();
            e.consume();
        });

        node.setOnMouseDragged(e -> {
            int targetRow = (int)(e.getY()/tileSize);
            int targetColumn = (int)(e.getX()/tileSize);
            if ((Chessboard.isWhiteMove()== isClientPlayer && isMoveValid(targetRow-row, targetColumn-column)) || (targetRow == startingRow && targetColumn == startingColumn)) {
                moveSprite(targetRow, targetColumn);
            }
            e.consume();
        });

        node.setOnMouseReleased(e -> {
            int targetRow = (int)(e.getY()/tileSize);
            int targetColumn = (int)(e.getX()/tileSize);
            if (Chessboard.isWhiteMove()== isClientPlayer && isMoveValid(targetRow-row, targetColumn-column))
                move(targetRow, targetColumn);
            else moveSprite(startingRow, startingColumn);
            e.consume();
        });
    }

    private void moveSprite(int row, int column){
        sprite.setX(tileSize*column);
        sprite.setY(tileSize*row);
    }

    public void move(int row, int column){
        ChessPiece piece = Chessboard.getPieceAt(row, column);
        if(piece == this) return;
        if(piece != null) {
            piece.kill();
        }
        this.row = row;
        this.column = column;
        moveSprite(row, column);
        Chessboard.sendMove(startingRow, startingColumn, row, column);
        Chessboard.getMove();
        Chessboard.moveToggle();
    }

    public void kill(){
        checkerboard.getChildren().remove(sprite);
        pieces.remove(this);
    }

    boolean isMoveValid(int deltaRow, int deltaColumn){
        return row+deltaRow >= 0 && row+deltaRow < 8 && column+deltaColumn >= 0 && column+deltaColumn < 8;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
