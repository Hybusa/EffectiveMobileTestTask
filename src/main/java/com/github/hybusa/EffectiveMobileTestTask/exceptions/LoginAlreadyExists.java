package com.github.hybusa.EffectiveMobileTestTask.exceptions;

public class LoginAlreadyExists extends RuntimeException{
    public LoginAlreadyExists(){}
    public LoginAlreadyExists(String text){
        super(text);
    }
}
