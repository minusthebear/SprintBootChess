package com.matthew.java_chess.entities;

public class Queen extends Piece {

    private PieceMoves pieceMoves;

    public Queen (int x, int y, String type, boolean color) {
        super(x, y, type, color);
    }

    public boolean setGrid(Grid grid, int x, int oldX, int y, int oldY) {
        return false;
    }

    public boolean move(int x, int y, Grid grid) {
        int posX = this.position.get("x");
        int posY = this.position.get("y");

        if (Math.abs(posX - x) == Math.abs(posY - y)) {
            return pieceMoves.moveDiagonal(this, x, y, grid);
        } else if ((posX == x && posY != y) || (posY == y && posX != x)) {
            return pieceMoves.moveStraight(this, x, y, grid);
        }
        return false;
    }

}
