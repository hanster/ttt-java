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
        ui.drawBoard(board);
    }

    public void displayGameOver() {
        output("Game Over");
    }

}
