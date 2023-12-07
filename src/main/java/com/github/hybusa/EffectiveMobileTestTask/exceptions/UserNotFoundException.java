package com.github.hybusa.EffectiveMobileTestTask.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){}
    public UserNotFoundException(String text){
        super(text);
    }
}
