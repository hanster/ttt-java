package com.samhan;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConsoleGameTest {


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
        System.setIn(System.in);
    }

    @Test
    public void interspaceChar(){
        ConsoleGame consoleGame = new ConsoleGame();
        assertEquals(" | | ", consoleGame.interSpaceWithChar("   ", '|'));
    }


    private ByteArrayInputStream getNumberInput(int number) {
        return new ByteArrayInputStream(Integer.toString(number).getBytes());
    }
}
