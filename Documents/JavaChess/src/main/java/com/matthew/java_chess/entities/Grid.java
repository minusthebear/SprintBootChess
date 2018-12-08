package com.matthew.java_chess.entities;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;


public class Grid {

    String name;
    private static final int min = 1;
    private static final int max = 8;
    private Map<Integer, Map<Integer, Piece>> board = new HashMap<>();
    private Map<String, Map<String, List<? extends Piece>>> allObjects = new HashMap<>();
    private Map<String, Integer> boundary = new HashMap<>();

    public Grid(String name) {
        this.name = name;
        this.boundary.put("min", min);
        this.boundary.put("max", max);
        this.boundary = Collections.unmodifiableMap(this.boundary);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Map<Integer, Piece>> getBoard() {
        return board;
    }

    public void setBoard(Map<Integer, Map<Integer, Piece>> board) {
        this.board = board;
    }

    public Map<String, Map<String, List<? extends Piece>>> getAllObjects() {
        return allObjects;
    }

    public void setAllObjects(Map<String, Map<String, List<? extends Piece>>> allObjects) {
        this.allObjects = allObjects;
    }

    public void setStartPosOnGrid(int x, int y, Piece piece) {
        Map <Integer, Piece> spot = this.board.get(x);
        spot.put(y, piece);
        this.board.put(x, spot);
    }

    public Piece getPiece(boolean color, Piece piece) {

        return piece;
    }

    public void setPiece(int x, int y, int oldX, int oldY, Piece piece) {
        Map <Integer, Piece> spotOne = this.board.get(oldX);
        Map <Integer, Piece> spotTwo = this.board.get(x);
        spotOne.put(oldY, null);
        spotTwo.put(y, piece);
        this.board.put(oldX, spotOne);
        this.board.put(x, spotTwo);
    }

    public boolean boundaryCheck(int x, int y) {
        int min = this.boundary.get("min");
        int max = this.boundary.get("max");

        if (min > x || max < x || min > y || max < y) {
            return false;
        }
        return true;
    }

    private void initializeGrid() {

        for (int numX = min; numX <= max; numX++) {
            Map map = new HashMap<Integer, Piece>();

            for (int numY = min; numY <= max; numY++) {
                map.put(numY, null);
            }

            this.board.put(numX, map);
        }
    }

    public void initializeGame() {
        initializeGrid();

        boolean homeTeam;
        int initFrontYPosition;
        int initRearYPosition;
        String color;

        for (int i = 0; i < 2; i++) {
            if (i % 2 == 0) {
                initFrontYPosition = 7;
                initRearYPosition = 8;
                color = "white";
                homeTeam = true;
            } else {
                initFrontYPosition = 2;
                initRearYPosition = 1;
                color = "black";
                homeTeam = false;
            }

            List<Rook> rookArray = initializeRooks(homeTeam, initRearYPosition);
            List<Queen> queenArray = initializeQueens(homeTeam, initRearYPosition);
            List<King> kingArray = initializeKings(homeTeam, initRearYPosition);
            List<Bishop> bishopArray = initializeBishops(homeTeam, initRearYPosition);
            List<Knight> knightArray = initializeKnights(homeTeam, initRearYPosition);
            List<Pawn> pawnArray = initializePawns(homeTeam, initFrontYPosition);
            HashMap<String, List<? extends Piece>> pieces = new HashMap<>();
            pieces.put("rooks", rookArray);
            pieces.put("queens", queenArray);
            pieces.put("kings", kingArray);
            pieces.put("bishops", bishopArray);
            pieces.put("knights", knightArray);
            pieces.put("pawns", pawnArray);
            this.allObjects.put(color, pieces);
        }
    }

    private List<Rook> initializeRooks(boolean team, int Y) {
        List<Rook> rooks = new ArrayList<>();
        Rook rookOne = new Rook(1, Y, "Rook", team);
        Rook rookTwo = new Rook(8, Y, "Rook", team);

        rooks.add(rookOne);
        rooks.add(rookTwo);

        this.setStartPosOnGrid(1, Y, rookOne);
        this.setStartPosOnGrid(8, Y, rookTwo);

        return rooks;
    }

    private List<Queen> initializeQueens(boolean team, int Y) {
        List<Queen> queens = new ArrayList<>();
        Queen queenOne = new Queen(4, Y, "Queen", team);
        queens.add(queenOne);
        this.setStartPosOnGrid(4, Y, queenOne);

        return queens;
    }

    private List<King> initializeKings(boolean team, int Y) {

        List<King> kings = new ArrayList<>();
        King kingOne = new King(5, Y, "King", team);
        kings.add(kingOne);
        this.setStartPosOnGrid(5, Y, kingOne);

        return kings;
    }

    private List<Bishop> initializeBishops(boolean team, int Y) {

        List<Bishop> bishops = new ArrayList<>();

        Bishop bishopOne = new Bishop(3, Y, "Bishop", team);
        Bishop bishopTwo = new Bishop(6, Y, "Bishop", team);

        bishops.add(bishopOne);
        bishops.add(bishopTwo);

        this.setStartPosOnGrid(3, Y, bishopOne);
        this.setStartPosOnGrid(6, Y, bishopTwo);

        return bishops;
    }

    private List<Knight> initializeKnights(boolean team, int Y) {

        List<Knight> knights = new ArrayList<>();

        Knight knightOne = new Knight(2, Y, "Knight", team);
        Knight knightTwo = new Knight(7, Y, "Knight", team);

        knights.add(knightOne);
        knights.add(knightTwo);

        this.setStartPosOnGrid(2, Y, knightOne);
        this.setStartPosOnGrid(7, Y, knightTwo);

        return knights;
    }


    private List<Pawn> initializePawns(boolean team, int Y) {
        List<Pawn> pawns = new ArrayList<Pawn>();

        for (int i = min; i <= max; i++) {
            Pawn pawn = new Pawn(i, Y, "Pawn", team);
            pawns.add(pawn);
            this.setStartPosOnGrid(i, Y, pawn);
        }
        return pawns;
    }

    public void splicePiece(Piece piece) {

        if (piece != null) {
            String color;
            Integer id = piece.getId();

            if (piece.isWhite()) {
                color = "white";
            } else {
                color = "black";
            }

            Map<String, List<? extends Piece>> allObjects = this.allObjects.get(color);
            List<? extends Piece> pieceList = allObjects.get(piece.getType());

            for (Piece p: pieceList) {
                if (id.equals(p.getId())) {
                    pieceList.remove(p);
                }
            }
        }
    }
}
