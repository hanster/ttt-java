package com.samhan.game;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Testing the game loop
 */
public class TestGame implements Game {
    Queue<Boolean> runningAnswers;
    private int updatedCount = 0;
    private int drawCount = 0;

    public TestGame() {
    }

    @Override
    public boolean isRunning() {
        return runningAnswers.remove();
    }

    @Override
    public void update() {
        updatedCount++;
    }

    @Override
    public void draw() {
        drawCount++;

    }

    @Override
    public boolean isUpdated() {
        return updatedCount > 0;
    }

    public void queueIsRunningAnswers(boolean[] answers) {
        runningAnswers = new LinkedBlockingQueue<Boolean>();
        for (boolean answer : answers) {
            runningAnswers.add(answer);
        }
    }

}
