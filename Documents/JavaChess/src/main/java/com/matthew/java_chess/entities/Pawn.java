package com.matthew.java_chess.entities;

import org.apache.commons.lang3.math.NumberUtils;

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

        if (NumberUtils.compare(this.position.get("y"), y) == 0) {
            return moveStraight(x, y, grid);
        } else if (true) {

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

        int positionX = this.position.get("x");
        int oldY = this.position.get("y");
        int diff = Math.abs(oldY - y);

        if (positionX != x) {
            return false;
        }

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
//
//    private boolean passing(int x, int y, Grid grid) {
//
//        if (!grid.boundaryCheck(x, y)) {
//            return false;
//        }
//
//        var piece = this,
//                oldX = piece.position.x,
//                oldY = piece.position.y,
//                board = grid.grid,
//                diffX = Math.abs(x - oldX),
//                diffY = Math.abs(y - oldY);
//
//        if (diffX !== 1 || diffY !== 1) {
//            return false;
//        }
//
//        if (!board[x][oldY] || board[x][y]) {
//            return false;
//        }
//
//        if (board[x][oldY].type === 'Pawn' && board[x][oldY].white !== piece.white) {
//            board[oldX][oldY] = null;
//            board[x][y] = piece;
//            piece.setPosition(x, y);
//            return true;
//        }
//        return false;
//    }
//
//    Pawn.prototype.take = function(x, y, grid) {
//
//        if (!grid.boundaryCheck(x, y)) {
//            return false;
//        }
//
//        var board = grid.grid,
//                yToCheck,
//                piece = this,
//                oldX = piece.position.x,
//                oldY = piece.position.y,
//                diffX = Math.abs(x - oldX),
//                diffY = Math.abs(y - oldY);
//
//        if (diffX !== 1 || diffY !== 1) {
//            return false;
//        }
//
//        yToCheck = !!piece.white ? oldY - 1 : oldY + 1;
//
//        if (yToCheck !== y) {
//            return false;
//        }
//
//        if (board[x][y] && (board[x][y].white !== piece.white)) {
//            board[oldX][oldY] = null;
//            board[x][y] = piece;
//            piece.setPosition(x, y);
//            return true;
//        }
//        return false;
//    };

}
