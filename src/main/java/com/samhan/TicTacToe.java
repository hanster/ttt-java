package com.samhan;

import com.samhan.ai.Ai;
import com.samhan.ai.MiniMaxAi;
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
        while (newGame) {
            consoleGame = new ConsoleGame(ai, ui);
            consoleGame.displayWelcomeMsg();
            consoleGame.displayBoard();
            while (!consoleGame.hasGameEnded()) {
                consoleGame.choseMove();
                if (consoleGame.hasGameEnded()) {
                    consoleGame.displayBoard();
                    break;
                }
                consoleGame.makeComputerMove();
                consoleGame.displayBoard();
            }

            consoleGame.displayGameOver();
            consoleGame.displayResult();
            newGame = consoleGame.getNewGameInput();
        }
        consoleGame.displayGoodbyeMsg();

    }
}
