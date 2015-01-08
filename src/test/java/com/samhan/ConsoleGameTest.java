package com.samhan;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConsoleGameTest {

    @Test
    public void testInterspaceChar(){
        ConsoleGame consoleGame = new ConsoleGame();
        assertEquals(" | | ", consoleGame.interSpaceWithChar("   ", '|'));
    }
}
