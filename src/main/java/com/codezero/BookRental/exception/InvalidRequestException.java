package com.codezero.BookRental.exception;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(String message) {
        super(message);
    }
}
