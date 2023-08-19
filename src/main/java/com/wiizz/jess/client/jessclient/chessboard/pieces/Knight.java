package com.wiizz.jess.client.jessclient.chessboard.pieces;
import com.wiizz.jess.client.jessclient.chessboard.Chessboard;
import javafx.scene.layout.Pane;

import java.util.LinkedList;

import static java.lang.Math.abs;

public class Knight extends ChessPiece{
    public Knight(int row, int column, boolean isWhite, Pane checkerBoard, LinkedList<ChessPiece> pieces) {
        super(row, column, isWhite, "knight", checkerBoard, pieces);
    }

    @Override
    boolean isMoveValid(int deltaRow, int deltaColumn) {
        if (!super.isMoveValid(deltaRow, deltaColumn)) return false;

        //Can move/attack
        ChessPiece piece = Chessboard.getPieceAt(row+deltaRow, column+deltaColumn);
        if(piece != null && piece.isWhite==isWhite) return false;

        //Is move a knight's move
        return abs(deltaRow) + abs(deltaColumn) == 3 && deltaRow != 0 && deltaColumn != 0;
    }
}