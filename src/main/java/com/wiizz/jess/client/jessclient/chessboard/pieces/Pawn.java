package com.wiizz.jess.client.jessclient.chessboard.pieces;

import javafx.scene.layout.Pane;
import java.util.LinkedList;

public class Pawn extends ChessPiece{
    public Pawn(int row, int column, boolean isWhite, Pane checkerBoard, LinkedList<ChessPiece> pieces) {
        super(row, column, isWhite, "pawn", checkerBoard, pieces);
    }
}
