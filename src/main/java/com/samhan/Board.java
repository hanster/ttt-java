package com.samhan;

/**
 * Tic Tac Toe board logic
 * todo split out the player and ai objects
 *
 * @version $Revision$
 */
public class Board {
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
        String pos = new String(positions);
        Integer[] posMoves = BoardChecker.possibleMoves(new String(positions));

        if (BoardChecker.hasWon(pos, X_TURN)) {
            return 100;
        }
        if (BoardChecker.hasWon(pos,O_TURN)) {
            return -100;
        }
        if (posMoves.length == 0) {
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
        for (Integer idx : BoardChecker.possibleMoves(new String(positions))) {
            Integer value = move(idx).minimax();
            if (mm == null || turn == X_TURN && mm < value || turn == O_TURN && value < mm) {
                mm = value;
                best = idx;
            }
        }
        return best;
    }
}
