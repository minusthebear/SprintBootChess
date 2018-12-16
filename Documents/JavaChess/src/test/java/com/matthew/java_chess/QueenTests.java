package com.matthew.java_chess;

import com.matthew.java_chess.entities.Grid;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueenTests {

    private Grid grid;

    @Before
    public void init() {
        grid = new Grid("Matthew");
        grid.initializeGame();
    }

    @Test
    public void moveQueen() {
        
    }
}
