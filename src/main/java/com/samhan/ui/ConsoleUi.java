package com.samhan.ui;

import com.samhan.Board;
import com.samhan.InvalidMoveException;

import java.io.*;

/**
 * User interface Console implementation
 */
public class ConsoleUi implements Ui {
    public static final String INVALID_NUMBER_ENTRY_0_8 = "Invalid number entry. (0-8)\n";
    public static final String MOVE_ALREADY_TAKEN = "Move already taken.\n";
    public static final String DEFAULT_PROMPT = "Enter move:\n";

    private BufferedReader bufferedReader;
    private PrintStream output;


    public ConsoleUi(InputStream inputStream, PrintStream output) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        this.output = output;
    }

    @Override
    public int getValidInput(Board board) {
        String errorMessage = "";
        while (true) {
            try {
                output.print(errorMessage + DEFAULT_PROMPT);
                int input = tryToGetValidIntegerInput();
                if (!board.isPositionFree(input)) {
                    throw new InvalidMoveException();
                }
                return input;
            } catch (IOException e) {
                // loop again to try and get another input
            } catch (NumberFormatException e) {
                // loop again to try and get another input
                errorMessage = INVALID_NUMBER_ENTRY_0_8;
            } catch (InvalidMoveException e) {
                // loop again to try and get another input
                errorMessage = MOVE_ALREADY_TAKEN;
            } catch (ArrayIndexOutOfBoundsException e) {
                // loop again to try and get another input
                errorMessage = INVALID_NUMBER_ENTRY_0_8;
            }
        }
    }

    private int tryToGetValidIntegerInput() throws IOException {
        return Integer.parseInt(bufferedReader.readLine());
    }


}
