package com.samhan;

import com.samhan.ai.MiniMaxAi;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class ConsoleGameTest {


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();


    @Test
    public void interspaceChar(){
        ConsoleGame consoleGame = new ConsoleGame(new MiniMaxAi());
        assertEquals(" | | ", consoleGame.interSpaceWithChar("   ", '|'));
    }


    private ByteArrayInputStream getNumberInput(int number) {
        return new ByteArrayInputStream(Integer.toString(number).getBytes());
    }
}
