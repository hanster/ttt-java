package com.samhan.player;

import com.samhan.Board;
import com.samhan.Marker;
import com.samhan.ai.Ai;

/**
 * Computer player implementation
 */
public class ComputerPlayer implements Player{
    private final Marker marker;
    private Ai aiEngine;

    public ComputerPlayer(Ai ai, Marker marker) {
        aiEngine = ai;
        this.marker = marker;
    }

    @Override
    public int getMove(Board board) {
        return aiEngine.nextMove(board, marker);
    }

    @Override
    public Marker getMarker() {
        return marker;
    }

    @Override
    public PlayerType getType() {
        return PlayerType.COMPUTER;
    }
}
