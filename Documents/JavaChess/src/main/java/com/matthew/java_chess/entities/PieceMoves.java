package com.matthew.java_chess.entities;

import java.util.HashMap;
import java.util.Map;

public class PieceMoves {

    public boolean moveStraight(Piece piece, int x, int y, Grid grid) {

        if (!grid.boundaryCheck(x, y)) {
            return false;
        }

        int yToCheck;
        int oldX = piece.position.get("x");
        int oldY = piece.position.get("y");;

        if ((oldX != x && oldY != y) || (oldX == x && oldY == y)) {
            return false;
        }

        if (oldX == x && oldY > y) {
            for (int i = oldY - 1; i > y; i--) {
                int loop = loopThru(piece, x, i, oldX, oldY, grid);

                if (loop == 1) {
                    return true;
                } else if (loop == -1) {
                    return false;
                }
            }
            setValues(piece, grid, x, oldX, y, oldY);
            return true;
        }

        if (oldX == x && oldY < y) {
            for (int i = oldY + 1; i < y; i++) {
                int loop = loopThru(piece, x, i, oldX, oldY, grid);

                if (loop == 1) {
                    return true;
                } else if (loop == -1) {
                    return false;
                }
            }
            setValues(piece, grid, x, oldX, y, oldY);
            return true;
        }

        if (oldY == y && oldX > x) {
            for (int i = oldX - 1; i > x; i--) {
                int loop = loopThru(piece, i, y, oldX, oldY, grid);

                if (loop == 1) {
                    return true;
                } else if (loop == -1) {
                    return false;
                }
            }
            setValues(piece, grid, x, oldX, y, oldY);
            return true;
        }

        if (oldY == y && oldX < x) {
            for (int i = oldX + 1; i <= x; i++) {
                int loop = loopThru(piece, i, y, oldX, oldY, grid);

                if (loop == 1) {
                    return true;
                } else if (loop == -1) {
                    return false;
                }
            }
            setValues(piece, grid, x, oldX, y, oldY);
            return true;
        }
        return false;
    }

    private void setValues(Piece piece, Grid grid, int x, int oldX, int y, int oldY) {
        piece.setGrid(grid, x, oldX, y, oldY);
        piece.setUntouched(false);
    }

    private int loopThru(Piece piece, int x, int y, int oldX, int oldY, Grid grid) {
        Map<Integer, Piece> row = grid.getBoard().get(x);
        Piece otherPiece = row.get(y);

        if (otherPiece != null) {

            Piece oldObj;

            if (piece.checkIfOppositeColor(x, y, grid)) {
                oldObj = otherPiece;
                piece.setGrid(grid, x, oldX, y, oldY);
                piece.setUntouched(false);

                /* TODO */

                grid.splicePiece(oldObj);
                return 1;
            } else {
                return -1;
            }
        }
        return 0;
    }

    public boolean moveDiagonal(Piece piece, int x, int y, Grid grid) {

        if (!grid.boundaryCheck(x, y)) {
            return false;
        }

        int xToCheck;
        int yToCheck;
        int oldX = piece.position.get("x");
        int oldY = piece.position.get("y");
        Map<Integer, Piece> row = new HashMap<>();


        if (oldY == y || oldX == x) {
            return false;
        }

        if (oldX > x && oldY > y) {
            xToCheck = oldX - x;
            yToCheck = oldY - y;

            if (xToCheck != yToCheck) {
                return false;
            }

            for (int i = 1; i <= xToCheck; i++) {
                row = grid.getBoard().get(oldX - i);
                if (row.get(oldY - i) != null) {
                    return checkPiece(grid, piece, oldX - i, oldX, oldY - i, oldY);
                }
            }
            return checkPiece(grid, piece, x, oldX, y, oldY);
        }

        if (oldX > x && oldY < y) {
            xToCheck = oldX - x;
            yToCheck = y - oldY;

            if (xToCheck != yToCheck) {
                return false;
            }

            for (int i = 1; i <= xToCheck; i++) {
                row = grid.getBoard().get(oldX - i);
                if (row.get(oldY + i) != null) {
                    return checkPiece(grid, piece, oldX - i, oldX, oldY + i, oldY);
                }
            }
            return checkPiece(grid, piece, x, oldX, y, oldY);
        }

        if (oldX < x && oldY > y) {
            xToCheck = x - oldX;
            yToCheck = oldY - y;

            if (xToCheck != yToCheck) {
                return false;
            }

            for (int i = 1; i <= xToCheck; i++) {
                row = grid.getBoard().get(oldX + i);
                if (row.get(oldY - i) != null) {
                    return checkPiece(grid, piece, oldX + 1, oldX, oldY + i, oldY);
                }
            }
            return checkPiece(grid, piece, x, oldX, y, oldY);
        }

        if (oldX < x && oldY < y) {
            xToCheck = x - oldX;
            yToCheck = y - oldY;

            if (xToCheck != yToCheck) {
                return false;
            }

            for (int i = 1; i <= xToCheck; i++) {
                row = grid.getBoard().get(oldX + i);
                if (row.get(oldY + i) != null) {
                    return checkPiece(grid, piece, oldX + 1, oldX, oldY + i, oldY);
                }
            }
            return checkPiece(grid, piece, x, oldX, y, oldY);
        }
        return false;
    };


    private boolean checkPiece(Grid grid, Piece piece, int numX, int oldNumX, int numY, int oldNumY) {
        Piece oldObj;

        Map<Integer, Piece> row = grid.getBoard().get(numX);

        if (piece.checkIfOppositeColor(numX, numY, grid)) {
            oldObj = row.get(numY);
            piece.setGrid(grid, numX, oldNumX, numY, oldNumY);
            // grid.splicePiece(oldObj);
            return true;
        }
        return false;
    };
}
