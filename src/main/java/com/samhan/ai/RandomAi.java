package com.samhan.ai;

import com.samhan.Board;
import com.samhan.Marker;

import java.util.List;
import java.util.Random;

/**
 * Randomly select a move from the possible moves
 */
public class RandomAi implements Ai{
    @Override
    public int nextMove(Board board, Marker marker) {
        List<Integer> validMoves = board.possibleMoves();
        Random rand = new Random();

        int  n = rand.nextInt(validMoves.size());
        return validMoves.get(n);
    }
}
