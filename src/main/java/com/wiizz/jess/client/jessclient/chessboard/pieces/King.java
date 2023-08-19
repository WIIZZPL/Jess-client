package com.wiizz.jess.client.jessclient.chessboard.pieces;

import com.wiizz.jess.client.jessclient.chessboard.Chessboard;
import javafx.scene.layout.Pane;

import java.util.LinkedList;

import static java.lang.Math.abs;

public class King extends ChessPiece{

    boolean hasMoved = false;
    public King(int row, int column, boolean isWhite, Pane checkerBoard, LinkedList<ChessPiece> pieces) {
        super(row, column, isWhite, "king", checkerBoard, pieces);
    }

    @Override
    protected void move(int row, int column) {
        hasMoved = true;
        if(abs(column-this.column) == 2){
            ChessPiece rook = Chessboard.getPieceAt(this.row, (column==2)?0:7);
            assert rook != null;
            rook.move(this.row, (column==2)?3:5);
            Chessboard.moveToggle();
        }
        super.move(row, column);
    }

    @Override
    boolean isMoveValid(int deltaRow, int deltaColumn) {
        if (!super.isMoveValid(deltaRow, deltaColumn)) return false;

        //Standard move
        if (abs(deltaRow) <= 1 && abs(deltaColumn) <= 1) {
            ChessPiece piece = Chessboard.getPieceAt(this.row + deltaRow, this.column + deltaColumn);
            if (piece != null && this.isWhite == piece.isWhite) return false;

            int oldRow = this.row, oldColumn = this.column;
            this.row = this.row+deltaRow;
            this.column = this.column+deltaColumn;
            boolean r = !isSpaceAttacked(this.row, this.column);
            this.row = oldRow;
            this.column = oldColumn;
            return r;
        }

        //Castling
        if (deltaRow == 0 && abs(deltaColumn) == 2 && !isInCheck() && !hasMoved){
            //Rook checks
            int rookColumn = (deltaColumn==-2)?0:7;
            ChessPiece rook = Chessboard.getPieceAt(this.row, rookColumn);
            if (rook instanceof Rook && !((Rook) rook).hasMoved){
                //check for empty space in b column
                if (deltaColumn == -2 && Chessboard.getPieceAt(this.row, 1) != null) return false;
                for (int iColumn = this.column+deltaColumn; iColumn != this.column; iColumn += (deltaColumn==-2)?1:-1){
                    if(Chessboard.getPieceAt(this.row, iColumn) != null && !isSpaceAttacked(this.row, iColumn)) return false;
                }
                return true;
            }
        }

        return false;
    }

    boolean isSpaceAttacked(int row, int column){
        for(ChessPiece piece : pieces){
            if (piece.isWhite != this.isWhite){
                if (piece.isMoveValid(row-piece.row, column-piece.column)){
                    return true;
                }
            }
        }
        return false;
    }

    boolean isInCheck(){
        return isSpaceAttacked(this.row, this.column);
    }

    boolean canMove(){
        for (int iRow = -1; iRow <= 1; iRow++){
            for (int iColumn = -1; iColumn <= 1; iColumn++){
                if (iRow == 0 && iColumn == 0) continue;
                if (isMoveValid(iRow, iColumn)) return true;
            }
        }
        return false;
    }

    boolean isInMate(){
        return !canMove() && isInCheck();
    }
}