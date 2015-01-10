package com.samhan;

/**
 * Launcher class to make calls the game
 */
public class Launcher {
    public static void main(String args[]) {
        boolean newGame = true;
        ConsoleGame consoleGame = null;
        while (newGame) {
            consoleGame = new ConsoleGame();
            consoleGame.displayWelcomeMsg();
            consoleGame.displayBoardLegend();
            consoleGame.displayBoard();
            while (!consoleGame.hasGameEnded()) {
                consoleGame.choseMove();
                if (consoleGame.hasGameEnded()) {
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
