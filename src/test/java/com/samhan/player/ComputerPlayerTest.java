package com.samhan.player;

import com.samhan.ai.Ai;
import com.samhan.game.Board;
import com.samhan.game.Marker;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class ComputerPlayerTest {
    private Player player;

    @Before
    public void setUp() {
        player = new ComputerPlayer(new StubAi(), Marker.X);
    }
    
    @Test
    public void correctReturnPlayerType(){
        assertEquals(PlayerType.COMPUTER, player.getType());
    }

    @Test
    public void initializePlayerWithMarker(){
        assertEquals(Marker.X, player.getMarker());
    }

    @Test
    public void aiNextMoveCorrectlyCalled(){
        assertEquals(0, player.getMove(new Board()));
    }


    public class StubAi implements Ai {
        @Override
        public int nextMove(Board board, Marker marker) {
            return 0;
        }
    }
}
