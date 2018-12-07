package com.matthew.java_chess.entities;

import org.springframework.stereotype.Component;

@Component
public class InitGame {

    public void initGame() {
        Grid game = initializeGame("Matthew");
        System.out.println(game.allObjects);
        System.out.println(game.board);
    }

    private static Grid initializeGame(String name) {
        Grid grid = new Grid(name);
        grid.initializeGame();
        return grid;
    }
}
