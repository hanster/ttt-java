package com.samhan;

import com.samhan.Ai.Ai;
import com.samhan.Ai.MiniMaxAi;

/**
 * Launcher class to make calls to the game
 */
public class TicTacToe {
    public static void main(String args[]) {
        boolean newGame = true;
        ConsoleGame consoleGame = null;
        // select different types of AI?
        Ai ai = new MiniMaxAi();
        while (newGame) {
            consoleGame = new ConsoleGame(ai);
            consoleGame.displayWelcomeMsg();
            consoleGame.displayBoardLegend();
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
