package com.samhan.ai;

import com.samhan.game.Board;
import com.samhan.game.Marker;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * test RandomAi Implementation.
 */
public class RandomAiTest {

    @Test
    public void itSelectsTheOnlyAvailbleMove(){
        Ai ai = new RandomAi();
        assertEquals(0, ai.nextMove(new Board("-xxoooxxx"), Marker.X));
        assertEquals(3, ai.nextMove(new Board("xxx-ooxxx"), Marker.X));
        assertEquals(6, ai.nextMove(new Board("xxxooo-xx"), Marker.X));
        assertEquals(8, ai.nextMove(new Board("xxxoooxx-"), Marker.X));
    }
}
