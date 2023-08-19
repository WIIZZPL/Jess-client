package com.wiizz.jess.client.jessclient.chessboard.pieces;

import com.wiizz.jess.client.jessclient.chessboard.Chessboard;
import javafx.scene.layout.Pane;
import java.util.LinkedList;

import static java.lang.Math.abs;

public class Pawn extends ChessPiece{

    boolean hasMoved = false;
    boolean hasDoubleMoved = false;
    public Pawn(int row, int column, boolean isWhite, Pane checkerBoard, LinkedList<ChessPiece> pieces) {
        super(row, column, isWhite, "pawn", checkerBoard, pieces);
    }

    @Override
    protected void move(int row, int column) {
        hasMoved = true;
        hasDoubleMoved = abs(this.row-row) == 2;
        if (abs(column-this.column) == 1 && row-this.row == ((isWhite)?-1:1)){
            ChessPiece enPessantPiece = Chessboard.getPieceAt(this.row, column);
            if (enPessantPiece != null && enPessantPiece.isWhite != isWhite) enPessantPiece.kill();
        }
        super.move(row, column);
        if (row == 0 && isWhite || row == 7 && !isWhite){
            pieces.add(new Queen(row, column, isWhite, checkerboard, pieces));
            this.kill();
        }
    }
    @Override
    boolean isMoveValid(int deltaRow, int deltaColumn) {
        if (!super.isMoveValid(deltaRow, deltaColumn)) return false;

        //forwards
        if(deltaColumn == 0){
            //one forward
            if(((deltaRow == -1 && isWhite) || (deltaRow == 1 && !isWhite)) && Chessboard.getPieceAt(row+deltaRow, column+deltaColumn) == null) return true;
            //two forward
            if(!hasMoved && Chessboard.getPieceAt(row+((isWhite)?-1:1), column+deltaColumn) == null) {
                if(((deltaRow == -2 && isWhite) || (deltaRow == 2 && !isWhite)) && Chessboard.getPieceAt(row+deltaRow, column+deltaColumn) == null) return true;
            }
        }
        //attacks
        if(abs(deltaColumn) == 1 && deltaRow == ((isWhite)?-1:1)){
            ChessPiece targetPiece = Chessboard.getPieceAt(row+deltaRow, column+deltaColumn);
            if(targetPiece != null){
                return targetPiece.isWhite != isWhite;
            } else {
                //en pessant
                ChessPiece enPessantPiece = Chessboard.getPieceAt(row, column+deltaColumn);
                return enPessantPiece instanceof Pawn && ((Pawn)enPessantPiece).hasDoubleMoved && enPessantPiece.isWhite != isWhite;
            }

        }
        return false;
    }
}
