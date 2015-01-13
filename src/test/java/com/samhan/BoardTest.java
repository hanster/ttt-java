package com.samhan;

import org.junit.Test;

import static org.junit.Assert.*;

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

}
