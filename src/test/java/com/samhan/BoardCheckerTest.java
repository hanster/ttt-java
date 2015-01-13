package com.samhan;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test cases for the BoardChecker class
 * @version $Revision$
 */
public class BoardCheckerTest {


  @Test
  public void testWinCases() {
    assertFalse(BoardChecker.hasWon("---------", Board.X_TURN));
    assertFalse(BoardChecker.hasWon("---------", Board.O_TURN));
    assertTrue(BoardChecker.hasWon("xxx------", Board.X_TURN));
    assertFalse(BoardChecker.hasWon("xxx------", Board.O_TURN));
    assertTrue(BoardChecker.hasWon("ooo------", Board.O_TURN));
    assertFalse(BoardChecker.hasWon("ooo------", Board.X_TURN));
    assertTrue(BoardChecker.hasWon("---xxx---", Board.X_TURN));
    assertTrue(BoardChecker.hasWon("------xxx", Board.X_TURN));
    assertTrue(BoardChecker.hasWon("x---x---x", Board.X_TURN));
    assertTrue(BoardChecker.hasWon("--x-x-x--", Board.X_TURN));
  }

  @Test
  public void testPossibleMoves() {
    assertArrayEquals(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, BoardChecker.possibleMoves("---------"));
    assertArrayEquals(new Integer[]{3, 4, 5, 6, 7, 8}, BoardChecker.possibleMoves("xxx------"));
    assertArrayEquals(new Integer[]{0, 1, 5, 6, 7}, BoardChecker.possibleMoves("--xxx---x"));
    assertArrayEquals(new Integer[0], BoardChecker.possibleMoves("xxxoooxxx"));
  }

  @Test
  public void testEnded(){
    assertFalse(BoardChecker.hasEnded("---------"));
    assertTrue(BoardChecker.hasEnded("xxx------"));
    assertTrue(BoardChecker.hasEnded("ooo------"));
    assertTrue(BoardChecker.hasEnded("xoxxoxoxo"));
  }
}
