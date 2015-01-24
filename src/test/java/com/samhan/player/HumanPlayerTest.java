package com.samhan.player;

import com.samhan.Board;
import com.samhan.Marker;
import com.samhan.StubUi;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class HumanPlayerTest {
    private Player player;

    @Before
    @Test
    public void setUp(){
        player = new HumanPlayer(new StubUi(), Marker.O);
    }


    @Test
    public void correctReturnPlayerType(){
        assertEquals(PlayerType.HUMAN, player.getType());
    }

    @Test
    public void initializePlayerWithMarker(){
        assertEquals(Marker.O, player.getMarker());
    }

    @Test
    public void uiNextMoveCalledCorrectly(){
        assertEquals(0, player.getMove(new Board()));
    }
}
