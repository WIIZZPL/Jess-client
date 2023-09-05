package com.wiizz.jess.client.jessclient.chessboard;
import com.wiizz.jess.client.jessclient.chessboard.pieces.*;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Chessboard {
    Pane checkerboard;
    static boolean isPlayerMove;
    static boolean isPlayerWhite;
    static BufferedReader in;
    static BufferedWriter out;

    static public boolean isWhiteMove() {
        return isPlayerMove;
    }

    static public void moveToggle(){
        isPlayerMove = !isPlayerMove;
    }

    static LinkedList<ChessPiece> pieces = new LinkedList<>();
    public Chessboard(Pane checkerboard, BufferedReader in, BufferedWriter out, boolean isPlayerWhite){
        this.checkerboard = checkerboard;
        Chessboard.in = in;
        Chessboard.out = out;
        Chessboard.isPlayerWhite = isPlayerWhite;
        Chessboard.isPlayerMove = isPlayerWhite;
        placePieces();
    }

    private void placePieces(){
        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(1, i, isPlayerWhite, isPlayerWhite, checkerboard, pieces));
            pieces.add(new Pawn(6, i, isPlayerWhite, !isPlayerWhite, checkerboard, pieces));
        }
        pieces.add(new Queen(0, 3, isPlayerWhite, isPlayerWhite, checkerboard, pieces));
        pieces.add(new Queen(7, 3, isPlayerWhite, !isPlayerWhite, checkerboard, pieces));
        pieces.add(new Bishop(0, 2, isPlayerWhite, isPlayerWhite, checkerboard, pieces));
        pieces.add(new Bishop(7, 2, isPlayerWhite, !isPlayerWhite, checkerboard, pieces));
        pieces.add(new Bishop(0, 5, isPlayerWhite, isPlayerWhite, checkerboard, pieces));
        pieces.add(new Bishop(7, 5, isPlayerWhite, !isPlayerWhite, checkerboard, pieces));
        pieces.add(new Rook(0, 0, isPlayerWhite, isPlayerWhite, checkerboard, pieces));
        pieces.add(new Rook(7, 0, isPlayerWhite, !isPlayerWhite, checkerboard, pieces));
        pieces.add(new Rook(0, 7, isPlayerWhite, isPlayerWhite, checkerboard, pieces));
        pieces.add(new Rook(7, 7, isPlayerWhite, !isPlayerWhite, checkerboard, pieces));
        pieces.add(new Knight(0, 1, isPlayerWhite, isPlayerWhite, checkerboard, pieces));
        pieces.add(new Knight(7, 1, isPlayerWhite, !isPlayerWhite, checkerboard, pieces));
        pieces.add(new Knight(0, 6, isPlayerWhite, isPlayerWhite, checkerboard, pieces));
        pieces.add(new Knight(7, 6, isPlayerWhite, !isPlayerWhite, checkerboard, pieces));
        pieces.add(new King(0, 4, isPlayerWhite, isPlayerWhite, checkerboard, pieces));
        pieces.add(new King(7, 4, isPlayerWhite, !isPlayerWhite, checkerboard, pieces));
    }

    public static ChessPiece getPieceAt(int row, int column){
        for (ChessPiece piece : pieces) {
            if(piece.getRow() == row && piece.getColumn() == column) return piece;
        }
        return null;
    }

    public static void sendMove(int row1, int column1, int row2, int column2) {
        try {
            out.write(row1 + column1 + row2 + column2);
            out.flush();
        } catch (IOException e){
            throw new RuntimeException();
        }
    }

    public static void getMove(){
        try {
            String response = in.readLine();
            if (response.equals("MATE")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Game end");
                return;
            }

            int[] coords = new int[4];
            for (int i = 0; i < 4; i++){
                coords[i] = Integer.parseInt(response.substring(i, i+1));
            }

            ChessPiece piece = getPieceAt(coords[0], coords[1]);
            piece.move(coords[2], coords[3]);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
