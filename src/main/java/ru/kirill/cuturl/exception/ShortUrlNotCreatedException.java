package ru.kirill.cuturl.exception;

public class ShortUrlNotCreatedException extends RuntimeException{

    public ShortUrlNotCreatedException(String message) {
        super(message);
    }
}
