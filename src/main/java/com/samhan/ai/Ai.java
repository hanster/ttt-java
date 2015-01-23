package com.samhan.ai;

import com.samhan.Board;
import com.samhan.Marker;

/**
 * ai interface to select the next move
 */
public interface Ai {
    public int nextMove(Board board, Marker marker);
}
