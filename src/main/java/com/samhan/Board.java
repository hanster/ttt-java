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
    positions[position] = turn;
    return new Board(new String(positions), (turn == 'x' ? 'o' : 'x'));
  }

  public Integer[] possibleMoves() {
    List<Integer> possibleMoves = new ArrayList<Integer>();

    for (int i = 0; i < positions.length; i++) {
      if (isPositionFree(i)) {
        possibleMoves.add(i);
      }
    }

    Integer[] possibleMovesList = possibleMoves.toArray(new Integer[0]);

    return possibleMovesList;
  }

  public boolean isPositionFree(final int position) {
    return positions[position] == '-';
  }
}
