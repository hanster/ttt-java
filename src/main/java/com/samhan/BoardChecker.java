package com.samhan;

import java.util.ArrayList;
import java.util.List;

/**
 * Checks the board position state
 * todo move back into board
 * @version $Revision$
 */
public class BoardChecker {
  private static int[][] winPatterns = new int[][]{{0, 1, 2}, // horizontals
    {3, 4, 5},
    {6, 7, 8},
    {0, 3, 6}, // verticals
    {1, 4, 7},
    {2, 5, 8},
    {0, 4, 8}, // diagonals
    {2, 4, 6}};

  public static  boolean hasWon(final String board, final char turn) {
    for (int[] ints : winPatterns) {
      if (allPositionsMatchTurn(board.toCharArray(), ints, turn)) {
        return true;
      }
    }
    return false;
  }


  private static boolean allPositionsMatchTurn(char[] boardPositions, int[] positionsToMatch, char turnToMatch) {
    boolean allMatchFlag = true;
    for (int i : positionsToMatch) {
      if (boardPositions[i] != turnToMatch) {
        allMatchFlag = false;
        break;
      }
    }
    return allMatchFlag;
  }

  public static Integer[]  possibleMoves(String positions) {
    char[] possitionChars = positions.toCharArray();
    List<Integer> possibleMoves = new ArrayList<Integer>();

    for (int i = 0; i < possitionChars.length; i++) {
      if (possitionChars[i] == '-') {
        possibleMoves.add(i);
      }
    }

    return possibleMoves.toArray(new Integer[possibleMoves.size()]);
  }

  public static boolean hasEnded(final String boardPositions) {
    return hasWon(boardPositions, Board.X_TURN) || hasWon(boardPositions, Board.O_TURN) || possibleMoves(boardPositions).length == 0;
  }
}
