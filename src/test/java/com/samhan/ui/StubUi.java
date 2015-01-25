package com.samhan.ui;

import com.samhan.game.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Stub Ui
 */
public class StubUi implements Ui {
    public Queue<Integer> inputMoveQueue;
    public int drawBoardCallCount = 0;
    public int clearDisplayCallCount = 0;
    public int displayMessageCallCount = 0;
    public List<String> displayMessageParamHistory = new ArrayList<String>();

    public StubUi() {

    }

    public StubUi(Queue<Integer> inputMoveQueue) {
        this.inputMoveQueue = inputMoveQueue;
    }

    @Override
    public int getValidMoveInput(Board board) {
        return inputMoveQueue.poll();
    }

    @Override
    public boolean doesUserWantToStartNewGame() {
        return false;
    }

    @Override
    public void drawBoard(Board board) {
        drawBoardCallCount++;
    }

    @Override
    public void clearDisplay() {
        clearDisplayCallCount++;
    }

    @Override
    public void displayMessage(String message) {
        displayMessageCallCount++;
        displayMessageParamHistory.add(message);
    }
}