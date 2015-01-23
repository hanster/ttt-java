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

    @Test
    public void xWinCase(){
        ConsoleGame consoleGame = new ConsoleGame();

        System.setIn(getNumberInput(1));
        consoleGame.choseMove();
        System.setIn(getNumberInput(4));
        consoleGame.choseMove();
        System.setIn(getNumberInput(3));
        consoleGame.choseMove();
        System.setIn(getNumberInput(5));
        consoleGame.choseMove();
        System.setIn(getNumberInput(2));
        consoleGame.choseMove();

        assertTrue(consoleGame.hasGameEnded());
        outContent.reset();

        consoleGame.displayResult();

        assertEquals("x wins\n", outContent.toString());

    }

    @Test
    public void oWinCase(){
        ConsoleGame consoleGame = new ConsoleGame();

        System.setIn(getNumberInput(1));
        consoleGame.choseMove();
        System.setIn(getNumberInput(4));
        consoleGame.choseMove();
        System.setIn(getNumberInput(3));
        consoleGame.choseMove();
        System.setIn(getNumberInput(5));
        consoleGame.choseMove();
        System.setIn(getNumberInput(9));
        consoleGame.choseMove();
        System.setIn(getNumberInput(6));
        consoleGame.choseMove();

        assertTrue(consoleGame.hasGameEnded());
        outContent.reset();

        consoleGame.displayResult();

        assertEquals("o wins\n", outContent.toString());

    }

    @Test
    public void drawCase(){
        ConsoleGame consoleGame = new ConsoleGame();

        System.setIn(getNumberInput(1));
        consoleGame.choseMove();
        System.setIn(getNumberInput(2));
        consoleGame.choseMove();
        System.setIn(getNumberInput(3));
        consoleGame.choseMove();
        System.setIn(getNumberInput(5));
        consoleGame.choseMove();
        System.setIn(getNumberInput(8));
        consoleGame.choseMove();
        System.setIn(getNumberInput(4));
        consoleGame.choseMove();
        System.setIn(getNumberInput(6));
        consoleGame.choseMove();
        System.setIn(getNumberInput(9));
        consoleGame.choseMove();
        System.setIn(getNumberInput(7));
        consoleGame.choseMove();

        assertTrue(consoleGame.hasGameEnded());
        outContent.reset();

        consoleGame.displayResult();

        assertEquals("draw\n", outContent.toString());

    }

    private ByteArrayInputStream getNumberInput(int number) {
        return new ByteArrayInputStream(Integer.toString(number).getBytes());
    }
}
