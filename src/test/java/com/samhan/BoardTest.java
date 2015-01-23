package com.samhan;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Board tests
 */
public class BoardTest {
  @Test
  public void newBoard() {
    final Board board = new Board();
    assertEquals("---------", board.toString());
  }

  @Test
  public void moveOnce() {
    Board board = new Board().move(0, Marker.X);
    assertEquals("x--------", board.toString());

    board = new Board().move(1, Marker.X);
    assertEquals("-x-------", board.toString());
  }

  @Test
  public void moveTwice() {
    Board board = new Board().move(1, Marker.X).move(2, Marker.O);
    assertEquals("-xo------", board.toString());
  }

  @Test
  public void itReturnsTrueWhenPositionIsFree(){
    Board board = new Board("-x-ooo---");

    assertTrue(board.isPositionFree(0));
    assertTrue(board.isPositionFree(2));
    assertTrue(board.isPositionFree(8));
    assertTrue(board.isPositionFree(7));

  }

  @Test
  public void itReturnsFalseWhenPositionIsOccupied() {
    Board board = new Board("-x-ooo---");

    assertFalse(board.isPositionFree(1));
    assertFalse(board.isPositionFree(5));
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
    assertTrue(new Board("xxx------").hasWon(Marker.X));
    assertTrue(new Board("ooo------").hasWon(Marker.O));
    assertTrue(new Board("---xxx---").hasWon(Marker.X));
    assertTrue(new Board("------xxx").hasWon(Marker.X));
    assertTrue(new Board("x---x---x").hasWon(Marker.X));
    assertTrue(new Board("--x-x-x--").hasWon(Marker.X));
  }
  
  @Test
  public void playerHasNotWonCases(){
    assertFalse(new Board("---------").hasWon(Marker.X));
    assertFalse(new Board("---------").hasWon(Marker.O));
    assertFalse(new Board("xxx------").hasWon(Marker.O));
    assertFalse(new Board("ooo------").hasWon(Marker.X));
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
