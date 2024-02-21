package it.epicode.w6d5.exception;

public class LoginFaultException extends RuntimeException{
    private String message;
    public LoginFaultException(String message){
        super(message);
    }
}
