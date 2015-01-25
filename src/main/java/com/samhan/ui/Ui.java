package com.samhan.ui;

import com.samhan.game.Board;

/**
 * User interface
 */
public interface Ui {

    public int getValidMoveInput(Board board) throws InvalidInputEntryException;

    public boolean doesUserWantToStartNewGame() throws InvalidInputEntryException;

    public void drawBoard(Board board);

    void clearDisplay();

    void displayMessage(String message);
}
