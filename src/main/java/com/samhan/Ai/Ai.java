package com.samhan.Ai;

import com.samhan.Board;
import com.samhan.Marker;

/**
 * Ai interface to select the next move
 */
public interface Ai {
    public int nextMove(Board board, Marker marker);
}
