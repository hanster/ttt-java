package com.samhan.game;

/**
 * Different markers for the TicTacToe game
 */
public enum Marker {
    X('x'), O('o'), EMPTY('-');

    public char asChar() {
        return asChar;
    }

    private final char asChar;

    private Marker(char asChar) {
        this.asChar = asChar;
    }

}
