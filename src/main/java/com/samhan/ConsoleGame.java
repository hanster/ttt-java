package com.samhan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Console user interface implementation for the tic tac toe board
 */
public class ConsoleGame {

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
            newGame = getNewGameInput();
        }
    }


    public boolean hasGameEnded() {
        return this.board.hasEnded();
    }

    private static boolean getNewGameInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean newGame = false;
        try {
            boolean inputValid = false;
            while (!inputValid) {
                System.out.print("Start a new game? ");
                String input = br.readLine();
                if (input.toLowerCase().equals("y") ||
                        input.toLowerCase().equals("yes")) {
                    inputValid = true;
                    newGame = true;
                } else if ( input.toLowerCase().equals("n") ||
                input.toLowerCase().equals("no")){
                    inputValid = true;
                    newGame = false;
                } else {
                    System.out.println("Invalid input ( yes or no)");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return newGame;
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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try{
            boolean validMove = false;
            int i = 0;
            while (!validMove) {
                System.out.print("Enter Move: ");
                i = Integer.parseInt(br.readLine());
                i--;
                if (board.isPositionFree(i)) {
                    validMove = true;
                } else {
                    System.out.println("Invalid move");
                }
            }
            board = board.move(i);
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
