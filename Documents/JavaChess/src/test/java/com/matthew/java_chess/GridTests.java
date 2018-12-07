package com.matthew.java_chess;

import com.matthew.java_chess.entities.Grid;
import com.matthew.java_chess.entities.Piece;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GridTests {

    @Test
    public void initializeGameTest() {
        Grid grid = new Grid("Matthew");
        assertThat(grid.getName(), is("Matthew"));
        grid.initializeGame();

        Map<String, Map<String, Piece[]>> allPieces = grid.getAllObjects();
        Map<String, Piece[]> allWhitePieces = allPieces.get("white");
        Map<String, Piece[]> allBlackPieces = allPieces.get("black");

        assertThat(allWhitePieces.get("kings").length, comparesEqualTo(1));
        assertThat(allWhitePieces.get("queens").length, comparesEqualTo(1));
        assertThat(allWhitePieces.get("rooks").length, comparesEqualTo(2));
        assertThat(allWhitePieces.get("bishops").length, comparesEqualTo(2));
        assertThat(allWhitePieces.get("knights").length, comparesEqualTo(2));

        assertThat(allBlackPieces.get("kings").length, comparesEqualTo(1));
        assertThat(allBlackPieces.get("queens").length, comparesEqualTo(1));
        assertThat(allBlackPieces.get("rooks").length, comparesEqualTo(2));
        assertThat(allBlackPieces.get("bishops").length, comparesEqualTo(2));
        assertThat(allBlackPieces.get("knights").length, comparesEqualTo(2));
    }

    @Test
    public void initializeGridTest() {
        Grid grid = new Grid("Matthew");
//        grid.initial
    }
}
