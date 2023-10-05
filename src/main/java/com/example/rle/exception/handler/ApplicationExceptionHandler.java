package com.example.rle.exception.handler;

import com.example.rle.exception.DuplicatePlayerException;
import com.example.rle.exception.InvalidPlayerPositionException;
import com.example.rle.exception.PlayerNotFoundException;
import com.example.rle.exception.TeamNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String badRequestHandler(MethodArgumentNotValidException ex) {
        return "Item name should not be blank";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String illegalArgumentHandler(IllegalArgumentException ex) {
        return "Team is required for adding a player.";
    }

    @ExceptionHandler(TeamNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String teamNotFoundHandler(TeamNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String playerNotFoundHandler(PlayerNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(DuplicatePlayerException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    String duplicatePlayerHandler(DuplicatePlayerException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidPlayerPositionException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidPlayerPositionHandler(InvalidPlayerPositionException ex) {
        return ex.getMessage();
    }
}
