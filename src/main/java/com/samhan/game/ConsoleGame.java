package com.samhan.game;

import com.samhan.player.Player;
import com.samhan.ui.InvalidInputEntryException;
import com.samhan.ui.Ui;

/**
 * Console user interface implementation for the tic tac toe board
 */
public class ConsoleGame implements Game{

    private static final String TOP_MESSAGE = "Tic Tac Toe\n\n Player = x\n Computer = o\n\n";
    private static final String GET_MOVE_MESSAGE = "Enter move:\n";
    public static final int NUMBER_OF_PLAYERS = 2;
    public static final String THANKS_FOR_PLAYING = "Thanks for playing.";
    public static final String WINS_MESSAGE = " wins";
    public static final String DRAW_MESSAGE = "draw";
    private Board board;
    private Ui ui;
    private boolean running = true;

    private Player[] players;
    private int currentPlayerIndex = 0;
    private Player currentPlayer;
    private String bottomMessage = "";
    private String errorMessage = "";

    public ConsoleGame(Player player1, Player player2, Ui ui) {
        this(player1, player2, ui, new Board());
    }

    public ConsoleGame(Player player1, Player player2, Ui ui, Board board) {
        this.board = board;
        this.ui = ui;
        players = new Player[2];
        players[0] = player1;
        players[1] = player2;
        currentPlayer = players[currentPlayerIndex];
        bottomMessage = GET_MOVE_MESSAGE;
    }

    public void setUpNewGame() {
        this.board = new Board();
        currentPlayerIndex = 0;
        currentPlayer = players[currentPlayerIndex];
        errorMessage = "";
        bottomMessage = GET_MOVE_MESSAGE;

    }

    public boolean hasGameEnded() {
        return board.hasEnded();
    }

    private String getResultText() {
        String message;

        if (board.hasWon(Marker.X)) {
            message = Marker.X.asChar() + WINS_MESSAGE;
        } else if (board.hasWon(Marker.O)) {
            message = Marker.O.asChar() + WINS_MESSAGE;
        } else {
            message = DRAW_MESSAGE;
        }
        return message;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public boolean isUpdated() {
        return false;
    }

    @Override
    public void update() {
        if (!hasGameEnded()) {
            attemptNextMove();
        } else {
            attemptNewGame();
        }

        if (!running) {
            bottomMessage = bottomMessage + "\n\n" + THANKS_FOR_PLAYING;
        }
    }

    @Override
    public void draw() {
        ui.clearDisplay();
        ui.displayMessage(TOP_MESSAGE);
        ui.drawBoard(board);
        ui.displayMessage(errorMessage);
        ui.displayMessage(bottomMessage);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void attemptNextMove() {
        try {
            int movePosition = currentPlayer.getMove(board);
            this.board = this.board.move(movePosition, currentPlayer.getMarker());
            cycleCurrentPlayerToNextPlayer();
            errorMessage = "";
            if (hasGameEnded()) {
                setEndGameMessage();
            }
        } catch (InvalidInputEntryException e) {
            errorMessage =  e.getMessage();
        }
    }

    public void attemptNewGame() {
        try {
            setEndGameMessage();
            running = ui.doesUserWantToStartNewGame();
            if (running) {
                setUpNewGame();
            }
            errorMessage = "";
        } catch (InvalidInputEntryException e) {
            errorMessage = e.getMessage();
        }
    }


    private void cycleCurrentPlayerToNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % NUMBER_OF_PLAYERS;
        currentPlayer = players[currentPlayerIndex];
    }

    public void setEndGameMessage() {
        bottomMessage = "Game Over\n\n" +getResultText() + "\n\n";
    }
}
