package com.example.rle.exception;

public class DuplicatePlayerException extends RuntimeException{
    public DuplicatePlayerException(String player){
        super("Player " + player + " already exists");
    }
}
