package com.samhan.player;

import com.samhan.Board;
import com.samhan.Marker;

/**
 * Player interface
 */
public interface Player {
    public int getMove(Board board);
    public Marker getMarker();
    public PlayerType getType();
}
