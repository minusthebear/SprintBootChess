package com.matthew.java_chess.entities;

import java.util.Map;

public class Knight extends Piece {
    PieceMoves pieceMoves;
    private boolean untouched;

    public Knight (int x, int y, String type, boolean color) {
        super(x, y, type, color);
    }

    public boolean setGrid(Grid grid, int x, int oldX, int y, int oldY) {
        return false;
    }

    public boolean move(int x, int y, Grid grid) {

        if (!grid.boundaryCheck(x, y)) {
            return false;
        }

        int oldX = this.position.get("x");
        int oldY = this.position.get("y");

        return workingOnTheKnightMoves(x, y, oldX, oldY, grid);
    }

    private boolean workingOnTheKnightMoves(int x, int y, int oldX, int oldY, Grid grid) {

        if (oldY == y || oldX == x) {
            return false;
        } else if (checkWhenYisTwoAndXisOne(x, y, oldX, oldY)) {
            checkBothAndSetPosition(x, y, oldX, x, grid);
            grid.setPiece(x, y, oldX, oldY, this);
            return true;
        } else if (checkWhenXisTwoAndYisOne(x, y, oldX, oldY)) {
            checkBothAndSetPosition(x, y, oldY, y, grid);
            grid.setPiece(x, y, oldX, oldY, this);
            return true;
        }
        return false;
    }

    private boolean checkBothAndSetPosition(int x, int y, int oldNum, int nouveauNum, Grid grid) {

        if (checkBoth(oldNum, nouveauNum, 1) && checkIfOppositeColor(x, y, grid)) {
            this.setPosition(x, y);
            return true;
        } else {
            return false;
        }
    }

    private boolean checkWhenYisTwoAndXisOne(int x, int y, int oldX, int oldY) {
        return (oldGreaterThanNew(y, oldY, 2) || newGreaterThanOld(y, oldY, 2)) &&
                (oldGreaterThanNew(x, oldX, 1) || newGreaterThanOld(x, oldX, 1));
    }

    private boolean checkWhenXisTwoAndYisOne(int x, int y, int oldX, int oldY) {
        return (oldGreaterThanNew(x, oldX, 2) || newGreaterThanOld(x, oldX, 2)) &&
                (oldGreaterThanNew(y, oldY, 1) || newGreaterThanOld(y, oldY, 1));
    }

    private boolean oldGreaterThanNew(int n, int o, int num) {
        return (o > n && (o - n == num));
    }

    private boolean newGreaterThanOld(int n, int o, int num) {
        return (o < n && (n - o == num));
    }

    private boolean checkBoth(int n, int o, int num) {
        return oldGreaterThanNew(n, o, num) || newGreaterThanOld(n, o, num);
    }
}
