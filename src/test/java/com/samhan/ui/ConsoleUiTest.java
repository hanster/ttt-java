package com.samhan.ui;

import com.samhan.game.Board;
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
    public void getSingleNumberValidMoveInput() {
        setUpQueuedConsoleInput(new String[] {"5"});
        assertEquals(5, consoleUi.getValidMoveInput(new Board()));
    }

    @Test
    public void exceptionMessageForInvalidMoveInput() {
        String exceptionMessage = "";
        setUpQueuedConsoleInput(new String[]{"d"});
        try {
            consoleUi.getValidMoveInput(new Board());
        } catch (InvalidInputEntryException e) {
            exceptionMessage = e.getMessage();
        }
        assertEquals(ConsoleUi.INVALID_NUMBER_ENTRY_0_8, exceptionMessage);
    }

    @Test
    public void exceptionMessageForInvalidOutOfRangeMoveInput() {
        String exceptionMessage = "";
        setUpQueuedConsoleInput(new String[]{"200"});
        try {
            consoleUi.getValidMoveInput(new Board());
        } catch (InvalidInputEntryException e) {
            exceptionMessage = e.getMessage();
        }
        assertEquals(ConsoleUi.INVALID_NUMBER_ENTRY_0_8, exceptionMessage);
    }

    @Test
    public void exceptionMessageForMoveAlreadyTaken(){
        String exceptionMessage = "";
        setUpQueuedConsoleInput(new String[]{"0"});
        try {
            consoleUi.getValidMoveInput(new Board("x--------"));
        } catch (InvalidInputEntryException e) {
            exceptionMessage = e.getMessage();
        }
        assertEquals(ConsoleUi.MOVE_ALREADY_TAKEN, exceptionMessage);
    }

    @Test
    public void enterYesInputForNewGamePrompt(){
        setUpQueuedConsoleInput(new String[] {"y"});
        assertEquals(true, consoleUi.doesUserWantToStartNewGame());

        setUpQueuedConsoleInput(new String[] {"Y"});
        assertEquals(true, consoleUi.doesUserWantToStartNewGame());

        setUpQueuedConsoleInput(new String[] {"yes"});
        assertEquals(true, consoleUi.doesUserWantToStartNewGame());

        setUpQueuedConsoleInput(new String[] {"YES"});
        assertEquals(true, consoleUi.doesUserWantToStartNewGame());
    }

    @Test
    public void enterNoInputForNewGamePrompt(){
        setUpQueuedConsoleInput(new String[] {"n"});
        assertEquals(false, consoleUi.doesUserWantToStartNewGame());

        setUpQueuedConsoleInput(new String[] {"N"});
        assertEquals(false, consoleUi.doesUserWantToStartNewGame());

        setUpQueuedConsoleInput(new String[] {"no"});
        assertEquals(false, consoleUi.doesUserWantToStartNewGame());

        setUpQueuedConsoleInput(new String[] {"NO"});
        assertEquals(false, consoleUi.doesUserWantToStartNewGame());
    }

    @Test
    public void invalidNewGameInputExceptionMessage() {
        String exceptionMessage = "";
        setUpQueuedConsoleInput(new String[]{"1231"});

        try {
            consoleUi.doesUserWantToStartNewGame();
        } catch (InvalidInputEntryException e) {
            exceptionMessage = e.getMessage();
        }
        assertEquals(ConsoleUi.INVALID_YES_NO, exceptionMessage);
    }

    @Test
    public void checkNewGamePrompt(){
        setUpQueuedConsoleInput(new String [] {"y"});
        assertEquals(true, consoleUi.doesUserWantToStartNewGame());
        assertEquals("Start a new game?\n", output.toString());
    }


    @Test
    public void drawBoardOutput(){
        setUpQueuedConsoleInput(new String[]{});
        consoleUi.drawBoard(new Board());
        assertEquals("  0  |  1  |  2  \n" +
                "-----+-----+-----\n" +
                "  3  |  4  |  5  \n" +
                "-----+-----+-----\n" +
                "  6  |  7  |  8  \n", output.toString());
    }

    @Test
    public void displayMessageOutput() {
        setUpQueuedConsoleInput(new String[]{});
        consoleUi.displayMessage("hello world");
        assertEquals("\nhello world\n", output.toString());
    }
}
