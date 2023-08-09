package com.wiizz.jess.client.jessclient.chessboard;
import com.wiizz.jess.client.jessclient.chessboard.pieces.ChessPiece;
import com.wiizz.jess.client.jessclient.chessboard.pieces.Pawn;
import javafx.scene.layout.Pane;
import java.util.LinkedList;

public class Chessboard {
    Pane checkerboard;
    LinkedList<ChessPiece> pieces = new LinkedList<>();
    public Chessboard(Pane checkerboard){
        this.checkerboard = checkerboard;
        new Pawn(5, 0, true, checkerboard, pieces);
    }

    public ChessPiece getPieceAt(int row, int column){
        for (ChessPiece piece : pieces) {
            if(piece.getRow() == row && piece.getColumn() == column) return piece;
        }
        return null;
    }
}
