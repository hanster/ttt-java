package com.samhan;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ConsoleGameTest {


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();


//    @Test
//    public void interspaceChar(){
//        ConsoleGame consoleGame = new ConsoleGame(new MiniMaxAi());
//        assertEquals(" | | ", consoleGame.interSpaceWithChar("   ", '|'));
//    }


    private ByteArrayInputStream getNumberInput(int number) {
        return new ByteArrayInputStream(Integer.toString(number).getBytes());
    }
}
