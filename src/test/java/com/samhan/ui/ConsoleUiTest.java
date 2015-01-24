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
        assertEquals(5, consoleUi.getValidMoveInput(new Board()));
    }

    @Test
    public void getValidInputAfterInvalidInput() {
        setUpQueuedConsoleInput(new String[] {"d", "5"});
        assertEquals(5, consoleUi.getValidMoveInput(new Board()));
    }

    @Test
    public void getValidInputUntilEmptyPosition() {
        setUpQueuedConsoleInput(new String[] {"d", "5", "6", "7", "8", "9"});
        assertEquals(8, consoleUi.getValidMoveInput(new Board("xxxxxxxx-")));
    }

    @Test
    public void getValidInputAfterOutOfRangeInputs() {
        setUpQueuedConsoleInput(new String[] {"d", "55", "-6", "7", "8", "9"});
        assertEquals(8, consoleUi.getValidMoveInput(new Board("xxxxxxxx-")));
    }

    @Test
    public void enterMovePrompt() {
        setUpQueuedConsoleInput(new String[] {"8"});
        assertEquals(8, consoleUi.getValidMoveInput(new Board("xxxxxxxx-")));
        assertEquals("Enter move:\n", output.toString());
    }

    @Test
    public void enterMovePromptAndInvalidInputPrompt() {
        setUpQueuedConsoleInput(new String[] {"d", "8"});
        assertEquals(8, consoleUi.getValidMoveInput(new Board("xxxxxxxx-")));
        assertEquals("Enter move:\nInvalid number entry. (0-8)\nEnter move:\n", output.toString());
    }

    @Test
    public void enterMovePromptAndMoveAlreadyTakenInputPrompt() {
        setUpQueuedConsoleInput(new String[] {"3", "8"});
        assertEquals(8, consoleUi.getValidMoveInput(new Board("xxxxxxxx-")));
        assertEquals("Enter move:\nMove already taken.\nEnter move:\n", output.toString());
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
    public void enterInvalidInputForNewGamePromptBeforeValidInput(){
        setUpQueuedConsoleInput(new String[] {"sadf", "Y"});
        assertEquals(true, consoleUi.doesUserWantToStartNewGame());
    }

    @Test
    public void checkNewGamePrompt(){
        setUpQueuedConsoleInput(new String [] {"y"});
        assertEquals(true, consoleUi.doesUserWantToStartNewGame());
        assertEquals("Start a new game?\n", output.toString());
    }

    @Test
    public void checkInvalidYesNoPrompt(){
        setUpQueuedConsoleInput(new String [] {"asdf", "y"});
        assertEquals(true, consoleUi.doesUserWantToStartNewGame());
        assertEquals("Start a new game?\nInvalid input ( yes or no).\nStart a new game?\n", output.toString());
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
}
