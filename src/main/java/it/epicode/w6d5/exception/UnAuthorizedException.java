package it.epicode.w6d5.exception;

public class UnAuthorizedException extends RuntimeException{
    public UnAuthorizedException(String message){
        super( message);
    }

}
