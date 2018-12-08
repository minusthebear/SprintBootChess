package com.matthew.java_chess.entities;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.ObjectUtils;

import java.util.Map;

public class Pawn extends Piece {

    private boolean firstMove;
    private int initYPosition;

    public Pawn(int x, int y, String type, boolean color) {
        super(x, y, type, color);
        this.firstMove = true;
        this.initYPosition = y;
    }

    @Override
    public boolean move(int x, int y, Grid grid) {

        if (NumberUtils.compare(this.position.get("x"), x) == 0) {
            return moveStraight(x, y, grid);
        } else if (Math.abs(this.position.get("x") - 1) == 1 && Math.abs(this.position.get("y") - y) == 1) {

        }
        return true;
    }

    @Override
    public boolean setGrid(Grid grid, int x, int oldX, int y, int oldY) {
        return false;
    }

    private boolean moveStraight(int x, int y, Grid grid) {

        if (!grid.boundaryCheck(x, y)) {
            return false;
        }

        int oldY = this.position.get("y");
        int diff = Math.abs(oldY - y);

        if (this.isUntouched() && diff < 1 && diff >= 3) {
            return false;
        }

        if (!this.isUntouched() && diff != 1) {
            return false;
        }

        if (this.isWhite() && oldY <= y) {
            return false;
        } else if (!this.isWhite() && oldY >= y) {
            return false;
        }

        // Make so doesn't move if another player is in front.

        this.setPosition(x, y);
        grid.setPiece(x, y, this.position.get("x"), this.position.get("y"), this);
        this.setUntouched(false);
        return true;
    }

    private boolean passing(int x, int y, Grid grid) {

        if (!grid.boundaryCheck(x, y)) {
            return false;
        }

        int oldX = this.position.get("x");
        int oldY = this.position.get("y");
        Map<Integer, Map<Integer, Piece>> board = grid.getBoard();
        Map<Integer, Piece> rowX = board.get("x");

        if (ObjectUtils.isEmpty(rowX.get(oldY)) || !ObjectUtils.isEmpty(rowX.get(y))) {
            return false;
        }

        if (rowX.get(oldY).getType().equalsIgnoreCase("Pawn") && rowX.get(oldY).isWhite() != this.isWhite()) {
            // Additional checks are needed


            this.setPosition(x, y);
            grid.setPiece(x, y, oldX, oldY, this);

            return true;
        }
        return false;
    }

    private boolean take(int x, int y, Grid grid) {

        if (!grid.boundaryCheck(x, y)) {
            return false;
        }

        int oldX = this.position.get("x");
        int oldY = this.position.get("y");

        int yToCheck = this.isWhite() ? oldY - 1 : oldY + 1;

        if (yToCheck != y) {
            return false;
        }

        Map<Integer, Map<Integer, Piece>> board = grid.getBoard();
        Map<Integer, Piece> rowX = board.get("x");

        if (!ObjectUtils.isEmpty(rowX.get(y)) && (rowX.get(y).isWhite() != this.isWhite())) {

            this.setPosition(x, y);
            grid.setPiece(x, y, oldX, oldY, this);
            return true;
        }
        return false;
    };

}
