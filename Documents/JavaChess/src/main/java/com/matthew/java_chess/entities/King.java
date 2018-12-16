package com.matthew.java_chess.entities;

import java.util.HashMap;
import java.util.Map;

public class King extends Piece {
    PieceMoves pieceMoves;
    private boolean untouched;

    public King (int x, int y, String type, boolean color) {
        super(x, y, type, color);
        this.untouched = true;
    }

    public boolean setGrid(Grid grid, int x, int oldX, int y, int oldY) {
        return false;
    }

    public boolean move(int x, int y, Grid grid) {

        if (!grid.boundaryCheck(x, y)) {
            return false;
        }

        if (checkIfInCheck(x, y, grid)) {
            return false;
        }

        int posX = this.position.get("x");
        int posY = this.position.get("y");

        if (Math.abs(posX - x) == Math.abs(posY - y)) {
            return moveDiagonal(x, y, grid);
        } else if ((posX == x && posY != y) || (posY == y && posX != x)) {
            return moveStraight(x, y, grid);
        }
        return false;
    }

    public boolean moveStraight(int x, int y, Grid grid) {

        int oldX = this.position.get("x");
        int oldY = this.position.get("y");

        if (!moveStraightCheck(x, y)) {
            return false;
        }

        Piece piece = getOtherPiece(x, y, grid);

        if (piece != null) {

            Piece oldObj = piece;

            if (this.checkIfOppositeColor(x, y, grid)) {
                grid.splicePiece(oldObj);
            }
            return false;
        }
        this.setGridStraight(grid, x, y);
        this.untouched = false;
        return true;
    };

    private void setGridStraight(Grid grid, int x, int y) {
        int oldX = this.position.get("x");
        int oldY = this.position.get("y");

        this.setPosition(x, y);
        // grid.setPiece(x, y, oldX, oldY);
    };

    private boolean moveStraightCheck(int x, int y) {

        int oldX = this.position.get("x");
        int oldY = this.position.get("y");

        if ((oldX != x && oldY != y) || (oldX == x && oldY == y)) {
            return false;
        }

        if (oldX != x && Math.abs(oldX - x) != 1) {
            return false;
        }

        if (oldY != y && Math.abs(oldY - y) != 1) {
            return false;
        }
        return true;
    }


    private boolean moveDiagonal(int x, int y, Grid grid) {

        if (moveDiagonalCheck(x, y)) {
            return checkPiece(grid, x, y);
        }
        return false;
    };


    private boolean moveDiagonalCheck(int x, int y) {

        int oldX = this.position.get("x");
        int oldY = this.position.get("y");
        int xAbs = Math.abs(oldX - x);
        int yAbs = Math.abs(oldY - y);

        if (oldY == y || oldX == x) {
            return false;
        }

        if (xAbs != yAbs || xAbs != 1) {
            return false;
        }

        return true;
    }

    private void setGridDiagonal(int x, int y, Grid grid) {

        int oldNumX = this.position.get("x");
        int oldNumY = this.position.get("y");

        if (oldNumX != x && oldNumY != y) {
            setPosition(x, y);
            grid.setPiece(x, y, oldNumX, oldNumY, this );
        }
    }


    private boolean checkPiece(Grid grid, int numX, int numY) {
        Map<Integer, Piece> row = grid.getBoard().get(numX);
        Piece otherPiece = row.get(numY);

        if (this.checkIfOppositeColor(numX, numY, grid)) {
            grid.splicePiece(otherPiece);
            setGridDiagonal(numX, numY, grid);
            return true;
        }
        return false;
    };

    private boolean straightLineCheck(int x, int y, int pos, Grid grid) {

        Piece otherPiece = getOtherPiece(x, y, grid);

        boolean isWhite = this.isWhite();

        if (otherPiece != null) {
            if (otherPiece.isWhite() == isWhite) {
                return false;
            }

            if (pos == 1 && otherPiece.getType() == "King") {
                return true;
            } else if (otherPiece.getType() == "Queen" || otherPiece.getType() == "Rook") {
                return true;
            }
        }
        return false;
    }

    private boolean diagonalLineCheck(int x, int y, int pos, Grid grid) {

        Piece otherPiece = getOtherPiece(x, y, grid);

        boolean isWhite = this.isWhite();

        if (otherPiece != null) {
            if (otherPiece.isWhite() == isWhite) {
                return false;
            }

            if (pos == 1 && (otherPiece.getType() == "King" || otherPiece.getType() == "Pawn")) {
                return true;
            } else if (otherPiece.getType() == "Queen" || otherPiece.getType() == "Bishop") {
                return true;
            }
        }
        return false;
    }

