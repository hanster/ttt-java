package com.samhan.player;

import com.samhan.game.Board;
import com.samhan.game.Marker;
import com.samhan.ui.InvalidInputEntryException;

/**
 * Player interface
 */
public interface Player {
    public int getMove(Board board) throws InvalidInputEntryException;
    public Marker getMarker();
    public PlayerType getType();
}
