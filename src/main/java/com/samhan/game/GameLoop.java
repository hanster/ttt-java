package com.samhan.game;

/**
 * Game loop
 */
public class GameLoop {
    Game game;

    public GameLoop(Game game) {
        this.game = game;
    }

    public void run() {
        game.draw();
        while (game.isRunning()) {
            game.update();
            game.draw();
        }
    }
}
