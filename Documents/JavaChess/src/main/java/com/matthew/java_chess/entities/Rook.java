package com.matthew.java_chess.entities;

import java.util.Map;

public class Rook extends Piece {

    PieceMoves pieceMoves;
    private boolean untouched;

    public Rook (int x, int y, String type, boolean color) {
        super(x, y, type, color);
        this.untouched = true;
    }

    public boolean setGrid(Grid grid, int x, int oldX, int y, int oldY) {
        return false;
    }

    public boolean move(int x, int y, Grid grid) {
        return pieceMoves.moveStraight(this, x, y, grid);
    }

}
