package com.example.rle.exception;

public class TeamNotFoundException extends RuntimeException{
    public TeamNotFoundException(String teamName){
        super("Could not find team " + teamName);
    }
}
