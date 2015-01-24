package com.samhan;

import com.samhan.player.Player;
import com.samhan.ui.Ui;

/**
 * Console user interface implementation for the tic tac toe board
 * todo split console and game to seperate object
 * todo spacing the output
 */
public class ConsoleGame {

    private static final String TOP_MESSAGE = "Tic Tac Toe\n\n Player = x\n Computer = o\n\n";

    public static final int NUMBER_OF_PLAYERS = 2;
    private Board board;
    private Ui ui;

    private Player[] players;
    private int currentPlayerIndex = 0;
    private Player currentPlayer;

    private String bottomMessage = "";

    public ConsoleGame(Player player1, Player player2, Ui ui) {
        this.board = new Board();
        this.ui = ui;
        players = new Player[2];
        players[0] = player1;
        players[1] = player2;
        currentPlayer = players[currentPlayerIndex];
    }

    public void displayGoodbyeMsg() {
        ui.displayMessage("Thanks for playing.");

    }

    public boolean hasGameEnded() {
        return board.hasEnded();
    }

    public boolean getNewGameInput() {
        return ui.doesUserWantToStartNewGame();
    }

    private String getResultText() {
        String message;

        if (board.hasWon(Marker.X)) {
            message = Marker.X.asChar() +" wins";
        } else if (board.hasWon(Marker.O)) {
            message = Marker.O.asChar() + " wins";
        } else {
            message = "draw";
        }
        return message;
    }

    public void draw() {
        ui.clearDisplay();
        ui.displayMessage(TOP_MESSAGE);
        ui.drawBoard(board);
        ui.displayMessage(bottomMessage);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void attemptNextMove() {
        int movePosition = currentPlayer.getMove(board);
        this.board = this.board.move(movePosition, currentPlayer.getMarker());
        cycleCurrentPlayerToNextPlayer();
    }

    private void cycleCurrentPlayerToNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % NUMBER_OF_PLAYERS;
        currentPlayer = players[currentPlayerIndex];
    }

    public void setEndGameMessage() {
        bottomMessage = "Game Over\n\n" +getResultText() + "\n\n";
    }
}
