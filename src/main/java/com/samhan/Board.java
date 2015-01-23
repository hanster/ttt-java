package com.samhan;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Tic Tac Toe board logic
 * todo split out the player and ai objects
 *
 * @version $Revision$
 */
public class Board {
    public static final char EMPTY_POSITION_MARKER = '-';
    private static final int BOARD_DIMENSION = 3;
    private static final int BOARD_SIZE = BOARD_DIMENSION * BOARD_DIMENSION;
    public static final String EMPTY_BOARD = StringUtils.repeat(EMPTY_POSITION_MARKER, BOARD_SIZE);
    private static int[][] winPatterns = new int[][]{{0, 1, 2}, // horizontals
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6}, // verticals
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8}, // diagonals
            {2, 4, 6}};

    private char[] positions;

    public Board() {
        this(EMPTY_BOARD);
    }

    public Board(final String positions) {
        this.positions = positions.toCharArray();
    }

    public String toString() {
        return new String(positions);
    }

    public Board move(final int position, Marker marker) {
        char[] newPositions = positions.clone();
        newPositions[position] = marker.asChar();

        return new Board(new String(newPositions));
    }

    public boolean isPositionFree(final int position) {
        return positions[position] == '-';
    }

    // todo duplication in minimax and calcBestMove
    private int minimax(Marker marker) {
        List<Integer> posMoves = possibleMoves();

        if (hasWon(Marker.X)) {
            return 100;
        }
        if (hasWon(Marker.O)) {
            return -100;
        }
        if (posMoves.size() == 0) {
            return 0;
        }
        //todo miniMaxValue naming
        Integer miniMaxValue = null;
        // for each possible move call minimax
        for (int idx : posMoves) {
            Integer value = move(idx, marker).minimax((marker == Marker.X ? Marker.O : Marker.X));
            // check if the value is a new mini or max value
            if (miniMaxValue == null || marker == Marker.X && miniMaxValue < value || marker == Marker.O && value < miniMaxValue) {
                miniMaxValue = value;
            }
        }
        // need to account for depth
        return miniMaxValue + (miniMaxValue > 0 ? -1 : 1);
    }

    public int calcBestMove(Marker marker) {
        Integer mm = null;
        int best = -1;
        for (Integer idx : possibleMoves()) {
            Integer value = move(idx, marker).minimax((marker == Marker.X ? Marker.O : Marker.X));
            if (mm == null || marker == Marker.X && mm < value || marker == Marker.O && value < mm) {
                mm = value;
                best = idx;
            }
        }
        return best;
    }

    public List<Integer> possibleMoves() {
        List<Integer> possibleMoves = new ArrayList<Integer>();

        for (int i = 0; i < positions.length; i++) {
            if (positions[i] == EMPTY_POSITION_MARKER) {
                possibleMoves.add(i);
            }
        }
        return possibleMoves;
    }

    public boolean hasWon(final Marker turn) {
        for (int[] ints : winPatterns) {
            if (allPositionsMatchTurn(positions, ints, turn)) {
                return true;
            }
        }
        return false;
    }


    private boolean allPositionsMatchTurn(char[] boardPositions, int[] positionsToMatch, Marker turnToMatch) {
        boolean allMatchFlag = true;
        for (int i : positionsToMatch) {
            if (boardPositions[i] != turnToMatch.asChar()) {
                allMatchFlag = false;
                break;
            }
        }
        return allMatchFlag;
    }

    public boolean hasEnded() {
        return hasWon(Marker.X) || hasWon(Marker.O) || possibleMoves().size() == 0;
    }
}
