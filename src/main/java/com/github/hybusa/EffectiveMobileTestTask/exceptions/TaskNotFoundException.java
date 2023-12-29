package com.github.hybusa.EffectiveMobileTestTask.exceptions;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(){}
    public TaskNotFoundException(String text){
        super(text);
    }
}
