package com.samhan.ui;

import com.samhan.Board;

import java.io.*;

/**
 * User interface Console implementation
 */
public class ConsoleUi implements Ui {
    public static final String INVALID_NUMBER_ENTRY_0_8 = "Invalid number entry. (0-8)\n";
    public static final String MOVE_ALREADY_TAKEN = "Move already taken.\n";
    public static final String INVALID_YES_NO = "Invalid input ( yes or no).\n";


    public static final String ENTER_MOVE_PROMPT = "Enter move:\n";
    public static final String NEW_GAME_PROMPT = "Start a new game?\n";

    private BufferedReader bufferedReader;
    private PrintStream output;


    public ConsoleUi(InputStream inputStream, PrintStream output) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        this.output = output;
    }

    @Override
    public int getValidMoveInput(Board board) {
        String errorMessage = "";
        while (true) {
            try {
                output.print(errorMessage + ENTER_MOVE_PROMPT);
                int input = tryToGetValidIntegerInput();
                if (!board.isPositionFree(input)) {
                    throw new InvalidMoveException();
                }
                return input;
            } catch (IOException e) {
                // loop again to try and get another input
            } catch (NumberFormatException e) {
                errorMessage = INVALID_NUMBER_ENTRY_0_8;
            } catch (InvalidMoveException e) {
                errorMessage = MOVE_ALREADY_TAKEN;
            } catch (ArrayIndexOutOfBoundsException e) {
                errorMessage = INVALID_NUMBER_ENTRY_0_8;
            }
        }
    }

    @Override
    public boolean doesUserWantToStartNewGame() {
        String errorMessage = "";
        while (true) {
            try {
                output.print(errorMessage + NEW_GAME_PROMPT);
                String input = tryToGetValidYesOrNoInput();
                return (isStringYes(input));
            } catch (IOException e) {
                // loop again to try and get another input
            } catch (InvalidNewGameInputException e) {
                errorMessage = INVALID_YES_NO;
            }
        }
    }

    @Override
    public void drawBoard(Board board) {
        output.print(board.getLayout());
    }

    final private String ANSI_CLS = "\u001b[2J";
    final private String ANSI_HOME = "\u001b[H";

    @Override
    public void clearDisplay() {
        output.print(ANSI_CLS + ANSI_HOME);
        output.flush();
    }

    @Override
    public void displayMessage(String message) {
        output.print("\n" + message + "\n");
    }

    private boolean isStringYesOrNo(String str) {
        return (isStringYes(str) || isStringNo(str));
    }

    private boolean isStringYes(String str) {
        return (str.toLowerCase().equals("y") || str.toLowerCase().equals("yes"));
    }

    private boolean isStringNo(String str) {
        return (str.toLowerCase().equals("n") || str.toLowerCase().equals("no"));
    }

    private int tryToGetValidIntegerInput() throws IOException {
        return Integer.parseInt(bufferedReader.readLine());
    }

    private String tryToGetValidYesOrNoInput() throws IOException {
        String input = bufferedReader.readLine();
        if (!isStringYesOrNo(input)) {
            throw new InvalidNewGameInputException();
        }
        return input;
    }
}
