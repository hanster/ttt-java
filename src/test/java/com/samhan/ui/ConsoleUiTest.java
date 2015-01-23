package com.samhan.ui;

import com.samhan.Board;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class ConsoleUiTest {
    ByteArrayOutputStream output;
    ConsoleUi consoleUi;

    private void setUpQueuedConsoleInput (String[] listOfInputs) {
        String totalInputs = "";
        for (String input : listOfInputs) {
            totalInputs = totalInputs + input + "\n";
        }
        output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        consoleUi = new ConsoleUi(new ByteArrayInputStream(totalInputs.getBytes()), printStream);
    }


    @Test
    public void getSingleNumberValidInput() {
        setUpQueuedConsoleInput(new String[] {"5"});
        assertEquals(5, consoleUi.getValidInput(new Board()));
    }

    @Test
    public void getValidInputAfterInvalidInput() {
        setUpQueuedConsoleInput(new String[] {"d", "5"});
        assertEquals(5, consoleUi.getValidInput(new Board()));
    }

    @Test
    public void getValidInputUntilEmptyPosition() {
        setUpQueuedConsoleInput(new String[] {"d", "5", "6", "7", "8", "9"});
        assertEquals(8, consoleUi.getValidInput(new Board("xxxxxxxx-")));
    }

    @Test
    public void getValidInputAfterOutOfRangeInputs() {
        setUpQueuedConsoleInput(new String[] {"d", "55", "-6", "7", "8", "9"});
        assertEquals(8, consoleUi.getValidInput(new Board("xxxxxxxx-")));
    }

    @Test
    public void enterMovePrompt() {
        setUpQueuedConsoleInput(new String[] {"8"});
        assertEquals(8, consoleUi.getValidInput(new Board("xxxxxxxx-")));
        assertEquals("Enter move:\n", output.toString());
    }

    @Test
    public void enterMovePromptAndInvalidInputPrompt() {
        setUpQueuedConsoleInput(new String[] {"d", "8"});
        assertEquals(8, consoleUi.getValidInput(new Board("xxxxxxxx-")));
        assertEquals("Enter move:\nInvalid number entry. (0-8)\nEnter move:\n", output.toString());
    }

    @Test
    public void enterMovePromptAndMoveAlreadyTakenInputPrompt() {
        setUpQueuedConsoleInput(new String[] {"3", "8"});
        assertEquals(8, consoleUi.getValidInput(new Board("xxxxxxxx-")));
        assertEquals("Enter move:\nMove already taken.\nEnter move:\n", output.toString());
    }

}
