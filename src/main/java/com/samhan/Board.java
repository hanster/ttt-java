package com.samhan;

import java.util.ArrayList;
import java.util.List;

/**
 * Tic Tac Toe board logic
 * todo split out the player and ai objects
 *
 * @version $Revision$
 */
public class Board {
    private static int[][] winPatterns = new int[][]{{0, 1, 2}, // horizontals
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6}, // verticals
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8}, // diagonals
            {2, 4, 6}};
    public static final char X_TURN = 'x';
    public static final char O_TURN = 'o';
    private char[] positions;
    private char turn;

    public Board() {
        this("---------", X_TURN);
    }

    public Board(final String positions) {
        this(positions, X_TURN);
    }

    public Board(final String positions, final char turn) {
        this.positions = positions.toCharArray();
        this.turn = turn;
    }

    public String toString() {
        return new String(positions);
    }

    public char getTurn() {
        return turn;
    }

    public Board move(final int position) {
        char[] newPositions = positions.clone();
        newPositions[position] = turn;

        return new Board(new String(newPositions), (turn == X_TURN ? O_TURN : X_TURN));
    }


    public boolean isPositionFree(final int position) {
        return positions[position] == '-';
    }

    // todo duplication in minimax and calcBestMove
    public int minimax() {
        List<Integer> posMoves = possibleMoves();

        if (hasWon(X_TURN)) {
            return 100;
        }
        if (hasWon(O_TURN)) {
            return -100;
        }
        if (posMoves.size() == 0) {
            return 0;
        }
        //todo mm naming
        Integer mm = null;
        // for each possible move call minimax
        for (int idx : posMoves) {
            Integer value = move(idx).minimax();
            // check if the value is a new mini or max value
            if (mm == null || turn == X_TURN && mm < value || turn == O_TURN && value < mm) {
                mm = value;
            }
        }
        // need to account for depth
        return mm + (mm > 0 ? -1 : 1);
    }

    public int calcBestMove() {
        Integer mm = null;
        int best = -1;
        for (Integer idx : possibleMoves()) {
            Integer value = move(idx).minimax();
            if (mm == null || turn == X_TURN && mm < value || turn == O_TURN && value < mm) {
                mm = value;
                best = idx;
            }
        }
        return best;
    }

    public List<Integer> possibleMoves() {
        List<Integer> possibleMoves = new ArrayList<Integer>();

        for (int i = 0; i < positions.length; i++) {
            if (positions[i] == '-') {
                possibleMoves.add(i);
            }
        }
        return possibleMoves;
    }

    public boolean hasWon(final char turn) {
        for (int[] ints : winPatterns) {
            if (allPositionsMatchTurn(positions, ints, turn)) {
                return true;
            }
        }
        return false;
    }


    private boolean allPositionsMatchTurn(char[] boardPositions, int[] positionsToMatch, char turnToMatch) {
        boolean allMatchFlag = true;
        for (int i : positionsToMatch) {
            if (boardPositions[i] != turnToMatch) {
                allMatchFlag = false;
                break;
            }
        }
        return allMatchFlag;
    }

    public boolean hasEnded() {
        return hasWon(Board.X_TURN) || hasWon(Board.O_TURN) || possibleMoves().size() == 0;
    }
}
