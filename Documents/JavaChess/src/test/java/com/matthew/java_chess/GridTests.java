package com.matthew.java_chess;

import com.matthew.java_chess.entities.Grid;
import com.matthew.java_chess.entities.Piece;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
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

        Map<String, Map<String, List<? extends Piece>>> allPieces = grid.getAllObjects();
        Map<String, List<? extends Piece>> allWhitePieces = allPieces.get("white");
        Map<String, List<? extends Piece>> allBlackPieces = allPieces.get("black");

        assertThat(allWhitePieces.get("kings").size(), comparesEqualTo(1));
        assertThat(allWhitePieces.get("queens").size(), comparesEqualTo(1));
        assertThat(allWhitePieces.get("rooks").size(), comparesEqualTo(2));
        assertThat(allWhitePieces.get("bishops").size(), comparesEqualTo(2));
        assertThat(allWhitePieces.get("knights").size(), comparesEqualTo(2));
        assertThat(allWhitePieces.get("pawns").size(), comparesEqualTo(8));

        assertThat(allBlackPieces.get("kings").size(), comparesEqualTo(1));
        assertThat(allBlackPieces.get("queens").size(), comparesEqualTo(1));
        assertThat(allBlackPieces.get("rooks").size(), comparesEqualTo(2));
        assertThat(allBlackPieces.get("bishops").size(), comparesEqualTo(2));
        assertThat(allBlackPieces.get("knights").size(), comparesEqualTo(2));
        assertThat(allBlackPieces.get("pawns").size(), comparesEqualTo(8));
    }

    @Test
    public void initializeGridTest() {
        Grid grid = new Grid("Matthew");
//        grid.initial
    }
}
