package com.samhan;

import com.samhan.ui.Ui;

/**
 * Stub Ui
 */
public class StubUi implements Ui {

    @Override
    public int getValidMoveInput(Board board) {
        return 0;
    }

    @Override
    public boolean doesUserWantToStartNewGame() {
        return false;
    }

    @Override
    public void drawBoard(Board board) {

    }

    @Override
    public void clearDisplay() {

    }

    @Override
    public void displayMessage(String message) {

    }
}