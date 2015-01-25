package com.samhan.ui;

/**
 * Exception for invalid move
 */
public class InvalidInputEntryException extends RuntimeException {

    public InvalidInputEntryException(String message) {
        super(message);
    }
}