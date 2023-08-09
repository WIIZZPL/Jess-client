package com.wiizz.jess.client.jessclient.chessboard;
import com.wiizz.jess.client.jessclient.chessboard.pieces.ChessPiece;
import com.wiizz.jess.client.jessclient.chessboard.pieces.Pawn;
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
    }

    public static ChessPiece getPieceAt(int row, int column){
        for (ChessPiece piece : pieces) {
            if(piece.getRow() == row && piece.getColumn() == column) return piece;
        }
        return null;
    }
}
