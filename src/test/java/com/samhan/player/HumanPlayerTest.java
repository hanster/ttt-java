package com.samhan.player;

import com.samhan.game.Board;
import com.samhan.game.Marker;
import com.samhan.ui.StubUi;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class HumanPlayerTest {
    private Player player;

    @Before
    @Test
    public void setUp() {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(0);
        player = new HumanPlayer(new StubUi(queue), Marker.O);
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
