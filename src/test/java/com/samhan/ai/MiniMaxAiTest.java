package com.samhan.ai;

import com.samhan.game.Board;
import com.samhan.game.Marker;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Minimax algorithm test. Make sure the best move is selected.
 */
public class MiniMaxAiTest {
    MiniMaxAi miniMaxAi;

    @Before
    public void setUp() {
        miniMaxAi = new MiniMaxAi();
    }

    @Test
    public void itReturnsTheBestMoveWithOnlyOnePossibleMove(){
        assertEquals(7, miniMaxAi.nextMove(new Board("xoxxoxo-o"), Marker.O));
    }

    @Test
    public void itReturnsWinningMove(){
        assertEquals( 0, miniMaxAi.nextMove(new Board("-xx------"), Marker.X));
        assertEquals( 1, miniMaxAi.nextMove(new Board("o-o------"), Marker.O));
        assertEquals( 8, miniMaxAi.nextMove(new Board("" +
                "xoo" +
                "-x-" +
                "---"), Marker.X));

    }

    @Test
    public void itReturnsMoveWhichStopsTheOtherPlayerFromWinning(){
        assertEquals( 1, miniMaxAi.nextMove(new Board("o-o------"), Marker.X));
        assertEquals( 0, miniMaxAi.nextMove(new Board("-oo------"), Marker.X));
        assertEquals( 8, miniMaxAi.nextMove(new Board("" +
                "xoo" +
                "-x-" +
                "---"), Marker.O));
    }

    @Test
    public void itReturnsNegOneWhenFullBoard(){
        assertEquals(-1, miniMaxAi.nextMove(new Board("oxooxoxox"), Marker.O));
    }
}
