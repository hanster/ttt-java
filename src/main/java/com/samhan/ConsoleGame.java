package com.samhan;

import com.samhan.Ai.Ai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Console user interface implementation for the tic tac toe board
 * todo split console and game to seperate object
 * todo spacing the output
 */
public class ConsoleGame {

    private int[] validInputMoves = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static final String lineSeperator = "-+-+-";
    private static final String legendLineSeperator = "=====Legend=====";
    private Board board;
    private Ai ai;
    public ConsoleGame(Ai ai) {
        this.board = new Board();
        this.ai = ai;
    }

    public void makeComputerMove() {
        int bestMove = this.ai.nextMove(this.board, Marker.X);
        this.board = this.board.move(bestMove, Marker.X);
    }

    private void output(String string) {
        System.out.println(string);
    }


    public void displayWelcomeMsg() {
        output("TTT console edition");
        output("Player = x");
        output("Computer = o");
    }

    public void displayGoodbyeMsg() {
        output("Thanks for playing.");

    }

    public boolean hasGameEnded() {
        return board.hasEnded();
    }

    public boolean getNewGameInput() {
        boolean newGame = false;
        boolean inputValid;
        do {
            output("Start a new game? ");
            String input = getUserInput();
            if (isStringYesOrNo(input)) {
                inputValid = true;
                if (isStringYes(input)) {
                    newGame = true;
                } else if (isStringNo(input)) {
                    newGame = false;
                }
            } else {
                inputValid = false;
                output("Invalid input ( yes or no)");
            }
        } while (!inputValid);

        return newGame;
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

    private String getUserInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }


    public void displayResult() {
        if (board.hasWon(Marker.X)) {
            output(Marker.X.asChar() +" wins");
        } else if (board.hasWon(Marker.O)) {
            output(Marker.O.asChar() +" wins");
        } else {
            output("draw");
        }
    }

    public void displayBoard() {
        outputBoard(this.board.toString());
    }

    public void displayBoardLegend() {
        output(legendLineSeperator);
        outputBoard("123456789");
        output(legendLineSeperator);
    }

    private void outputBoard(String boardString) {
        String top = boardString.substring(0, 3);
        String middle = boardString.substring(3, 6);
        String bottom = boardString.substring(6, 9);
        outputFormatedLine(top);
        output(lineSeperator);
        outputFormatedLine(middle);
        output(lineSeperator);
        outputFormatedLine(bottom);
    }



    public void choseMove() {
        boolean validMove = false;
        int i = 0;
        while (!validMove) {
            i = getValidMoveInput();
            i--;
            if (board.isPositionFree(i)) {
                validMove = true;
            } else {
                output("Invalid move. Space already taken.");
            }
        }
        board = board.move(i, Marker.O);
    }

    private int getValidMoveInput() {
        int input = 1;
        boolean validInput = false;
        while (!validInput) {
            output("Enter Move: ");
            try {
                input = Integer.parseInt(getUserInput());
            } catch (NumberFormatException e) {
                input = -1;
            }
            for (int i : validInputMoves) {
                if (input == i) {
                    validInput = true;
                }
            }
            if (!validInput) {
                output("Invalid input. (1-9)");
            }
        }
        return input;
    }


    public String interSpaceWithChar(String string, char interChar) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            sb.append(string.charAt(i));
            if (i != string.length() - 1) {
                sb.append(interChar);
            }
        }
        return sb.toString();
    }

    private void outputFormatedLine(String line) {
        output(interSpaceWithChar(line.replace('-', ' '), '|'));
    }

    public void displayGameOver() {
        output("Game Over");
    }

}
