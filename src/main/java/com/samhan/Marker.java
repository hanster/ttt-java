package com.samhan;

/**
 * Different markers for the TicTacToe game
 */
public enum Marker {
    X('x'), O('o');

    public char asChar() {
        return asChar;
    }

    private final char asChar;

    private Marker(char asChar) {
        this.asChar = asChar;
    }

}
