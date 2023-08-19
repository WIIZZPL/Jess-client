package com.wiizz.jess.client.jessclient.chessboard.pieces;

import com.wiizz.jess.client.jessclient.chessboard.Chessboard;
import javafx.scene.layout.Pane;

import java.util.LinkedList;

import static java.lang.Math.abs;

public class Queen extends ChessPiece{
    public Queen(int row, int column, boolean isWhite, Pane checkerBoard, LinkedList<ChessPiece> pieces) {
        super(row, column, isWhite, "queen", checkerBoard, pieces);
    }

    @Override
    boolean isMoveValid(int deltaRow, int deltaColumn) {
        if (!super.isMoveValid(deltaRow, deltaColumn)) return false;

        //Can move/attack
        ChessPiece piece = Chessboard.getPieceAt(row+deltaRow, column+deltaColumn);
        if(piece != null && piece.isWhite==isWhite) return false;

        //Is blocked+movement check
        if (abs(deltaColumn) == abs(deltaRow)){
            //Bishop like
            int iRow = 0, iColumn = 0;
            iRow += (deltaRow>0)?1:-1;
            iColumn += (deltaColumn>0)?1:-1;
            while(abs(deltaRow) > abs(iRow) && abs(deltaColumn) > abs(iColumn)){
                if(Chessboard.getPieceAt(row+iRow, column+iColumn) != null) return false;
                iRow += (deltaRow>0)?1:-1;
                iColumn += (deltaColumn>0)?1:-1;
            }
            return true;
        } else if (deltaColumn == 0 || deltaRow == 0){
            //Rook like
            int iRow = 0, iColumn = 0;
            if(deltaRow!=0) iRow += (deltaRow>0)?1:-1;
            if(deltaColumn!=0) iColumn += (deltaColumn>0)?1:-1;
            while(abs(deltaRow) > abs(iRow) || abs(deltaColumn) > abs(iColumn)){
                if(Chessboard.getPieceAt(row+iRow, column+iColumn) != null) return false;
                if(deltaRow!=0) iRow += (deltaRow>0)?1:-1;
                if(deltaColumn!=0) iColumn += (deltaColumn>0)?1:-1;
            }
            return true;
        } else return false;
    }
}