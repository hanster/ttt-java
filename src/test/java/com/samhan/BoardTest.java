package com.samhan;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * todo (hansa): Document Me
 * @version $Revision$
 */
public class BoardTest {
  @Test
  public void newBoard() {
    final Board board = new Board();
    assertEquals("---------", board.toString());
    assertEquals(Board.X_TURN, board.getTurn());
  }

  @Test
  public void moveOnce() {
    Board board = new Board().move(0);
    assertEquals("x--------", board.toString());
    assertEquals(Board.O_TURN, board.getTurn());

    board = new Board().move(1);
    assertEquals("-x-------", board.toString());
    assertEquals(Board.O_TURN, board.getTurn());
  }

  @Test
  public void moveTwice() {
    Board board = new Board().move(1).move(2);
    assertEquals("-xo------", board.toString());
    assertEquals(Board.X_TURN, board.getTurn());
  }

  @Test
  public void isPositionFree() {
    Board board = new Board("-x-ooo---");

    assertTrue(board.isPositionFree(0));
    assertFalse(board.isPositionFree(1));
    assertTrue(board.isPositionFree(2));
    assertFalse(board.isPositionFree(5));
    assertTrue(board.isPositionFree(7));
    assertTrue(board.isPositionFree(8));
  }

  @Test
  public void minimax(){
    assertEquals( 100, new Board("xxx------", Board.X_TURN).minimax());
    assertEquals(-100, new Board("ooo------", Board.O_TURN).minimax());
    assertEquals(   0, new Board("xoxxoxoxo", Board.X_TURN).minimax());
    assertEquals(  99, new Board("-xx------", Board.X_TURN).minimax());
    assertEquals( -99, new Board("-oo------", Board.O_TURN).minimax());
    assertEquals( -96, new Board("-oo------", Board.X_TURN).minimax());
    assertEquals(  96, new Board("-xx------", Board.O_TURN).minimax());
  }

  @Test
  public void bestMove(){
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
  public void possibleMoves() {
    assertEquals(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8), new Board("---------").possibleMoves());
    assertEquals(Arrays.asList(3, 4, 5, 6, 7, 8), new Board("xxx------").possibleMoves());
    assertEquals(Arrays.asList(0, 1, 5, 6, 7), new Board("--xxx---x").possibleMoves());
    assertEquals(Arrays.asList(), new Board("xxxoooxxx").possibleMoves());
  }

  @Test
  public void playerHasWonCases() {
    assertTrue(new Board("xxx------").hasWon(Board.X_TURN));
    assertTrue(new Board("ooo------").hasWon(Board.O_TURN));
    assertTrue(new Board("---xxx---").hasWon(Board.X_TURN));
    assertTrue(new Board("------xxx").hasWon(Board.X_TURN));
    assertTrue(new Board("x---x---x").hasWon(Board.X_TURN));
    assertTrue(new Board("--x-x-x--").hasWon(Board.X_TURN));
  }
  
  @Test
  public void playerHasNotWonCases(){
    assertFalse(new Board("---------").hasWon(Board.X_TURN));
    assertFalse(new Board("---------").hasWon(Board.O_TURN));
    assertFalse(new Board("xxx------").hasWon(Board.O_TURN));
    assertFalse(new Board("ooo------").hasWon(Board.X_TURN));
  }

  @Test
  public void boardStateEnded(){
    assertFalse(new Board("---------").hasEnded());
    assertFalse(new Board("-x---o---").hasEnded());

    assertTrue(new Board("xxx------").hasEnded());
    assertTrue(new Board("ooo------").hasEnded());
    assertTrue(new Board("xoxxoxoxo").hasEnded());
  }

}
