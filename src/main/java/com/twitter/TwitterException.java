package com.twitter;

public class TwitterException extends RuntimeException{

    public TwitterException(){}

    public TwitterException(String message) {
        super(message);
    }
}
