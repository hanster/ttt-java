package com.samhan.ui;

import com.samhan.Board;

/**
 * User interface
 */
public interface Ui {

    public int getValidMoveInput(Board board);

    public boolean doesUserWantToStartNewGame();

    public void drawBoard(Board board);

    void clearDisplay();

    void displayMessage(String message);
}
