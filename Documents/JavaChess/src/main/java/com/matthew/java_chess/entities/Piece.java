package com.matthew.java_chess.entities;

import java.util.HashMap;
import java.util.Map;

public abstract class Piece {

    private int id;
    private String type;
    private boolean white;
    private boolean taken;
    private boolean untouched;
    public Map<String, Integer> position = new HashMap<>();

    public Piece() {}

    public Piece(int x, int y, String type, boolean color) {
        this.id = x;
        this.type = type;
        this.white = color;
        this.taken = false;
        this.untouched = true;
        this.position.put("x", x);
        this.position.put("y", y);
    }

    public Piece(int id, int x, int y, String type, boolean color) {
        this.id = id;
        this.type = type;
        this.white = color;
        this.taken = false;
        this.untouched = true;
        this.position.put("x", x);
        this.position.put("y", y);
    }

    public boolean checkIfOppositeColor(int x, int y, Grid grid) {
        Map <Integer, Piece> row = grid.board.get(x);
        Piece piece = row.get(y);

        if (piece != null) {
            return false;
        }

        if (piece.white == this.white) {
            return false;
        }
        return true;
    }

    public void setPosition(int x, int y) {
        this.position.put("x", x);
        this.position.put("y", y);
    }

    public abstract boolean move(int x, int y, Grid grid);

    public abstract boolean setGrid(Grid grid, int x, int oldX, int y, int oldY);

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public boolean isWhite() {
        return white;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public boolean isUntouched() {
        return untouched;
    }

    public void setUntouched(boolean untouched) {
        this.untouched = untouched;
    }
}
