package com.samhan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Console user interface implementation for the tic tac toe board
 */
public class ConsoleGame {

    private int[] validInputMoves = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static final String lineSeperator = "-+-+-";
    private static final String legendLineSeperator = "=====Legend=====";
    private Board board;

    public ConsoleGame () {
        this.board = new Board();
    }

    public static void main (String args[]) {
        boolean newGame = true;
        while (newGame) {
            ConsoleGame consoleGame = new ConsoleGame();
            System.out.println("TTT console edition");
            System.out.println("Player = x");
            System.out.println("Computer = o");
            consoleGame.displayBoard();
            while (!consoleGame.hasGameEnded()) {
                consoleGame.choseMove();
                int bestMove = consoleGame.board.bestMove();
                if (bestMove == -1) {
                    break;
                }
                consoleGame.board = consoleGame.board.move(bestMove);
                consoleGame.displayBoard();
            }

            System.out.println("Game over");
            displayResult(consoleGame);
            newGame = consoleGame.getNewGameInput();
        }

        System.out.println("Thanks for playing.");
    }


    public boolean hasGameEnded() {
        return this.board.hasEnded();
    }

    private boolean getNewGameInput() {
        boolean newGame = false;
        boolean inputValid;
        do {
            System.out.print("Start a new game? ");
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
                System.out.println("Invalid input ( yes or no)");
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
        return ( str.toLowerCase().equals("n") || str.toLowerCase().equals("no"));
    }

    public String getUserInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
             input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }


    private static void displayResult(ConsoleGame consoleGame) {
        if (consoleGame.board.hasWon('x')) {
            System.out.println("x wins");
        }
        else if (consoleGame.board.hasWon('o')) {
            System.out.println("o wins");
        }
        else {
            System.out.println("draw");
        }
    }

    public void displayBoard() {
        outputBoard(this.board.toString());
        System.out.println(legendLineSeperator);
        displayBoardLegend();
    }

    public void displayBoardLegend() {
        outputBoard("123456789");
    }

    private void outputBoard(String boardString) {
        String top = boardString.substring(0,3);
        String middle = boardString.substring(3, 6);
        String bottom = boardString.substring(6, 9);
        outputFormatedLine(top);
        System.out.println(lineSeperator);
        outputFormatedLine(middle);
        System.out.println(lineSeperator);
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
                System.out.println("Invalid move. Space already taken.");
            }
        }
        board = board.move(i);
    }

    private int getValidMoveInput() {
        int input = 1;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter Move: ");
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
                System.out.println("Invalid input. (1-9)");
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
        System.out.println (interSpaceWithChar(line.replace('-', ' '), '|'));
    }
}
