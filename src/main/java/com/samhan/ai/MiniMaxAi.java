package com.samhan.ai;

import com.samhan.game.Board;
import com.samhan.game.Marker;

import java.util.List;

/**
 * Minimax implementation to work out the best move.
 */
public class MiniMaxAi implements Ai{
    public class MiniMax {
        public MiniMax() {
            value = -1;
            index = -1;
        }
        public int value;
        public int index;
    }

    public static final int MAX_O_SCORE = -100;
    public static final int MAX_X_SCORE = Math.abs(MAX_O_SCORE);
    public static final int DRAW_SCORE = 0;

    private int minimax(Board board, Marker marker) {
        List<Integer> possibleMoves = board.possibleMoves();

        if (board.hasWon(Marker.X)) {
            return MAX_X_SCORE;
        }
        if (board.hasWon(Marker.O)) {
            return MAX_O_SCORE;
        }
        if (possibleMoves.size() == 0) {
            return DRAW_SCORE;
        }

        MiniMax miniMax = calcMiniMaxValue(board, marker);
        return adjustMiniMaxValueForDepth(miniMax.value);
    }

    @Override
    public int nextMove(Board board, Marker marker) {
        MiniMax miniMax = calcMiniMaxValue(board, marker);
        return miniMax.index;
    }

    private MiniMax calcMiniMaxValue(Board board, Marker marker) {

        MiniMax miniMax = new MiniMax();
        for (Integer boardIndex : board.possibleMoves()) {
            Integer value = minimax(board.move(boardIndex, marker), switchMarker(marker));
            if (miniMax.value == -1 || isNewMiniMaxValue(marker, miniMax.value, value)) {
                miniMax.value = value;
                miniMax.index = boardIndex;
            }
        }
        return miniMax;
    }

    private boolean isNewMiniMaxValue(Marker marker, Integer miniMaxValue, Integer value) {
        return marker == Marker.X && miniMaxValue < value || marker == Marker.O && value < miniMaxValue;
    }

    private Marker switchMarker(Marker marker) {
        return (marker == Marker.X ? Marker.O : Marker.X);
    }

    private int adjustMiniMaxValueForDepth(Integer miniMaxValue) {
        return miniMaxValue + (miniMaxValue > 0 ? -1 : 1);
    }

}
