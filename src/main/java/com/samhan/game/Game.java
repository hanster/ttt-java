package com.samhan.game;

/**
 * Game Interface
 */
public interface Game {
    public boolean isRunning();
    public boolean isUpdated();
    public void update();
    public void draw();
}
