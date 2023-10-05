package com.example.rle.exception;

public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException(Long playerId) {
        super("Player Id not found.");
    }
}
