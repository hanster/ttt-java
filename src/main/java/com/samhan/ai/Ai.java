package com.samhan.ai;

import com.samhan.game.Board;
import com.samhan.game.Marker;

/**
 * ai interface to select the next move
 */
public interface Ai {
    public int nextMove(Board board, Marker marker);
}
