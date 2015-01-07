package com.samhan;

import java.util.ArrayList;
import java.util.List;

/**
 * todo (hansa): Document Me
 * @version $Revision$
 */
public class Board {
  private char[] positions;
  private char turn;
  private int[][] winPatterns = new int[][]{{0, 1, 2}, // horizontals
          {3, 4, 5},
          {6, 7, 8},
          {0, 3, 6}, // verticals
          {1, 4, 7},
          {2, 5, 8},
          {0, 4, 8}, // diagonals
          {2, 4, 6}};


  public Board(final String positions, final char turn) {
    this.positions = positions.toCharArray();
    this.turn = turn;
  }

  public Board() {
    this.positions = "---------".toCharArray();
    this.turn = 'x';
  }

  public Board(final String positions) {
    this.positions = positions.toCharArray();
    turn = 'x';
  }

  public String toString() {
    return new String(positions);
  }

  public char turn() {
    return turn;
  }

  public Board move(final int position) {
    char[] newPositions = positions.clone();
    newPositions[position] = turn;

    return new Board(new String(newPositions), (turn == 'x' ? 'o' : 'x'));
  }

  public Integer[] possibleMoves() {
    List<Integer> possibleMoves = new ArrayList<Integer>();

    for (int i = 0; i < positions.length; i++) {
      if (isPositionFree(i)) {
        possibleMoves.add(i);
      }
    }

    return possibleMoves.toArray(new Integer[possibleMoves.size()]);
  }

  public boolean isPositionFree(final int position) {
    return positions[position] == '-';
  }

  public boolean hasWon(char turn) {
    // for each winning pattern check that all the positions are the same as the turn
    for (int[] winPattern : winPatterns) {
      boolean winFlag = true;
      for (int idx : winPattern) {
        if (positions[idx] != turn) {
          winFlag = false;
        }
      }
      if (winFlag) {
        return true;
      }
    }
    return false;
  }

  public boolean hasEnded() {
    return hasWon('x') || hasWon('o') || possibleMoves().length == 0;
  }

  public int minimax() {
    if (hasWon('x')) {return 100;}
    if (hasWon('o')) {return -100;}
    if (possibleMoves().length == 0) {return 0;}

    Integer mm = null;
    for (Integer idx : possibleMoves()) {
      Integer value = move(idx).minimax();
      if (mm == null || turn == 'x' && mm < value || turn == 'o' && value < mm) {
        mm = value;
      }
    }

    return mm + (mm < 0 ? 1 : -1);
  }

  public int bestMove() {
    Integer mm = null;
    int best = -1;
    for (Integer idx : possibleMoves()) {
      Integer value = move(idx).minimax();
      if (mm == null || turn == 'x' && mm < value || turn == 'o' && value < mm) {
        mm = value;
        best = idx;
      }
    }
    return best;
  }
}
