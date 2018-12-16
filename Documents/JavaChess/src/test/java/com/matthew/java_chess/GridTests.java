package com.matthew.java_chess;

import com.matthew.java_chess.entities.Grid;
import com.matthew.java_chess.entities.Pawn;
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

        Pawn whitePawnToTest = (Pawn) allWhitePieces.get("pawns").get(0);
        assertThat(whitePawnToTest.isWhite(), is(true));
        assertThat(whitePawnToTest.getType(), is("Pawn"));

        assertThat(allBlackPieces.get("kings").size(), comparesEqualTo(1));
        assertThat(allBlackPieces.get("queens").size(), comparesEqualTo(1));
        assertThat(allBlackPieces.get("rooks").size(), comparesEqualTo(2));
        assertThat(allBlackPieces.get("bishops").size(), comparesEqualTo(2));
        assertThat(allBlackPieces.get("knights").size(), comparesEqualTo(2));
        assertThat(allBlackPieces.get("pawns").size(), comparesEqualTo(8));

        Pawn blackPawnToTest = (Pawn) allBlackPieces.get("pawns").get(0);
        assertThat(blackPawnToTest.isWhite(), is(false));
        assertThat(blackPawnToTest.getType(), is("Pawn"));
    }

    @Test
    public void initializeGridTest() {
        Grid grid = new Grid("Matthew");
        grid.initializeGame();

        Piece x1y1 = grid.getBoard().get(1).get(1);
        assertThat(x1y1.getClass().getSimpleName(), is("Rook"));
        assertThat(x1y1.isWhite(), is(false));
        Piece x2y1 = grid.getBoard().get(2).get(1);
        assertThat(x2y1.getClass().getSimpleName(), is("Knight"));
        Piece x3y1 = grid.getBoard().get(3).get(1);
        assertThat(x3y1.getClass().getSimpleName(), is("Bishop"));
        Piece x4y1 = grid.getBoard().get(4).get(1);
        assertThat(x4y1.getClass().getSimpleName(), is("Queen"));
        Piece x5y1 = grid.getBoard().get(5).get(1);
        assertThat(x5y1.getClass().getSimpleName(), is("King"));
        Piece x6y1 = grid.getBoard().get(6).get(1);
        assertThat(x6y1.getClass().getSimpleName(), is("Bishop"));
        Piece x7y1 = grid.getBoard().get(7).get(1);
        assertThat(x7y1.getClass().getSimpleName(), is("Knight"));
        Piece x8y1 = grid.getBoard().get(8).get(1);
        assertThat(x8y1.getClass().getSimpleName(), is("Rook"));

        for (int i = 1; i <= 8; i++) {
            Piece y2 = grid.getBoard().get(i).get(2);
            assertThat(y2.getClass().getSimpleName(), is("Pawn"));
        }

        Piece x1y8 = grid.getBoard().get(1).get(8);
        assertThat(x1y8.getClass().getSimpleName(), is("Rook"));
        assertThat(x1y8.isWhite(), is(true));
        Piece x2y8 = grid.getBoard().get(2).get(8);
        assertThat(x2y8.getClass().getSimpleName(), is("Knight"));
        Piece x3y8 = grid.getBoard().get(3).get(8);
        assertThat(x3y8.getClass().getSimpleName(), is("Bishop"));
        Piece x4y8 = grid.getBoard().get(4).get(8);
        assertThat(x4y8.getClass().getSimpleName(), is("Queen"));
        Piece x5y8 = grid.getBoard().get(5).get(8);
        assertThat(x5y8.getClass().getSimpleName(), is("King"));
        Piece x6y8 = grid.getBoard().get(6).get(8);
        assertThat(x6y8.getClass().getSimpleName(), is("Bishop"));
        Piece x7y8 = grid.getBoard().get(7).get(8);
        assertThat(x7y8.getClass().getSimpleName(), is("Knight"));
        Piece x8y8 = grid.getBoard().get(8).get(8);
        assertThat(x8y8.getClass().getSimpleName(), is("Rook"));

        for (int i = 1; i <= 8; i++) {
            Piece y7 = grid.getBoard().get(i).get(7);
            assertThat(y7.getClass().getSimpleName(), is("Pawn"));
        }

    }
}
