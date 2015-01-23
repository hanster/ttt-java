package com.samhan;

import java.util.ArrayList;
import java.util.List;

/**
 * Tic Tac Toe board logic
 *
 * @version $Revision$
 */
public class Board {
    private static final int BOARD_DIMENSION = 3;
    private static final int BOARD_SIZE = BOARD_DIMENSION * BOARD_DIMENSION;
    public static final String EMPTY_BOARD = repeat(Marker.EMPTY.asChar(), BOARD_SIZE);

    private static int[][] HORIZONTAL_WIN_PATTERNS = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
    private static int[][] VERTICAL_WIN_PATTERNS = new int[][]{{0, 3, 6}, {1, 4, 7}, {2, 5, 8}};
    private static int[][] DIAGONAL_WIN_PATTERNS = new int[][]{{0, 4, 8}, {2, 4, 6}};
    private static int[][][] ALL_TYPES_OF_WINNING_PATTERNS = new int[][][]{HORIZONTAL_WIN_PATTERNS, VERTICAL_WIN_PATTERNS, DIAGONAL_WIN_PATTERNS};


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
        return positions[position] == Marker.EMPTY.asChar();
    }

    public List<Integer> possibleMoves() {
        List<Integer> possibleMoves = new ArrayList<Integer>();

        for (int i = 0; i < positions.length; i++) {
            if (positions[i] == Marker.EMPTY.asChar()) {
                possibleMoves.add(i);
            }
        }
        return possibleMoves;
    }

    public boolean hasWon(final Marker turn) {
        for (int [][] typeOfWinningPatterns : ALL_TYPES_OF_WINNING_PATTERNS) {
            if (anyMatchesToWinningPatterns(typeOfWinningPatterns, turn)) {
                return true;
            }
        }
        return false;
    }

    private boolean anyMatchesToWinningPatterns(int[][] typeOfWinningPatterns, Marker turn) {
        for (int[] winPattern : typeOfWinningPatterns) {
            if (allPositionsMatchWinningPattern(winPattern, turn)) {
                return true;
            }
        }
        return false;
    }

    private boolean allPositionsMatchWinningPattern(int[] positionsToMatch, Marker turnToMatch) {
        boolean allMatchFlag = true;
        for (int i : positionsToMatch) {
            if (positions[i] != turnToMatch.asChar()) {
                allMatchFlag = false;
                break;
            }
        }
        return allMatchFlag;
    }

    public boolean hasEnded() {
        return hasWon(Marker.X) || hasWon(Marker.O) || possibleMoves().size() == 0;
    }

    /**
     * taken from org.apache.commons.lang3.StringUtils#repeat so we don't have to include the whole library
     *
     */
    private static String repeat(char ch, int repeat) {
        char[] buf = new char[repeat];

        for(int i = repeat - 1; i >= 0; --i) {
            buf[i] = ch;
        }

        return new String(buf);
    }
}
