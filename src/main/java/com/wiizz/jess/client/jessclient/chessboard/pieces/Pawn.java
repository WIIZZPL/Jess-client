package com.wiizz.jess.client.jessclient.chessboard.pieces;

import com.wiizz.jess.client.jessclient.chessboard.Chessboard;
import javafx.scene.layout.Pane;
import java.util.LinkedList;

import static java.lang.Math.abs;

public class Pawn extends ChessPiece{

    boolean hasMoved = false;
    boolean hasDoubleMoved = false;
    public Pawn(int row, int column, boolean isPlayer,  boolean isWhite, Pane checkerBoard, LinkedList<ChessPiece> pieces) {
        super(row, column, isPlayer, isWhite, "pawn", checkerBoard, pieces);
    }

    @Override
    public void move(int row, int column) {
        hasMoved = true;
        hasDoubleMoved = abs(this.row-row) == 2;
        if (abs(column-this.column) == 1 && row-this.row == ((isClientPlayer)?-1:1)){
            ChessPiece enPessantPiece = Chessboard.getPieceAt(this.row, column);
            if (enPessantPiece != null && enPessantPiece.isClientPlayer != isClientPlayer) enPessantPiece.kill();
        }
        super.move(row, column);
        if (row == 0 && isClientPlayer || row == 7 && !isClientPlayer){
            pieces.add(new Queen(row, column, isClientPlayer, isWhite, checkerboard, pieces));
            this.kill();
        }
    }
    @Override
    boolean isMoveValid(int deltaRow, int deltaColumn) {
        if (!super.isMoveValid(deltaRow, deltaColumn)) return false;

        //forwards
        if(deltaColumn == 0){
            //one forward
            if(((deltaRow == -1 && isClientPlayer) || (deltaRow == 1 && !isClientPlayer)) && Chessboard.getPieceAt(row+deltaRow, column+deltaColumn) == null) return true;
            //two forward
            if(!hasMoved && Chessboard.getPieceAt(row+((isClientPlayer)?-1:1), column+deltaColumn) == null) {
                if(((deltaRow == -2 && isClientPlayer) || (deltaRow == 2 && !isClientPlayer)) && Chessboard.getPieceAt(row+deltaRow, column+deltaColumn) == null) return true;
            }
        }
        //attacks
        if(abs(deltaColumn) == 1 && deltaRow == ((isClientPlayer)?-1:1)){
            ChessPiece targetPiece = Chessboard.getPieceAt(row+deltaRow, column+deltaColumn);
            if(targetPiece != null){
                return targetPiece.isClientPlayer != isClientPlayer;
            } else {
                //en pessant
                ChessPiece enPessantPiece = Chessboard.getPieceAt(row, column+deltaColumn);
                return enPessantPiece instanceof Pawn && ((Pawn)enPessantPiece).hasDoubleMoved && enPessantPiece.isClientPlayer != isClientPlayer;
            }

        }
        return false;
    }
}
