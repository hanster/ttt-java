package com.samhan;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for the BoardChecker class
 * @version $Revision$
 */
public class BoardCheckerTest {
  private BoardChecker boardChecker;

  @Before
  public void initObjects() {
    boardChecker = new BoardChecker();
  }

  @Test
  public void testWinCases() {
    assertFalse(boardChecker.hasWon("---------", Board.X_TURN));
    assertFalse(boardChecker.hasWon("---------", Board.O_TURN));
    assertTrue(boardChecker.hasWon("xxx------", Board.X_TURN));
    assertFalse(boardChecker.hasWon("xxx------", Board.O_TURN));
    assertTrue(boardChecker.hasWon("ooo------", Board.O_TURN));
    assertFalse(boardChecker.hasWon("ooo------", Board.X_TURN));
    assertTrue(boardChecker.hasWon("---xxx---", Board.X_TURN));
    assertTrue(boardChecker.hasWon("------xxx", Board.X_TURN));
    assertTrue(boardChecker.hasWon("x---x---x", Board.X_TURN));
    assertTrue(boardChecker.hasWon("--x-x-x--", Board.X_TURN));
  }

  @Test
  public void testPossibleMoves() {
    assertArrayEquals(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, boardChecker.possibleMoves("---------"));
    assertArrayEquals(new Integer[]{3, 4, 5, 6, 7, 8}, boardChecker.possibleMoves("xxx------"));
    assertArrayEquals(new Integer[]{0, 1, 5, 6, 7}, boardChecker.possibleMoves("--xxx---x"));
    assertArrayEquals(new Integer[0], boardChecker.possibleMoves("xxxoooxxx"));
  }

  @Test
  public void testEnded(){
    assertFalse(boardChecker.hasEnded("---------"));
    assertTrue(boardChecker.hasEnded("xxx------"));
    assertTrue(boardChecker.hasEnded("ooo------"));
    assertTrue(boardChecker.hasEnded("xoxxoxoxo"));
  }
}
