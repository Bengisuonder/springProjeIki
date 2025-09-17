package com.bengisu.springProjeIki.exception;

public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException(String message){ super(message); }
}
