package com.wiizz.jess.client.jessclient.chessboard;
import com.wiizz.jess.client.jessclient.chessboard.pieces.*;
import javafx.scene.layout.Pane;
import java.util.LinkedList;

public class Chessboard {
    Pane checkerboard;
    static boolean isWhiteMove = true;

    static public boolean isWhiteMove() {
        return isWhiteMove;
    }

    static public void moveToggle(){
        isWhiteMove = !isWhiteMove;
    }

    static LinkedList<ChessPiece> pieces = new LinkedList<>();
    public Chessboard(Pane checkerboard){
        this.checkerboard = checkerboard;
        placePieces();
    }

    private void placePieces(){
        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(1, i, false, checkerboard, pieces));
            pieces.add(new Pawn(6, i, true, checkerboard, pieces));
        }
        pieces.add(new Queen(0, 3, false, checkerboard, pieces));
        pieces.add(new Queen(7, 3, true, checkerboard, pieces));
        pieces.add(new Bishop(0, 2, false, checkerboard, pieces));
        pieces.add(new Bishop(7, 2, true, checkerboard, pieces));
        pieces.add(new Bishop(0, 5, false, checkerboard, pieces));
        pieces.add(new Bishop(7, 5, true, checkerboard, pieces));
        pieces.add(new Rook(0, 0, false, checkerboard, pieces));
        pieces.add(new Rook(7, 0, true, checkerboard, pieces));
        pieces.add(new Rook(0, 7, false, checkerboard, pieces));
        pieces.add(new Rook(7, 7, true, checkerboard, pieces));
        pieces.add(new Knight(0, 1, false, checkerboard, pieces));
        pieces.add(new Knight(7, 1, true, checkerboard, pieces));
        pieces.add(new Knight(0, 6, false, checkerboard, pieces));
        pieces.add(new Knight(7, 6, true, checkerboard, pieces));
        pieces.add(new King(0, 4, false, checkerboard, pieces));
        pieces.add(new King(7, 4, true, checkerboard, pieces));
    }

    public static ChessPiece getPieceAt(int row, int column){
        for (ChessPiece piece : pieces) {
            if(piece.getRow() == row && piece.getColumn() == column) return piece;
        }
        return null;
    }
}
