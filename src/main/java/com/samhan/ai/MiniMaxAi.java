package com.samhan.ai;

import com.samhan.Board;
import com.samhan.Marker;

import java.util.List;

/**
 * Minimax implementation to work out the best move.
 */
public class MiniMaxAi implements Ai{

    private int minimax(Board board, Marker marker) {
        List<Integer> posMoves = board.possibleMoves();

        if (board.hasWon(Marker.X)) {
            return 100;
        }
        if (board.hasWon(Marker.O)) {
            return -100;
        }
        if (posMoves.size() == 0) {
            return 0;
        }
        Integer miniMaxValue = null;
        // for each possible move call minimax
        for (int idx : posMoves) {
            Integer value = minimax(board.move(idx, marker), (marker == Marker.X ? Marker.O : Marker.X));
            // check if the value is a new mini or max value
            if (miniMaxValue == null || marker == Marker.X && miniMaxValue < value || marker == Marker.O && value < miniMaxValue) {
                miniMaxValue = value;
            }
        }
        // need to account for depth
        return miniMaxValue + (miniMaxValue > 0 ? -1 : 1);
    }

    @Override
    public int nextMove(Board board, Marker marker) {
        Integer miniMaxValue = null;
        int best = -1;
        for (Integer idx : board.possibleMoves()) {
            Integer value = minimax(board.move(idx, marker), (marker == Marker.X ? Marker.O : Marker.X));
            if (miniMaxValue == null || marker == Marker.X && miniMaxValue < value || marker == Marker.O && value < miniMaxValue) {
                miniMaxValue = value;
                best = idx;
            }
        }
        return best;
    }

}
