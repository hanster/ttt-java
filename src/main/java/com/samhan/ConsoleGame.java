package com.samhan;

import com.samhan.ai.Ai;
import com.samhan.player.ComputerPlayer;
import com.samhan.player.HumanPlayer;
import com.samhan.player.Player;
import com.samhan.ui.Ui;

/**
 * Console user interface implementation for the tic tac toe board
 * todo split console and game to seperate object
 * todo spacing the output
 */
public class ConsoleGame {

    private static final String lineSeperator = "-+-+-";
    private static final String legendLineSeperator = "=====Legend=====";
    private Board board;
    private Ui ui;
    private Player humanPlayer;
    private Player computerPlayer;

    public ConsoleGame(Ai ai, Ui ui) {
        this.board = new Board();
        this.ui = ui;
        this.humanPlayer = new HumanPlayer(ui, Marker.O);
        this.computerPlayer = new ComputerPlayer(ai, Marker.X);
    }

    public void makeComputerMove() {
        int bestMove = computerPlayer.getMove(board);
        this.board = this.board.move(bestMove, computerPlayer.getMarker());
    }

    public void choseMove() {
        int i = humanPlayer.getMove(board);
        board = board.move(i, humanPlayer.getMarker());
    }

    private void output(String string) {
        System.out.println(string);
    }


    public void displayWelcomeMsg() {
        output("TTT console edition");
        output("player = x");
        output("Computer = o");
    }

    public void displayGoodbyeMsg() {
        output("Thanks for playing.");

    }

    public boolean hasGameEnded() {
        return board.hasEnded();
    }

    public boolean getNewGameInput() {
        return ui.doesUserWantToStartNewGame();
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
        outputBoard("012345678");
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
        output(interSpaceWithChar(line.replace(Marker.EMPTY.asChar(), ' '), '|'));
    }

    public void displayGameOver() {
        output("Game Over");
    }

}
