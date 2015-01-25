package com.samhan.player;

import com.samhan.game.Board;
import com.samhan.game.Marker;
import com.samhan.ui.InvalidInputEntryException;
import com.samhan.ui.Ui;

/**
 * Human player implementation
 */
public class HumanPlayer implements Player{
    private Ui ui;
    private Marker marker;

    public HumanPlayer(Ui ui, Marker marker) {
        this.ui = ui;
        this.marker = marker;
    }

    @Override
    public int getMove(Board board) throws InvalidInputEntryException {
        return ui.getValidMoveInput(board);
    }

    @Override
    public Marker getMarker() {
        return marker;
    }

    @Override
    public PlayerType getType() {
        return PlayerType.HUMAN;
    }
}
