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
    assertEquals(Board.X_TURN, board.getTurn());
  }

  @Test
  public void testMoveOnce() {
    Board board = new Board().move(0);
    assertEquals("x--------", board.toString());
    assertEquals(Board.O_TURN, board.getTurn());

    board = new Board().move(1);
    assertEquals("-x-------", board.toString());
    assertEquals(Board.O_TURN, board.getTurn());
  }

  @Test
  public void testMoveTwice() {
    Board board = new Board().move(1).move(2);
    assertEquals("-xo------", board.toString());
    assertEquals(Board.X_TURN, board.getTurn());
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
    assertFalse(new Board().hasWon(Board.X_TURN));
    assertFalse(new Board().hasWon(Board.O_TURN));
    assertTrue(new Board("xxx------").hasWon(Board.X_TURN));
    assertFalse(new Board("xxx------").hasWon(Board.O_TURN));
    assertTrue(new Board("ooo------").hasWon(Board.O_TURN));
    assertFalse(new Board("ooo------").hasWon(Board.X_TURN));
    assertTrue(new Board("---xxx---").hasWon(Board.X_TURN));
    assertTrue(new Board("------xxx").hasWon(Board.X_TURN));
    assertTrue(new Board("x---x---x").hasWon(Board.X_TURN));
    assertTrue(new Board("--x-x-x--").hasWon(Board.X_TURN));
  }

  @Test
  public void testEnded(){
    assertFalse(new Board().hasEnded());
    assertTrue(new Board("xxx------").hasEnded());
    assertTrue(new Board("ooo------").hasEnded());
    assertTrue(new Board("xoxxoxoxo").hasEnded());
  }
  
  @Test
  public void testMinimax(){
    assertEquals( 100, new Board("xxx------", Board.X_TURN).minimax());
    assertEquals(-100, new Board("ooo------", Board.O_TURN).minimax());
    assertEquals(   0, new Board("xoxxoxoxo", Board.X_TURN).minimax());
    assertEquals(  99, new Board("-xx------", Board.X_TURN).minimax());
    assertEquals( -99, new Board("-oo------", Board.O_TURN).minimax());
    assertEquals( -96, new Board("-oo------", Board.X_TURN).minimax());
    assertEquals(  96, new Board("-xx------", Board.O_TURN).minimax());
  }

  @Test
  public void testBestMove(){
    assertEquals( 1, new Board("o-o------", Board.X_TURN).calcBestMove());
    assertEquals( 0, new Board("-xx------", Board.X_TURN).calcBestMove());
    assertEquals( 1, new Board("o-o------", Board.O_TURN).calcBestMove());
    assertEquals( 0, new Board("-oo------", Board.X_TURN).calcBestMove());
    assertEquals( 8, new Board("" +
            "xoo" +
            "-x-" +
            "---", Board.O_TURN).calcBestMove());
    assertEquals(-1, new Board("oxooxoxox", Board.O_TURN).calcBestMove());
  }

  @Test
  public void testSameMoveTwice(){
    Board board = new Board().move(1).move(1);

  }
}
