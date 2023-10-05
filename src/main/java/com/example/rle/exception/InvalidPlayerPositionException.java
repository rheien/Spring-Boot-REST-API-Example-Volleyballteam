package com.example.rle.exception;

public class InvalidPlayerPositionException extends RuntimeException{
    public InvalidPlayerPositionException(String position) {
        super("This position is not valid: " + position + ".");
    }
}
