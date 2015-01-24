package com.samhan;

import com.samhan.ai.Ai;
import com.samhan.ai.MiniMaxAi;
import com.samhan.player.ComputerPlayer;
import com.samhan.player.HumanPlayer;
import com.samhan.player.Player;
import com.samhan.ui.ConsoleUi;
import com.samhan.ui.Ui;

/**
 * Launcher class to make calls to the game
 */
public class TicTacToe {
    public static void main(String args[]) {
        boolean newGame = true;
        ConsoleGame consoleGame = null;
        // select different types of AI?
        Ai ai = new MiniMaxAi();
        Ui ui = new ConsoleUi(System.in, System.out);
        Player playerOne = new HumanPlayer(ui, Marker.X);
        Player playerTwo = new ComputerPlayer(ai, Marker.O);

        while (newGame) {
            consoleGame = new ConsoleGame(playerOne, playerTwo, ui);
            consoleGame.draw();
            while (!consoleGame.hasGameEnded()) {
                consoleGame.attemptNextMove();
                if (consoleGame.hasGameEnded()) {
                    break;
                }
                consoleGame.draw();
            }
            consoleGame.setEndGameMessage();
            consoleGame.draw();

            newGame = consoleGame.getNewGameInput();
        }
        consoleGame.displayGoodbyeMsg();

    }
}
