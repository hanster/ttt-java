package com.samhan;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * todo (hansa): Document Me
 * @version $Revision$
 */
public class BoardTest {
  @Test
  public void testNewBoard() {
    final Board board = new Board();
    assertEquals("---------", board.toString());
    assertEquals('x', board.turn());
  }

  @Test
  public void testMoveOnce() {
    Board board = new Board().move(0);
    assertEquals("x--------", board.toString());
    assertEquals('o', board.turn());

    board = new Board().move(1);
    assertEquals("-x-------", board.toString());
    assertEquals('o', board.turn());
  }

  @Test
  public void testMoveTwice() {
    Board board = new Board().move(1).move(2);
    assertEquals("-xo------", board.toString());
    assertEquals('x', board.turn());
  }

  @Test
  public void testIsPositionFree() {
    Board board = new Board("-x-ooo---");

    assertTrue(board.isPositionFree(0));
    assertFalse(board.isPositionFree(1));
    assertTrue(board.isPositionFree(2));
    assertFalse(board.isPositionFree(5));
    assertTrue(board.isPositionFree(7));
    assertTrue(board.isPositionFree(8));
  }

  @Test
  public void testPossibleMoves() {
    Board board = new Board();
    assertArrayEquals(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, board.possibleMoves());

    board = new Board("xxx------");
    assertArrayEquals(new Integer[]{3, 4, 5, 6, 7, 8}, board.possibleMoves());

    board = new Board("--xxx---x");
    assertArrayEquals(new Integer[]{0, 1, 5, 6, 7}, board.possibleMoves());

    board = new Board("xxxoooxxx");
    assertArrayEquals(new Integer[0], board.possibleMoves());
  }



  @Test
  public void testWinCases() {
    assertFalse(new Board().hasWon('x'));
    assertFalse(new Board().hasWon('o'));
    assertTrue(new Board("xxx------").hasWon('x'));
    assertFalse(new Board("xxx------").hasWon('o'));
    assertTrue(new Board("ooo------").hasWon('o'));
    assertFalse(new Board("ooo------").hasWon('x'));
    assertTrue(new Board("---xxx---").hasWon('x'));
    assertTrue(new Board("------xxx").hasWon('x'));
    assertTrue(new Board("x---x---x").hasWon('x'));
    assertTrue(new Board("--x-x-x--").hasWon('x'));
  }

  @Test
  public void testEnded(){
      assertFalse(new Board().hasEnded());
      assertTrue(new Board("xxx------").hasEnded());
      assertTrue(new Board("ooo------").hasEnded());
      assertTrue(new Board("xoxxoxoxo").hasEnded());
  }
}
