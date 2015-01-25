package com.samhan.game;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class GameLoopTest {
    @Test
    public void itDoesNothingWhenTheGameIsStopped(){
        TestGame game = new TestGame();
        game.queueIsRunningAnswers(new boolean[]{false});


        GameLoop gameLoop = new GameLoop(game);
        gameLoop.run();
        assertFalse(game.isUpdated());
    }
    
    @Test
    public void itRunsUpdateOnceBeforeTheGameIsStopped(){
        TestGame game = new TestGame();
        game.queueIsRunningAnswers(new boolean[]{true, false});

        GameLoop gameLoop = new GameLoop(game);
        gameLoop.run();
        assertTrue(game.isUpdated());
    }



}
