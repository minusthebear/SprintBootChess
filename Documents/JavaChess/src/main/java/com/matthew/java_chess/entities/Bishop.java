package com.matthew.java_chess.entities;

public class Bishop extends Piece {
    PieceMoves piecemoves;

    public Bishop (int x, int y, String type, boolean color) {
        super(x, y, type, color);
    }

    public boolean setGrid(Grid grid, int x, int oldX, int y, int oldY) {
        return false;
    }

    public boolean move(int x, int y, Grid grid) {
        return piecemoves.moveDiagonal(this, x, y, grid);
    }

}
