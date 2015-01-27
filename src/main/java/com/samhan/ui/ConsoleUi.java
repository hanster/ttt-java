package com.samhan.ui;

import com.samhan.game.Board;

import java.io.*;

/**
 * User interface Console implementation
 */
public class ConsoleUi implements Ui {
    public static final String INVALID_NUMBER_ENTRY_0_8 = "Invalid number entry. (0-8)\n";
    public static final String MOVE_ALREADY_TAKEN = "Move already taken.\n";
    public static final String INVALID_YES_NO = "Invalid input ( yes or no).\n";
    private static final String ANSI_CLS = "\u001b[2J";
    private static final String ANSI_HOME = "\u001b[H";

    private BufferedReader bufferedReader;
    private PrintStream output;


    public ConsoleUi(InputStream inputStream, PrintStream output) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        this.output = output;
    }

    @Override
    public int getValidMoveInput(Board board) throws InvalidInputEntryException {
        try {
            int input = tryToGetValidIntegerInput();
            if (!board.isPositionFree(input)) {
                throw new InvalidInputEntryException(MOVE_ALREADY_TAKEN);
            }
            return input;
        } catch (IOException e) {
            throw new InvalidInputEntryException(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputEntryException(INVALID_NUMBER_ENTRY_0_8);
        } catch (NumberFormatException e) {
            throw new InvalidInputEntryException(INVALID_NUMBER_ENTRY_0_8);
        }
    }

    @Override
    public boolean doesUserWantToStartNewGame() throws InvalidInputEntryException{
        try {
            String input = tryToGetValidYesOrNoInput();
            return (isStringYes(input));
        } catch (IOException e) {
            throw new InvalidInputEntryException(INVALID_YES_NO);
        } catch (InvalidNewGameInputException e) {
            throw new InvalidInputEntryException(INVALID_YES_NO);
        }
    }

    @Override
    public void drawBoard(Board board) {
        output.print(board.getLayout());
    }

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
