package com.dev.hakeem.myfinanceapp.exception;

public class UserEmailUniqueViolationException extends  RuntimeException {

    public  UserEmailUniqueViolationException(String message){
        super(message);
    }
}