    private boolean allKnightChecks(int x, int y, Grid grid) {
        return (knightCheck(x - 2, y - 1, grid) || knightCheck(x + 2, y - 1, grid) ||
                knightCheck(x - 2, y + 1, grid) || knightCheck(x + 2, y + 1, grid) ||
                knightCheck(x - 1, y - 2, grid) || knightCheck(x + 1, y - 2, grid) ||
                knightCheck(x - 1, y + 2, grid) || knightCheck(x + 1, y + 2, grid));
    }

    private boolean knightCheck(int x, int y, Grid grid) {

        if (!grid.boundaryCheck(x, y)) {
            return false;
        }

        Piece otherPiece = getOtherPiece(x, y, grid);

        boolean isWhite = this.isWhite();

        if (otherPiece != null) {
            if (otherPiece.isWhite() == isWhite) {
                return false;
            }

            if (otherPiece.getType() == "Knight") {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfInCheck(int x, int y, Grid grid) {

        int oldX = this.position.get("x");
        int oldY = this.position.get("y");
        int min = grid.getBoundary().get("min");
        int max = grid.getBoundary().get("max");

        // Check horizontally, x value descending
        for (int i = x - 1; i >= min; i--) {
            if (straightLineCheck(i, y, (x - i), grid)) {
                return true;
            }
        }

        // Check horizontally, x value ascending
        for (int i = x + 1; i <= max; i++) {
            if (straightLineCheck(i, y, (i - x), grid)) {
                return true;
            }
        }

        // Check verticaly, y value descending
        for (int i = y - 1; i >= min; i--) {
            if (straightLineCheck(x, i, (y - i), grid)) {
                return true;
            }
        }

        // Check verticaly, y value ascending
        for (int i = y + 1; i <= max; i++) {
            if (straightLineCheck(x, i, (i - y), grid)) {
                return true;
            }
        }

        // Check diagonally, x and y value descending
        for (int i = x - 1; i >= min; i--) {
            for (int j = y - 1; j >= min; j--) {
                if ((x - i) == (y - j) && diagonalLineCheck(i, j, (x - i), grid)) {
                    return true;
                }
            }
        }

        // Check diagonally, x ascending and y value descending
        for (int i = x + 1; i <= max; i++) {
            for (int j = y - 1; j >= min; j--) {
                if ((i - x) == (y - j) && diagonalLineCheck(i, j, (i - x), grid)) {
                    return true;
                }
            }
        }

        // Check diagonally, x descending and y value ascending
        for (int i = x - 1; i >= min; i--) {
            for (int j = y + 1; j <= max; j++) {
                if ((x - i) == (j - y) && diagonalLineCheck(i, j, (x - i), grid)) {
                    return true;
                }
            }
        }

        // Check diagonally, x ascending and y value ascending
        for (int i = x + 1; i <= min; i++) {
            for (int j = y + 1; j <= max; j++) {
                if ((i - x) == (j - y) && diagonalLineCheck(i, j, (i - x), grid)) {
                    return true;
                }
            }
        }

        // Check for a knight
        if (allKnightChecks(x, y, grid)) {
            return true;
        }

        return false;
    }

    public boolean castle(Rook rook, Grid grid) {

        if (rook == null || this.isWhite() != rook.isWhite() ||
            !this.isUntouched() || !rook.isUntouched())
        {
            return false;
        }

        int oldKingPos = this.position.get("x");
        int oldRookPos = rook.position.get("x");
        int posY = this.position.get("y");

        Map<String, Integer> row = new HashMap<>();
        Map <String, Integer> posX = new HashMap<>();

        if (oldRookPos > oldKingPos) {
            row.put("max", oldRookPos);
            row.put("min", oldKingPos);
            posX.put("king", oldKingPos + 2);
            posX.put("rook", oldRookPos - 2);
        } else {
            row.put("max", oldKingPos);
            row.put("min", oldRookPos);
            posX.put("king", oldKingPos - 2);
            posX.put("rook", oldRookPos + 3);
        }

        // Check chess rules to see if values for min and max are correct
        for (int i = row.get("min") + 1; i < row.get("max"); i++) {
            Piece piece = getOtherPiece(i, posY, grid);
            if (piece != null) {
                return false;
            }
        }

        // Check chess rules to see if values for min and max are correct
        for (int i = row.get("min"); i < row.get("max"); i++) {
            if (checkIfInCheck(i, posY, grid)) {
                return false;
            }
        }

        this.setPosition(posX.get("king"), posY);
        grid.setPiece(posX.get("king"), posY, oldKingPos, posY, this);
        rook.setPosition(posX.get("rook"), posY);
        grid.setPiece(posX.get("rook"), posY, oldRookPos, posY, rook);
        return true;
    }

    private Piece getOtherPiece(int x, int y, Grid grid) {
        Map<Integer, Piece> row = grid.getBoard().get(x);
        Piece otherPiece = row.get(y);
        return otherPiece;
    }
}
